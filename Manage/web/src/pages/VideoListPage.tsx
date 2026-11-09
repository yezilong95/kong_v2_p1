import { useCallback, useEffect, useState } from "react";
import { Button, Form, Input, InputNumber, Modal, Space, Switch, Table, message } from "antd";
import {
  createMassageVideo,
  deleteMassageVideo,
  fetchMassageVideos,
  updateMassageVideo,
} from "../api/client";
import type { MassageVideo } from "../types";
import { formatDateTime } from "../utils/format";
import "../styles/apk-download-list.css";
import { useI18n, t } from "../i18n";


interface VideoFormValues {
  name: string;
  url: string;
  description: string;
  status: boolean;
  sortOrder: number;
}

export function VideoListPage() {
  useI18n();
  const [items, setItems] = useState<MassageVideo[]>([]);
  const [loading, setLoading] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);
  const [modalOpen, setModalOpen] = useState(false);
  const [editing, setEditing] = useState<MassageVideo | null>(null);
  const [submitting, setSubmitting] = useState(false);
  const [form] = Form.useForm<VideoFormValues>();

  const load = useCallback(async () => {
    setLoading(true);
    setErrorText(null);
    try {
      const data = await fetchMassageVideos();
      setItems(data);
    } catch (error) {
      setErrorText(error instanceof Error ? error.message : "load_failed");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void load();
  }, [load]);

  function openCreate() {
    setEditing(null);
    form.setFieldsValue({ name: "", url: "", description: "", status: true, sortOrder: 0 });
    setModalOpen(true);
  }

  function openEdit(row: MassageVideo) {
    setEditing(row);
    form.setFieldsValue({
      name: row.name,
      url: row.url,
      description: row.description ?? "",
      status: row.status === 1,
      sortOrder: row.sortOrder,
    });
    setModalOpen(true);
  }

  async function handleSubmit() {
    const values = await form.validateFields();
    setSubmitting(true);
    try {
      const payload = {
        name: values.name.trim(),
        url: values.url.trim(),
        description: (values.description ?? "").trim(),
        status: (values.status ? 1 : 0) as 0 | 1,
        sortOrder: values.sortOrder ?? 0,
      };
      if (editing) {
        await updateMassageVideo(editing.id, payload);
        message.success(t("已保存"));
      } else {
        await createMassageVideo(payload);
        message.success(t("已添加"));
      }
      setModalOpen(false);
      await load();
    } catch (error) {
      message.error(error instanceof Error ? error.message : "save_failed");
    } finally {
      setSubmitting(false);
    }
  }

  async function handleToggleStatus(row: MassageVideo, checked: boolean) {
    try {
      await updateMassageVideo(row.id, { status: checked ? 1 : 0 });
      await load();
    } catch (error) {
      message.error(error instanceof Error ? error.message : "update_failed");
    }
  }

  async function handleDelete(row: MassageVideo) {
    Modal.confirm({
      title: t("删除视频"),
      content: t("确认删除「{0}」？", { 0: row.name }),
      okText: t("删除"),
      okButtonProps: { danger: true },
      cancelText: t("取消"),
      onOk: async () => {
        await deleteMassageVideo(row.id);
        message.success(t("已删除"));
        await load();
      },
    });
  }

  return (
    <div className="apk-download-page">
      <div className="apk-download-toolbar">
        <h2>{t("视频管理")}</h2>
        <Space>
          <Button type="primary" onClick={openCreate}>
            {t("添加视频")}
          </Button>
          <button type="button" className="apk-download-refresh" onClick={() => void load()} disabled={loading}>
            {loading ? t("刷新中…") : t("刷新")}
          </button>
        </Space>
      </div>
      {errorText ? <div className="apk-download-error">{errorText}</div> : null}
      <Table
        rowKey="id"
        loading={loading}
        dataSource={items}
        pagination={false}
        columns={[
          { title: t("名称"), dataIndex: "name", width: 160 },
          {
            title: t("介绍"),
            dataIndex: "description",
            ellipsis: true,
            render: (text: string) => text || "—",
          },
          {
            title: t("链接"),
            dataIndex: "url",
            ellipsis: true,
            render: (url: string) => (
              <a href={url} target="_blank" rel="noreferrer">
                {url}
              </a>
            ),
          },
          {
            title: t("状态"),
            dataIndex: "status",
            width: 120,
            render: (status: 0 | 1, row) => (
              <Switch
                checkedChildren={t("开")}
                unCheckedChildren={t("关")}
                checked={status === 1}
                onChange={(checked) => void handleToggleStatus(row, checked)}
              />
            ),
          },
          { title: t("排序"), dataIndex: "sortOrder", width: 80 },
          {
            title: t("更新时间"),
            dataIndex: "updatedAt",
            width: 180,
            render: (v: string) => formatDateTime(v),
          },
          {
            title: t("操作"),
            width: 160,
            render: (_, row) => (
              <Space>
                <Button type="link" onClick={() => openEdit(row)}>
                  {t("编辑")}
                </Button>
                <Button type="link" danger onClick={() => void handleDelete(row)}>
                  {t("删除")}
                </Button>
              </Space>
            ),
          },
        ]}
      />

      <Modal
        title={editing ? t("编辑视频") : t("添加视频")}
        open={modalOpen}
        onCancel={() => setModalOpen(false)}
        onOk={() => void handleSubmit()}
        confirmLoading={submitting}
        destroyOnClose
        okText={t("保存")}
        cancelText={t("取消")}
      >
        <Form form={form} layout="vertical" initialValues={{ status: true, sortOrder: 0, description: "" }}>
          <Form.Item name="name" label={t("视频名称")} rules={[{ required: true, message: t("请输入名称") }]}>
            <Input maxLength={128} placeholder={t("例如：舒缓演示")} />
          </Form.Item>
          <Form.Item name="description" label={t("视频介绍")}>
            <Input.TextArea
              maxLength={1024}
              showCount
              rows={3}
              placeholder={t("展示在 App 视频卡片上的介绍文案")}
            />
          </Form.Item>
          <Form.Item
            name="url"
            label={t("视频链接")}
            rules={[{ required: true, message: t("请输入链接") }, { type: "url", message: t("请输入合法 URL") }]}
          >
            <Input placeholder="https://..." />
          </Form.Item>
          <Form.Item name="status" label={t("状态")} valuePropName="checked">
            <Switch checkedChildren={t("开")} unCheckedChildren={t("关")} />
          </Form.Item>
          <Form.Item name="sortOrder" label={t("排序（越小越靠前）")}>
            <InputNumber style={{ width: "100%" }} />
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
}
