import { useCallback, useRef, useState } from "react";
import { sendAdbInput, sendCommand } from "../api/client";

interface AssistantVirtualKeyboardProps {
  deviceId: string;
  isOnline: boolean;
  useAdbInput?: boolean;
}

type KeyDef =
  | { type: "char"; label: string; value?: string; flex?: number }
  | { type: "action"; id: "shift" | "backspace" | "space" | "enter" | "mode" | "clear"; label: string; flex?: number };

const ALPHA_ROWS: KeyDef[][] = [
  [
    { type: "char", label: "q" },
    { type: "char", label: "w" },
    { type: "char", label: "e" },
    { type: "char", label: "r" },
    { type: "char", label: "t" },
    { type: "char", label: "y" },
    { type: "char", label: "u" },
    { type: "char", label: "i" },
    { type: "char", label: "o" },
    { type: "char", label: "p" },
  ],
  [
    { type: "char", label: "a" },
    { type: "char", label: "s" },
    { type: "char", label: "d" },
    { type: "char", label: "f" },
    { type: "char", label: "g" },
    { type: "char", label: "h" },
    { type: "char", label: "j" },
    { type: "char", label: "k" },
    { type: "char", label: "l" },
  ],
  [
    { type: "action", id: "shift", label: "⇧", flex: 1.2 },
    { type: "char", label: "z" },
    { type: "char", label: "x" },
    { type: "char", label: "c" },
    { type: "char", label: "v" },
    { type: "char", label: "b" },
    { type: "char", label: "n" },
    { type: "char", label: "m" },
    { type: "action", id: "backspace", label: "⌫", flex: 1.2 },
  ],
  [
    { type: "action", id: "mode", label: "?123", flex: 1.3 },
    { type: "char", label: "@", flex: 1 },
    { type: "action", id: "space", label: "空格", flex: 4 },
    { type: "char", label: ".", flex: 1 },
    { type: "action", id: "enter", label: "回车", flex: 1.4 },
  ],
];

const SYMBOL_ROWS: KeyDef[][] = [
  [
    { type: "char", label: "1" },
    { type: "char", label: "2" },
    { type: "char", label: "3" },
    { type: "char", label: "4" },
    { type: "char", label: "5" },
    { type: "char", label: "6" },
    { type: "char", label: "7" },
    { type: "char", label: "8" },
    { type: "char", label: "9" },
    { type: "char", label: "0" },
  ],
  [
    { type: "char", label: "-" },
    { type: "char", label: "/" },
    { type: "char", label: ":" },
    { type: "char", label: ";" },
    { type: "char", label: "(" },
    { type: "char", label: ")" },
    { type: "char", label: "$" },
    { type: "char", label: "&" },
    { type: "char", label: "@" },
    { type: "char", label: "\"" },
  ],
  [
    { type: "char", label: "#" },
    { type: "char", label: "%" },
    { type: "char", label: "^" },
    { type: "char", label: "*" },
    { type: "char", label: "+" },
    { type: "char", label: "=" },
    { type: "char", label: "_" },
    { type: "char", label: "!" },
    { type: "action", id: "backspace", label: "⌫", flex: 1.4 },
  ],
  [
    { type: "action", id: "mode", label: "ABC", flex: 1.3 },
    { type: "char", label: ",", flex: 1 },
    { type: "action", id: "space", label: "空格", flex: 4 },
    { type: "char", label: ".", flex: 1 },
    { type: "action", id: "enter", label: "回车", flex: 1.4 },
  ],
];

