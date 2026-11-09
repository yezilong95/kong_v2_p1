import { useCallback, useEffect, useState } from "react";
import {
  Button,
  Card,
  Form,
  Input,
  Popconfirm,
  Space,
  Switch,
  Table,
  Typography,
  message,
} from "antd";
import {
  createPeerServer,
  deletePeerServer,
  fetchDeviceTransferConfig,
  fetchPeerServers,
  updateDeviceTransferConfig,
  updatePeerServer,
  type PeerServer,
} from "../api/client";
import { useI18n, t } from "../i18n";


interface PeerFormValues {
  name: string;
  apiBaseUrl: string;
  wsBaseUrl: string;
  wsScreenBaseUrl: string;
  transferSecret: string;
  enabled: boolean;
}

export function PeerServersPage() {
  const { locale } = useI18n();
  const [enabled, setEnabled] = useState(false);
  const [secret, setSecret] = useState("");
  const [peers, setPeers] = useState<PeerServer[]>([]);
  const [loading, setLoading] = useState(false);
  const [saving, setSaving] = useState(false);
  const [form] = Form.useForm<PeerFormValues>();
  const [editingId, setEditingId] = useState<string | null>(null);

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const [config, peerData] = await Promise.all([
        fetchDeviceTransferConfig(),
        fetchPeerServers(),
      ]);
      setEnabled(config.enabled === 1);
      setSecret(config.secret);
      setPeers(peerData.peers);
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("加载失败"));
    } finally {
      setLoading(false);
    }
  }, [locale]);

  useEffect(() => {
    void load();
  }, [load]);

  async function saveLocalConfig() {
    const nextSecret = secret.trim();
    if (!nextSecret) {
      message.error(t("本机接收密钥不能为空"));
      return;
    }
    setSaving(true);
    try {
      const data = await updateDeviceTransferConfig({
        enabled: enabled ? 1 : 0,
        secret: nextSecret,
      });
      setEnabled(data.enabled === 1);
      setSecret(data.secret);
      message.success(t("已保存转移开关和本机密钥"));
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("保存失败"));
    } finally {
      setSaving(false);
    }
  }

  function startEdit(peer: PeerServer) {
    setEditingId(peer.id);
    form.setFieldsValue({
      name: peer.name,
      apiBaseUrl: peer.apiBaseUrl,
      wsBaseUrl: peer.wsBaseUrl,
      wsScreenBaseUrl: peer.wsScreenBaseUrl,
      transferSecret: peer.transferSecret,
      enabled: peer.enabled === 1,
    });
  }

  function resetForm() {
    setEditingId(null);
    form.resetFields();
    form.setFieldsValue({ enabled: true });
  }

  async function submitPeer(values: PeerFormValues) {
    const payload = {
      name: values.name.trim(),
      apiBaseUrl: values.apiBaseUrl.trim(),
      wsBaseUrl: values.wsBaseUrl.trim(),
      wsScreenBaseUrl: values.wsScreenBaseUrl.trim(),
      transferSecret: values.transferSecret.trim(),
      enabled: values.enabled ? (1 as const) : (0 as const),
    };
    try {
      if (editingId) {
        await updatePeerServer(editingId, payload);
        message.success(t("对端已更新"));
      } else {
        await createPeerServer(payload);
        message.success(t("对端已添加"));
      }
      resetForm();
      await load();
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("保存对端失败"));
    }
  }

  async function handleDelete(id: string) {
    try {
      await deletePeerServer(id);
      if (editingId === id) {
        resetForm();
      }
      await load();
      message.success(t("已删除"));
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("删除失败"));
    }
  }

  return (
    <Space direction="vertical" size={16} style={{ width: "100%" }}>
      <Card title={t("设备转移")} loading={loading}>
        <Typography.Paragraph type="secondary">
          {t("开关用 0/1。开启后可把在线设备迁到对端面板。本机密钥给对端「调用本机 accept-transfer」用；对端列表里的密钥是调用对方时带的。")}
        </Typography.Paragraph>
        <Space wrap>
          <span>{t("启用转移")}</span>
          <Switch checked={enabled} onChange={setEnabled} />
          <Input.Password
            placeholder={t("本机接收密钥")}
            value={secret}
            onChange={(event) => setSecret(event.target.value)}
            style={{ width: 280 }}
          />
          <Button type="primary" loading={saving} onClick={() => void saveLocalConfig()}>
            {t("保存开关/密钥")}
          </Button>
        </Space>
      </Card>

      <Card title={editingId ? t("编辑对端服务器") : t("添加对端服务器")}>
        <Form<PeerFormValues>
          form={form}
          layout="vertical"
          initialValues={{ enabled: true }}
          onFinish={(values) => void submitPeer(values)}
        >
          <Form.Item name="name" label={t("名称")} rules={[{ required: true, message: t("填写名称") }]}>
            <Input placeholder={t("td2 香港")} />
          </Form.Item>
          <Form.Item
            name="apiBaseUrl"
            label="API"
            rules={[{ required: true, message: t("填写 https://td2.example.com") }]}
          >
            <Input placeholder="https://td2.example.com" />
          </Form.Item>
          <Form.Item
            name="wsBaseUrl"
            label={t("控制 WS")}
            rules={[{ required: true, message: t("填写 wss://td2.example.com/ws/device") }]}
          >
            <Input placeholder="wss://td2.example.com/ws/device" />
          </Form.Item>
          <Form.Item
            name="wsScreenBaseUrl"
            label={t("投屏 WS")}
            rules={[{ required: true, message: t("填写 wss://td2.example.com/ws/device/screen") }]}
          >
            <Input placeholder="wss://td2.example.com/ws/device/screen" />
          </Form.Item>
          <Form.Item
            name="transferSecret"
            label={t("对端接收密钥")}
            rules={[{ required: true, message: t("与对端本机密钥一致") }]}
          >
            <Input.Password />
          </Form.Item>
          <Form.Item name="enabled" label={t("启用")} valuePropName="checked">
            <Switch />
          </Form.Item>
          <Space>
            <Button type="primary" htmlType="submit">
              {editingId ? t("更新") : t("添加")}
            </Button>
            {editingId ? (
              <Button onClick={resetForm}>{t("取消编辑")}</Button>
            ) : null}
          </Space>
        </Form>
      </Card>

      <Card title={t("对端列表")}>
        <Table<PeerServer>
          rowKey="id"
          dataSource={peers}
          pagination={false}
          columns={[
            { title: t("名称"), dataIndex: "name" },
            { title: "API", dataIndex: "apiBaseUrl", ellipsis: true },
            {
              title: t("启用"),
              dataIndex: "enabled",
              width: 80,
              render: (value: 0 | 1) => (value === 1 ? t("开") : t("关")),
            },
            {
              title: t("操作"),
              width: 160,
              render: (_, peer) => (
                <Space>
                  <Button type="link" onClick={() => startEdit(peer)}>
                    {t("编辑")}
                  </Button>
                  <Popconfirm title={t("删除该对端？")} onConfirm={() => void handleDelete(peer.id)}>
                    <Button type="link" danger>
                      {t("删除")}
                    </Button>
                  </Popconfirm>
                </Space>
              ),
            },
          ]}
        />
      </Card>
    </Space>
  );
}
