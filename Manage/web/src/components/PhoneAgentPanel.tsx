import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { Alert, Button, Input, Select, Tag } from "antd";
import {
  fetchAiConfig,
  planPhoneAgentStep,
  sendAdbInput,
  sendCommand,
  type PhoneAgentHistoryStep,
  type PhoneAgentStepPlan,
} from "../api/client";
import type { UiTreeSnapshot } from "../types";
import {
  buildCompactUiNodes,
  formatPhoneAgentAction,
  resolveNodeCenter,
  type CompactUiNode,
} from "../utils/phone-agent";
import { useI18n, t } from "../i18n";


const { TextArea } = Input;
const MAX_STEPS = 40;
const STEP_INTERVAL_OPTIONS = [
  { value: 800, label: "0.8 秒" },
  { value: 1500, label: "1.5 秒（推荐）" },
  { value: 2500, label: "2.5 秒" },
  { value: 4000, label: "4 秒" },
];

interface PhoneAgentPanelProps {
  deviceId: string;
  isOnline: boolean;
  snapshot: UiTreeSnapshot | null;
  screenWidth: number;
  screenHeight: number;
  useAdbInput?: boolean;
}

interface PendingPlan {
  plan: PhoneAgentStepPlan;
  compactNodes: CompactUiNode[];
}

interface PlanResult {
  plan: PhoneAgentStepPlan;
  compactNodes: CompactUiNode[];
  uiSnapshot: UiTreeSnapshot;
}

function sleep(ms: number) {
  return new Promise<void>((resolve) => {
    window.setTimeout(resolve, ms);
  });
}

function mapAiError(message: string): string {
  if (message === "ai_disabled") {
    return t("AI 未启用，请先在「AI 配置」中开启");
  }
  if (message === "ai_api_key_missing") {
    return t("未配置 API Key");
  }
    if (message === "ui_nodes_empty") {
      return t("UI 树为空，请先开启屏幕助手并等待节点");
  }
  if (message === "goal_required") {
    return t("请输入目标");
  }
  return message;
}

function terminalHistoryStep(
  stepIndex: number,
  plan: PhoneAgentStepPlan,
): PhoneAgentHistoryStep {
  return {
    stepIndex,
    action: plan.action,
    reason: plan.reason,
    params: plan.params,
    result:
      plan.action === "done"
        ? String(plan.params.summary ?? "")
        : String(plan.params.reason ?? plan.reason),
  };
}

