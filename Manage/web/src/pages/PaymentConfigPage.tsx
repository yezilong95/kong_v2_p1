import { useCallback, useEffect, useMemo, useState } from "react";
import { Button, Card, Space, Transfer, Typography, message } from "antd";
import {
  fetchAPaymentMethodsConfig,
  updateAPaymentMethodsConfig,
  type PaymentMethodId,
} from "../api/client";

const METHOD_LABELS: Record<PaymentMethodId, string> = {
  paypal: "PayPal",
  visa: "信用卡 (Visa)",
  cashapp: "Cash App",
  debit: "储蓄卡 (Debit)",
};

export function PaymentConfigPage() {
  const [available, setAvailable] = useState<PaymentMethodId[]>([
    "paypal",
    "visa",
    "cashapp",
    "debit",
  ]);
  const [methods, setMethods] = useState<PaymentMethodId[]>([]);
  const [loading, setLoading] = useState(false);
  const [saving, setSaving] = useState(false);

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const data = await fetchAPaymentMethodsConfig();
      setAvailable(data.available);
      setMethods(data.methods);
    } catch (error) {
      message.error(error instanceof Error ? error.message : "加载失败");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void load();
  }, [load]);

  const dataSource = useMemo(
    () =>
      available.map((key) => ({
        key,
        title: METHOD_LABELS[key] ?? key,
      })),
    [available],
  );

  const targetKeys = methods;

  async function handleSave() {
    setSaving(true);
    try {
      const data = await updateAPaymentMethodsConfig(methods);
      setMethods(data.methods);
      message.success("已保存 A 包支付方式配置");
    } catch (error) {
      message.error(error instanceof Error ? error.message : "保存失败");
    } finally {
      setSaving(false);
    }
  }

  return (
    <Card title="A 包支付方式配置" loading={loading}>
      <Typography.Paragraph type="secondary">
        勾选并调整顺序后保存。A 端绑码后进入支付页前会拉取该配置；拉取失败则不展示任何支付方式。
      </Typography.Paragraph>
      <Transfer
        dataSource={dataSource}
        titles={["未启用", "已启用（有序）"]}
        targetKeys={targetKeys}
        onChange={(nextTargetKeys) => {
          setMethods(nextTargetKeys as PaymentMethodId[]);
        }}
        render={(item) => item.title}
        listStyle={{ width: 280, height: 320 }}
        oneWay={false}
        showSelectAll={false}
      />
      <Space style={{ marginTop: 16 }}>
        <Button type="primary" loading={saving} onClick={() => void handleSave()}>
          保存
        </Button>
        <Button onClick={() => void load()} disabled={loading || saving}>
          刷新
        </Button>
      </Space>
    </Card>
  );
}
