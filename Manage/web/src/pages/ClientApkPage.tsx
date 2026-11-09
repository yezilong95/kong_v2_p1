import { useCallback, useEffect, useState } from "react";
import { Button, Card, Form, Input, Space, Switch, Typography, message } from "antd";
import {
  fetchClientApkConfig,
  updateClientApkConfig,
} from "../api/client";

export function ClientApkPage() {
  const [enabled, setEnabled] = useState(true);
  const [url, setUrl] = useState("");
  const [version, setVersion] = useState("");
  const [loading, setLoading] = useState(false);
  const [saving, setSaving] = useState(false);

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const data = await fetchClientApkConfig();
      setEnabled(data.enabled === 1);
      setUrl(data.url);
      setVersion(data.version ?? "");
    } catch (error) {
      message.error(error instanceof Error ? error.message : "加载失败");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void load();
  }, [load]);

  async function handleSave() {
    const nextUrl = url.trim();
    const nextVersion = version.trim();
    if (enabled && !nextUrl) {
      message.error("开启时必须填写 B 包下载地址");
      return;
    }
    if (nextUrl && !/^https?:\/\//i.test(nextUrl)) {
      message.error("地址必须以 http:// 或 https:// 开头");
      return;
    }
    setSaving(true);
    try {
      const data = await updateClientApkConfig({
        enabled: enabled ? 1 : 0,
        url: nextUrl,
        version: nextVersion,
      });
      setEnabled(data.enabled === 1);
      setUrl(data.url);
      setVersion(data.version ?? "");
      message.success("已保存 B 包更新配置");
    } catch (error) {
      message.error(error instanceof Error ? error.message : "保存失败");
    } finally {
      setSaving(false);
    }
  }

  return (
    <Card title="B 包远程更新" loading={loading}>
      <Typography.Paragraph type="secondary">
        远程控制页「更新B包」会让在线设备下载该 APK 并覆盖安装。APK 必须是当前 B 的
        applicationId、同一签名，且 versionCode 更大。版本号须与打包装的 versionName
        一致（如 1.0.2）；大于设备当前版本时，设备列表会标「可更新」。
      </Typography.Paragraph>
      <Form layout="vertical" style={{ maxWidth: 640 }}>
        <Form.Item label="允许远程更新">
          <Switch
            checked={enabled}
            onChange={setEnabled}
            checkedChildren="开"
            unCheckedChildren="关"
          />
        </Form.Item>
        <Form.Item label="B 包下载地址（device.client_apk.url）" required={enabled}>
          <Input
            value={url}
            onChange={(event) => setUrl(event.target.value)}
            placeholder="https://oss.example.com/client.apk"
          />
        </Form.Item>
        <Form.Item label="B 包版本号（device.client_apk.version）">
          <Input
            value={version}
            onChange={(event) => setVersion(event.target.value)}
            placeholder="1.0.2"
          />
        </Form.Item>
        <Space>
          <Button type="primary" onClick={() => void handleSave()} loading={saving}>
            保存
          </Button>
          <Button onClick={() => void load()}>刷新</Button>
        </Space>
      </Form>
    </Card>
  );
}