export function PhoneAgentPanel({
  deviceId,
  isOnline,
  snapshot,
  screenWidth,
  screenHeight,
  useAdbInput = false,
}: PhoneAgentPanelProps) {
  useI18n();
  const [goal, setGoal] = useState("");
  const [aiReady, setAiReady] = useState<boolean | null>(null);
  const [planBusy, setPlanBusy] = useState(false);
  const [executeBusy, setExecuteBusy] = useState(false);
  const [autoRunning, setAutoRunning] = useState(false);
  const [stepIntervalMs, setStepIntervalMs] = useState(1500);
  const [alertMessage, setAlertMessage] = useState<string | null>(null);
  const [alertType, setAlertType] = useState<"success" | "error" | "info" | "warning">("info");
  const [history, setHistory] = useState<PhoneAgentHistoryStep[]>([]);
  const [pending, setPending] = useState<PendingPlan | null>(null);
  const [finished, setFinished] = useState(false);

  const stopRequestedRef = useRef(false);

  useEffect(() => {
    void fetchAiConfig()
      .then((config) => {
        setAiReady(config.enabled === 1 && config.hasApiKey);
      })
      .catch(() => {
        setAiReady(false);
      });
  }, []);

  useEffect(() => {
    return () => {
      stopRequestedRef.current = true;
    };
  }, []);

  const stepCount = history.length;
  const busy = planBusy || executeBusy || autoRunning;
  const canPlan = isOnline && aiReady && !busy && !finished && stepCount < MAX_STEPS;
  const canAutoRun = canPlan && Boolean(goal.trim());

  const showAlert = useCallback(
    (type: "success" | "error" | "info" | "warning", message: string) => {
      setAlertType(type);
      setAlertMessage(message);
    },
    [],
  );

  const snapshotRef = useRef(snapshot);
  snapshotRef.current = snapshot;

  async function fetchFreshUiSnapshot(): Promise<UiTreeSnapshot | null> {
    const live = snapshotRef.current;
    if (!live || live.nodes.length === 0) {
      throw new Error("ui_nodes_empty");
    }
    return live;
  }

  async function planNextStep(currentHistory: PhoneAgentHistoryStep[]): Promise<PlanResult> {
    const uiSnapshot = await fetchFreshUiSnapshot();
    if (!uiSnapshot || uiSnapshot.nodes.length === 0) {
      throw new Error("ui_nodes_empty");
    }

    const compactNodes = buildCompactUiNodes(uiSnapshot.nodes, {
      primaryPackage: uiSnapshot.packageName,
      screenWidth: uiSnapshot.screenWidth,
      screenHeight: uiSnapshot.screenHeight,
      max: 100,
    });

    const plan = await planPhoneAgentStep({
      goal: goal.trim(),
      screenWidth: uiSnapshot.screenWidth,
      screenHeight: uiSnapshot.screenHeight,
      packageName: uiSnapshot.packageName,
      keyguardLocked: uiSnapshot.keyguardLocked,
      nodes: compactNodes,
      history: currentHistory,
    });

    return { plan, compactNodes, uiSnapshot };
  }

  async function executePlan(plan: PhoneAgentStepPlan, compactNodes: CompactUiNode[]) {
    const { action, params } = plan;

    if (action === "wait") {
      const ms = Number(params.ms ?? 1000);
      await sleep(Math.min(3000, Math.max(300, ms)));
      return { ok: true, message: t("已等待 {0}ms", { 0: ms }) };
    }

    if (action === "done" || action === "failed") {
      return {
        ok: action === "done",
        message: action === "done" ? String(params.summary ?? t("完成")) : String(params.reason ?? t("失败")),
      };
    }

    if (action === "click") {
      let x = Number(params.x);
      let y = Number(params.y);
      const nodeIndex = Number(params.nodeIndex);
      if (nodeIndex > 0) {
        const node = compactNodes.find((item) => item.i === nodeIndex);
        if (!node) {
          throw new Error(t("节点 #{0} 不存在", { 0: nodeIndex }));
        }
        const center = resolveNodeCenter(node);
        x = center.x;
        y = center.y;
      }
      if (useAdbInput) {
        await sendAdbInput(deviceId, { action: "tap", x, y });
      } else {
        const result = await sendCommand({
          deviceId,
          cmd: "click",
          params: { x, y },
          waitMs: 800,
        });
        if (result.status !== "success") {
          throw new Error(result.errorMessage ?? "click_failed");
        }
      }
      return { ok: true, message: t("点击 ({0}, {1})", { 0: x, 1: y }) };
    }

    if (action === "swipe") {
      const durationMs = Number(params.durationMs ?? params.duration ?? 320);
      if (useAdbInput) {
        await sendAdbInput(deviceId, {
          action: "swipe",
          x1: Number(params.x1),
          y1: Number(params.y1),
          x2: Number(params.x2),
          y2: Number(params.y2),
          durationMs,
        });
      } else {
        const result = await sendCommand({
          deviceId,
          cmd: "swipe",
          params: {
            x1: Number(params.x1),
            y1: Number(params.y1),
            x2: Number(params.x2),
            y2: Number(params.y2),
            duration: durationMs,
          },
          waitMs: 2000,
        });
        if (result.status !== "success") {
          throw new Error(result.errorMessage ?? "swipe_failed");
        }
      }
      return { ok: true, message: formatPhoneAgentAction(plan) };
    }

    if (action === "input_text") {
      const text = String(params.text ?? "");
      if (useAdbInput) {
        await sendAdbInput(deviceId, { action: "text", text });
      } else {
        const result = await sendCommand({
          deviceId,
          cmd: "input_text",
          params: { text },
          waitMs: 4000,
        });
        if (result.status !== "success") {
          throw new Error(result.errorMessage ?? "input_text_failed");
        }
      }
      return { ok: true, message: t("已输入文本") };
    }

    if (action === "open_app") {
      const result = await sendCommand({
        deviceId,
        cmd: "open_app",
        params: { packageName: String(params.packageName ?? "") },
        waitMs: 4000,
      });
      if (result.status !== "success") {
        throw new Error(result.errorMessage ?? "open_app_failed");
      }
      return { ok: true, message: t("已打开 {0}", { 0: params.packageName }) };
    }

    if (action === "global_action") {
      const raw = String(params.action ?? "").toLowerCase();
      const mapped = raw === "home" ? "HOME" : raw === "back" ? "BACK" : raw === "recents" ? "RECENTS" : null;
      if (!mapped) {
        throw new Error(`unsupported_action:${raw}`);
      }
      if (useAdbInput) {
        await sendAdbInput(deviceId, { action: mapped });
      } else {
        const result = await sendCommand({
          deviceId,
          cmd: "global_action",
          params: { action: raw },
          waitMs: 1500,
        });
        if (result.status !== "success") {
          throw new Error(result.errorMessage ?? "global_action_failed");
        }
      }
      return { ok: true, message: formatPhoneAgentAction(plan) };
    }

    if (action === "wake_screen") {
      const result = await sendCommand({
        deviceId,
        cmd: "wake_screen",
        params: {},
        waitMs: 2500,
      });
      if (result.status !== "success") {
        throw new Error(result.errorMessage ?? "wake_screen_failed");
      }
      return { ok: true, message: t("已唤醒屏幕") };
    }

    throw new Error(`unsupported_action:${action}`);
  }

  async function handlePlanStep() {
    if (!goal.trim()) {
      showAlert("warning", t("请输入目标"));
      return;
    }
    if (!isOnline) {
      showAlert("warning", t("设备离线"));
      return;
    }

    setPlanBusy(true);
    setAlertMessage(null);
    try {
      const { plan, compactNodes } = await planNextStep(history);

      if (plan.action === "done" || plan.action === "failed") {
        setHistory((prev) => [...prev, terminalHistoryStep(prev.length + 1, plan)]);
        setPending(null);
        setFinished(true);
        showAlert(plan.action === "done" ? "success" : "warning", formatPhoneAgentAction(plan));
        return;
      }

      setPending({ plan, compactNodes });
      showAlert("info", t("已规划第 {0} 步，请确认后执行", { 0: stepCount + 1 }));
    } catch (error) {
      const message = error instanceof Error ? error.message : "phone_agent_plan_failed";
      showAlert("error", mapAiError(message));
    } finally {
      setPlanBusy(false);
    }
  }

  async function handleExecutePending() {
    if (!pending) {
      return;
    }
    setExecuteBusy(true);
    setAlertMessage(null);
    const { plan, compactNodes } = pending;
    try {
      const outcome = await executePlan(plan, compactNodes);
      const nextStep: PhoneAgentHistoryStep = {
        stepIndex: history.length + 1,
        action: plan.action,
        reason: plan.reason,
        params: plan.params,
        result: outcome.message,
      };
      setHistory((prev) => [...prev, nextStep]);
      setPending(null);

      if (plan.action === "done" || plan.action === "failed") {
        setFinished(true);
        showAlert(plan.action === "done" ? "success" : "warning", outcome.message);
        return;
      }

      showAlert("success", t("第 {0} 步已执行：{1}", { 0: nextStep.stepIndex, 1: outcome.message }));
    } catch (error) {
      const message = error instanceof Error ? error.message : "execute_failed";
      showAlert("error", message);
    } finally {
      setExecuteBusy(false);
    }
  }

  function handleStopAuto() {
    stopRequestedRef.current = true;
    showAlert("warning", t("正在停止自动执行…"));
  }

  async function handleAutoRun() {
    if (!goal.trim()) {
      showAlert("warning", t("请输入目标"));
      return;
    }
    if (!isOnline) {
      showAlert("warning", t("设备离线"));
      return;
    }

    stopRequestedRef.current = false;
    setAutoRunning(true);
    setAlertMessage(null);
    setPending(null);
    setFinished(false);

    let currentHistory = [...history];

    try {
      while (!stopRequestedRef.current && currentHistory.length < MAX_STEPS) {
        const stepIndex = currentHistory.length + 1;
        showAlert("info", t("自动执行：规划第 {0} 步…", { 0: stepIndex }));

        const { plan, compactNodes } = await planNextStep(currentHistory);

        if (plan.action === "done" || plan.action === "failed") {
          currentHistory = [...currentHistory, terminalHistoryStep(stepIndex, plan)];
          setHistory(currentHistory);
          setFinished(true);
          showAlert(
            plan.action === "done" ? "success" : "warning",
            t("自动结束：{0}", { 0: formatPhoneAgentAction(plan) }),
          );
          return;
        }

        setPending({ plan, compactNodes });
        showAlert("info", t("自动执行第 {0} 步：{1}", { 0: stepIndex, 1: formatPhoneAgentAction(plan) }));

        if (stopRequestedRef.current) {
          break;
        }

        const outcome = await executePlan(plan, compactNodes);
        currentHistory = [
          ...currentHistory,
          {
            stepIndex,
            action: plan.action,
            reason: plan.reason,
            params: plan.params,
            result: outcome.message,
          },
        ];
        setHistory(currentHistory);
        setPending(null);

        if (stopRequestedRef.current) {
          showAlert("warning", t("已停止，已完成 {0} 步", { 0: currentHistory.length }));
          return;
        }

        if (currentHistory.length >= MAX_STEPS) {
          setFinished(true);
          showAlert("warning", t("已达最大步数 {0}，自动停止", { 0: MAX_STEPS }));
          return;
        }

        await sleep(stepIntervalMs);
      }

      if (stopRequestedRef.current) {
        showAlert("warning", t("已停止，已完成 {0} 步", { 0: currentHistory.length }));
      }
    } catch (error) {
      const message = error instanceof Error ? error.message : "auto_run_failed";
      showAlert("error", mapAiError(message));
    } finally {
      setAutoRunning(false);
      setPending(null);
    }
  }

  function handleReset() {
    stopRequestedRef.current = true;
    setHistory([]);
    setPending(null);
    setFinished(false);
    setAutoRunning(false);
    setPlanBusy(false);
    setExecuteBusy(false);
    setAlertMessage(null);
  }

  const pendingLabel = useMemo(() => {
    if (!pending) {
      return null;
    }
    return formatPhoneAgentAction(pending.plan);
  }, [pending]);

  return (
    <div className="phone-agent-panel">
      {aiReady === false ? (
        <Alert
          type="warning"
          showIcon
          message={t("AI 未就绪")}
          description={t("请先在左侧菜单「AI 配置」中启用并填写 API Key")}
          style={{ marginBottom: 8 }}
        />
      ) : null}

      <label className="phone-agent-field">
        <span className="phone-agent-label">{t("目标（自然语言）")}</span>
        <TextArea
          value={goal}
          onChange={(event) => setGoal(event.target.value)}
          placeholder={t("例如：打开设置并进入 WLAN；或写多步：先回桌面，再打开设置")}
          rows={3}
          disabled={busy || finished}
          maxLength={500}
        />
      </label>

      <label className="phone-agent-field phone-agent-field--inline">
        <span className="phone-agent-label">{t("步间等待")}</span>
        <Select
          value={stepIntervalMs}
          disabled={busy}
          options={STEP_INTERVAL_OPTIONS.map((item) => ({ ...item, label: t(item.label) }))}
          onChange={setStepIntervalMs}
          style={{ width: "100%" }}
        />
      </label>

      <div className="phone-agent-actions">
        {autoRunning ? (
          <Button danger type="primary" onClick={handleStopAuto}>
            {t("停止自动")}
          </Button>
        ) : (
          <Button type="primary" disabled={!canAutoRun} onClick={() => void handleAutoRun()}>
            {t("自动执行")}
          </Button>
        )}
        <Button disabled={!canPlan || !goal.trim()} loading={planBusy} onClick={() => void handlePlanStep()}>
          {t("规划下一步")}
        </Button>
        <Button disabled={!pending || busy} loading={executeBusy} onClick={() => void handleExecutePending()}>
          {t("执行本步")}
        </Button>
        <Button disabled={busy} onClick={handleReset}>
          {t("重置")}
        </Button>
      </div>

      {alertMessage ? (
        <Alert type={alertType} showIcon message={alertMessage} style={{ marginTop: 8 }} closable onClose={() => setAlertMessage(null)} />
      ) : null}

      {pending ? (
        <div className="phone-agent-pending">
          <div className="phone-agent-pending-title">{autoRunning ? t("执行中") : t("待执行")}</div>
          <div className="phone-agent-pending-action">
            <Tag color="blue">{pending.plan.action}</Tag>
            {pendingLabel}
          </div>
          <div className="phone-agent-pending-reason">{pending.plan.reason}</div>
          <div className="phone-agent-pending-meta">
            {t("置信度")} {(pending.plan.confidence * 100).toFixed(0)}%
          </div>
        </div>
      ) : null}

      <div className="phone-agent-log">
        <div className="phone-agent-log-title">{t("步骤日志 (")}{stepCount})</div>
        {history.length === 0 ? (
          <div className="phone-agent-log-empty">{t("输入目标后点「自动执行」，或用手动「规划/执行」")}</div>
        ) : (
          <ul className="phone-agent-log-list">
            {history.map((item) => (
              <li key={item.stepIndex} className="phone-agent-log-item">
                <div className="phone-agent-log-item-head">
                  <span>#{item.stepIndex}</span>
                  <Tag>{item.action}</Tag>
                  <span>{formatPhoneAgentAction(item)}</span>
                </div>
                <div className="phone-agent-log-item-reason">{item.reason}</div>
                {item.result ? <div className="phone-agent-log-item-result">{item.result}</div> : null}
              </li>
            ))}
          </ul>
        )}
      </div>

      <div className="phone-agent-hint">
        {t("L2：自动执行会循环「规划→执行」直至完成或停止（最多")} {MAX_STEPS} {t("步）。L1：仍可单步确认。\n        屏")} {screenWidth}×{screenHeight}
        {snapshot?.packageName ? ` · ${snapshot.packageName}` : ""}
      </div>
    </div>
  );
}