export function AssistantVirtualKeyboard({
  deviceId,
  isOnline,
  useAdbInput = false,
}: AssistantVirtualKeyboardProps) {
  const [open, setOpen] = useState(false);
  const [shiftOn, setShiftOn] = useState(false);
  const [symbolMode, setSymbolMode] = useState(false);
  const [draft, setDraft] = useState("");
  const [status, setStatus] = useState<string | null>(null);
  const queueRef = useRef(Promise.resolve());

  const enqueue = useCallback((task: () => Promise<void>) => {
    queueRef.current = queueRef.current.then(task).catch(() => undefined);
  }, []);

  const pushChar = useCallback(
    (ch: string) => {
      if (!isOnline || !ch) {
        return;
      }
      setDraft((current) => `${current}${ch}`);
      enqueue(async () => {
        try {
          if (useAdbInput) {
            await sendAdbInput(deviceId, { action: "text", text: ch });
          } else {
            const result = await sendCommand({
              deviceId,
              cmd: "input_text",
              params: { text: ch },
              waitMs: 4000,
            });
            if (result.status !== "success") {
              setStatus(result.errorMessage ?? result.status);
              return;
            }
          }
          setStatus(null);
        } catch (error) {
          setStatus(error instanceof Error ? error.message : "input_failed");
        }
      });
    },
    [deviceId, enqueue, isOnline, useAdbInput],
  );

  const sendSpecial = useCallback(
    (mode: "delete" | "enter") => {
      if (!isOnline) {
        return;
      }
      enqueue(async () => {
        try {
          if (useAdbInput) {
            await sendAdbInput(deviceId, { action: mode });
          } else {
            const result = await sendCommand({
              deviceId,
              cmd: "input_text",
              params: { mode },
              waitMs: 4000,
            });
            if (result.status !== "success") {
              setStatus(result.errorMessage ?? result.status);
              return;
            }
          }
          setStatus(null);
        } catch (error) {
          setStatus(error instanceof Error ? error.message : "input_failed");
        }
      });
    },
    [deviceId, enqueue, isOnline, useAdbInput],
  );

  function handleKey(def: KeyDef) {
    if (!isOnline) {
      return;
    }
    if (def.type === "action") {
      switch (def.id) {
        case "shift":
          setShiftOn((v) => !v);
          return;
        case "mode":
          setSymbolMode((v) => !v);
          setShiftOn(false);
          return;
        case "space":
          pushChar(" ");
          return;
        case "backspace":
          setDraft((current) => current.slice(0, -1));
          sendSpecial("delete");
          return;
        case "enter":
          sendSpecial("enter");
          return;
        case "clear":
          setDraft("");
          return;
        default:
          return;
      }
    }

    const raw = def.value ?? def.label;
    const ch = !symbolMode && shiftOn ? raw.toUpperCase() : raw;
    if (shiftOn && !symbolMode) {
      setShiftOn(false);
    }
    pushChar(ch);
  }

  function handleToggle() {
    if (!open) {
      setDraft("");
      setStatus(null);
      setShiftOn(false);
      setSymbolMode(false);
    }
    setOpen((current) => !current);
  }

  const rows = symbolMode ? SYMBOL_ROWS : ALPHA_ROWS;

  return (
    <div
      className={`assistant-keyboard-dock ${open ? "assistant-keyboard-dock--open" : ""}`}
      onPointerDown={(event) => event.stopPropagation()}
      onPointerMove={(event) => event.stopPropagation()}
      onPointerUp={(event) => event.stopPropagation()}
    >
      <div className="assistant-keyboard-toolbar">
        <button
          type="button"
          className={`assistant-keyboard-toggle ${open ? "active" : ""}`}
          disabled={!isOnline}
          onClick={handleToggle}
        >
          {open ? "收起" : "模拟键盘"}
        </button>
        {open ? (
          <>
            <span className="assistant-keyboard-draft" title={draft}>
              {draft || "（空）"}
            </span>
            <button
              type="button"
              className="assistant-keyboard-clear"
              disabled={!isOnline}
              onClick={() => setDraft("")}
            >
              清空
            </button>
          </>
        ) : null}
      </div>
      {open ? (
        <div className="assistant-keyboard-panel">
          {rows.map((row, rowIndex) => (
            <div key={`row-${rowIndex}`} className="assistant-keyboard-row">
              {row.map((key) => {
                const label =
                  key.type === "char" && !symbolMode && shiftOn
                    ? key.label.toUpperCase()
                    : key.label;
                const active =
                  (key.type === "action" && key.id === "shift" && shiftOn) ||
                  (key.type === "action" && key.id === "mode" && symbolMode);
                return (
                  <button
                    key={`${rowIndex}-${key.type === "action" ? key.id : key.label}`}
                    type="button"
                    className={`assistant-keyboard-key ${active ? "active" : ""}`}
                    style={{ flex: key.flex ?? 1 }}
                    disabled={!isOnline}
                    onClick={() => handleKey(key)}
                  >
                    {label}
                  </button>
                );
              })}
            </div>
          ))}
          {status ? <div className="assistant-keyboard-status">{status}</div> : null}
        </div>
      ) : null}
    </div>
  );
}
