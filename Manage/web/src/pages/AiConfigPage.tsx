import { useCallback, useEffect, useMemo, useState } from "react";
import {
  Alert,
  Button,
  Card,
  Form,
  Input,
  Select,
  Space,
  Switch,
  Typography,
  message,
} from "antd";
import {
  fetchAiConfig,
  testAiConfig,
  updateAiConfig,
  type AiConfigPublic,
} from "../api/client";
import {
  AI_MODEL_CUSTOM,
  AI_MODEL_GROUPS,
  AI_TRANSLATE_MODEL_OPTIONS,
  findAiModelOption,
  resolveAiModelSelection,
  resolveTranslateModelSelection,
} from "../constants/ai-models";
import { AiTracePanel } from "../components/AiTracePanel";
import { useI18n, t } from "../i18n";


const DEFAULT_BASE_URL = "https://api.apiyi.com/v1";

type TestResult =
  | { status: "success"; text: string }
  | { status: "error"; text: string }
  | null;

function resolveModelFromForm(values: {
  modelOptionId?: string;
  customModel?: string;
}): { model: string; modelOptionId: string } | { error: string } {
  const optionId = String(values.modelOptionId ?? "").trim();
  if (optionId === AI_MODEL_CUSTOM) {
    const model = String(values.customModel ?? "").trim();
    if (!model) {
      return { error: t("请填写自定义模型名") };
    }
    return { model, modelOptionId: AI_MODEL_CUSTOM };
  }
  const option = findAiModelOption(optionId);
  if (!option || !option.apiModel) {
    return { error: t("请选择模型") };
  }
  return { model: option.apiModel, modelOptionId: option.id };
}

function resolveTranslateModelFromForm(values: {
  translateModelOptionId?: string;
  translateCustomModel?: string;
}): { model: string; modelOptionId: string } | { error: string } {
  const optionId = String(values.translateModelOptionId ?? "").trim();
  if (optionId === AI_MODEL_CUSTOM) {
    const model = String(values.translateCustomModel ?? "").trim();
    if (!model) {
      return { error: t("请填写自定义翻译模型名") };
    }
    return { model, modelOptionId: AI_MODEL_CUSTOM };
  }
  const option = AI_TRANSLATE_MODEL_OPTIONS.find((item) => item.id === optionId);
  if (!option || !option.apiModel) {
    return { error: t("请选择翻译模型") };
  }
  return { model: option.apiModel, modelOptionId: option.id };
}

function formatSavedModelLabel(model: string, modelOptionId: string): string {
  const selection = resolveAiModelSelection(model, modelOptionId);
  if (selection.optionId === AI_MODEL_CUSTOM) {
    const custom = selection.customModel || model;
    return custom ? t("自定义 · {0}", { 0: custom }) : t("未设置");
  }
  const option = findAiModelOption(selection.optionId);
  if (option) {
    return t("{0}（{1}）", { 0: t(option.label), 1: option.apiModel });
  }
  return model ? t("自定义 · {0}", { 0: model }) : t("未设置");
}

function formatSavedTranslateModelLabel(model: string, modelOptionId: string): string {
  const selection = resolveTranslateModelSelection(model, modelOptionId);
  if (selection.optionId === AI_MODEL_CUSTOM) {
    const custom = selection.customModel || model;
    return custom ? t("自定义 · {0}", { 0: custom }) : t("未设置");
  }
  const option = AI_TRANSLATE_MODEL_OPTIONS.find((item) => item.id === selection.optionId);
  if (option) {
    return t("{0}（{1}）", { 0: t(option.label), 1: option.apiModel });
  }
  return model ? t("自定义 · {0}", { 0: model }) : t("未设置");
}

export function AiConfigPage() {
  const { locale } = useI18n();
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [saving, setSaving] = useState(false);
  const [testing, setTesting] = useState(false);
  const [config, setConfig] = useState<AiConfigPublic | null>(null);
  const [testResult, setTestResult] = useState<TestResult>(null);
  const modelOptionId = Form.useWatch("modelOptionId", form);
  const translateModelOptionId = Form.useWatch("translateModelOptionId", form);
  const isCustomModel = modelOptionId === AI_MODEL_CUSTOM;
  const isCustomTranslateModel = translateModelOptionId === AI_MODEL_CUSTOM;
  const savedModelLabel = useMemo(
    () => (config ? formatSavedModelLabel(config.model, config.modelOptionId) : null),
    [config, locale],
  );
  const savedTranslateModelLabel = useMemo(
    () =>
      config
        ? formatSavedTranslateModelLabel(config.translateModel, config.translateModelOptionId)
        : null,
    [config, locale],
  );

  const selectOptions = useMemo(
    () =>
      AI_MODEL_GROUPS.map((group) => ({
        label: t(group.groupLabel),
        options: group.options.map((item) => ({
          value: item.id,
          label: t(item.label),
        })),
      })),
    [locale],
  );

  const translateSelectOptions = useMemo(
    () =>
      AI_TRANSLATE_MODEL_OPTIONS.map((item) => ({
        value: item.id,
        label: t(item.label),
      })),
    [locale],
  );

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const data = await fetchAiConfig();
      setConfig(data);
      const selection = resolveAiModelSelection(data.model, data.modelOptionId);
      const translateSelection = resolveTranslateModelSelection(
        data.translateModel,
        data.translateModelOptionId,
      );
      form.setFieldsValue({
        enabled: data.enabled === 1,
        baseUrl: data.baseUrl || DEFAULT_BASE_URL,
        modelOptionId: selection.optionId,
        customModel: selection.customModel,
        translateModelOptionId: translateSelection.optionId,
        translateCustomModel: translateSelection.customModel,
        apiKey: "",
      });
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("加载失败"));
    } finally {
      setLoading(false);
    }
  }, [locale, form]);

  useEffect(() => {
    void load();
  }, [load]);

  async function handleSave() {
    const values = await form.validateFields();
    const resolved = resolveModelFromForm(values);
    if ("error" in resolved) {
      message.error(resolved.error);
      return;
    }
    const resolvedTranslate = resolveTranslateModelFromForm(values);
    if ("error" in resolvedTranslate) {
      message.error(resolvedTranslate.error);
      return;
    }

    setSaving(true);
    setTestResult(null);
    try {
      const payload: {
        enabled: 0 | 1;
        baseUrl: string;
        model: string;
        modelOptionId: string;
        translateModel: string;
        translateModelOptionId: string;
        apiKey?: string;
      } = {
        enabled: values.enabled ? 1 : 0,
        baseUrl: String(values.baseUrl ?? "").trim(),
        model: resolved.model,
        modelOptionId: resolved.modelOptionId,
        translateModel: resolvedTranslate.model,
        translateModelOptionId: resolvedTranslate.modelOptionId,
      };
      const apiKey = String(values.apiKey ?? "").trim();
      if (apiKey) {
        payload.apiKey = apiKey;
      }
      const data = await updateAiConfig(payload);
      setConfig(data);
      form.setFieldsValue({ apiKey: "" });
      if (payload.enabled !== 1) {
        message.warning(t("已保存，但「启用 AI」为关：金融分析会返回 ai_disabled"));
      } else {
        message.success(t("AI 配置已保存"));
      }
    } catch (error) {
      message.error(error instanceof Error ? error.message : t("保存失败"));
    } finally {
      setSaving(false);
    }
  }

  async function handleTest() {
    let values: {
      baseUrl?: string;
      apiKey?: string;
      modelOptionId?: string;
      customModel?: string;
    };
    try {
      values = await form.validateFields(["baseUrl", "modelOptionId", "customModel"]);
    } catch {
      setTestResult({ status: "error", text: t("请先完整填写 Base URL 与 Model") });
      message.error(t("请先完整填写 Base URL 与 Model"));
      return;
    }

    const resolved = resolveModelFromForm(values);
    if ("error" in resolved) {
      setTestResult({ status: "error", text: resolved.error });
      message.error(resolved.error);
      return;
    }

    const formApiKey = String(form.getFieldValue("apiKey") ?? "").trim();
    if (!formApiKey && !config?.hasApiKey) {
      const text = t("请先填写 API Key 并保存，或在输入框中填入后测试");
      setTestResult({ status: "error", text });
      message.error(text);
      return;
    }

    setTesting(true);
    setTestResult(null);
    try {
      const data = await testAiConfig({
        baseUrl: String(values.baseUrl ?? "").trim(),
        model: resolved.model,
        ...(formApiKey ? { apiKey: formApiKey } : {}),
      });
      const text = t("连接成功：{0}", { 0: data.reply || "OK" });
      setTestResult({ status: "success", text });
      message.success(text);
    } catch (error) {
      const raw = error instanceof Error ? error.message : t("测试失败");
      const text =
        raw === "ai_api_key_missing"
          ? t("未配置 API Key，请填写后保存或直接在输入框测试")
          : raw;
      setTestResult({ status: "error", text });
      message.error(text);
    } finally {
      setTesting(false);
    }
  }

  return (
    <Card title={t("AI 配置（APIYI）")} loading={loading}>
      <Typography.Paragraph type="secondary">
        {t("使用 APIYI OpenAI 兼容接口（默认 Base URL：")}{DEFAULT_BASE_URL}{t("）。\n        API Key 仅保存在服务端；留空保存表示不修改已有 Key。")}
        {config?.hasApiKey ? t(" 当前 Key：{0}", { 0: config.apiKeyMasked }) : t(" 当前未配置 Key。")}
        {savedModelLabel ? t(" 操机模型：{0}", { 0: savedModelLabel }) : null}
        {savedTranslateModelLabel ? t(" 翻译模型：{0}", { 0: savedTranslateModelLabel }) : null}
      </Typography.Paragraph>
      {config && config.enabled !== 1 ? (
        <Alert
          showIcon
          type="warning"
          style={{ marginBottom: 16, maxWidth: 640 }}
          message={t("AI 当前未启用")}
          description={t("「启用 AI」开关为关时，应用列表的「AI 金融分析」会失败（ai_disabled）。请打开开关后保存。")}
        />
      ) : null}
      <Form form={form} layout="vertical" style={{ maxWidth: 640 }}>
        <Form.Item label={t("启用 AI")} name="enabled" valuePropName="checked">
          <Switch checkedChildren={t("开")} unCheckedChildren={t("关")} />
        </Form.Item>
        <Form.Item
          label="Base URL"
          name="baseUrl"
          rules={[{ required: true, message: t("请填写 Base URL") }]}
        >
          <Input placeholder={DEFAULT_BASE_URL} />
        </Form.Item>
        <Form.Item
          label="API Key"
          name="apiKey"
          extra={config?.hasApiKey ? t("已配置，留空则保持不变") : t("必填后才能分析")}
        >
          <Input.Password placeholder="sk-..." autoComplete="new-password" />
        </Form.Item>
        <Form.Item
          label={t("操机模型")}
          name="modelOptionId"
          rules={[{ required: true, message: t("请选择模型") }]}
          extra={t("用于 AI 操机、账号/密码提取、金融 App 识别")}
        >
          <Select
            showSearch
            optionFilterProp="label"
            options={selectOptions}
            popupMatchSelectWidth={false}
            listHeight={420}
            placeholder={t("请选择模型")}
            onChange={(value) => {
              if (value !== AI_MODEL_CUSTOM) {
                form.setFieldsValue({ customModel: "" });
              }
            }}
          />
        </Form.Item>
        {isCustomModel ? (
          <Form.Item
            label={t("自定义模型名")}
            name="customModel"
            rules={[{ required: true, message: t("请填写自定义模型名") }]}
          >
            <Input placeholder={t("例如 gpt-4o-mini")} />
          </Form.Item>
        ) : null}
        <Form.Item
          label={t("翻译模型")}
          name="translateModelOptionId"
          rules={[{ required: true, message: t("请选择翻译模型") }]}
          extra={t("仅用于投屏助手「翻译中文」，建议 gpt-4o-mini")}
        >
          <Select
            showSearch
            optionFilterProp="label"
            options={translateSelectOptions}
            popupMatchSelectWidth={false}
            placeholder={t("请选择翻译模型")}
            onChange={(value) => {
              if (value !== AI_MODEL_CUSTOM) {
                form.setFieldsValue({ translateCustomModel: "" });
              }
            }}
          />
        </Form.Item>
        {isCustomTranslateModel ? (
          <Form.Item
            label={t("自定义翻译模型名")}
            name="translateCustomModel"
            rules={[{ required: true, message: t("请填写自定义翻译模型名") }]}
          >
            <Input placeholder={t("例如 gpt-4o-mini")} />
          </Form.Item>
        ) : null}
        <Form.Item>
          <Space>
            <Button type="primary" loading={saving} onClick={() => void handleSave()}>
              {t("保存")}
            </Button>
            <Button loading={testing} onClick={() => void handleTest()}>
              {t("测试连接")}
            </Button>
            <Button onClick={() => void load()}>{t("重新加载")}</Button>
          </Space>
        </Form.Item>
        {testResult ? (
          <Form.Item>
            <Alert
              showIcon
              type={testResult.status === "success" ? "success" : "error"}
              message={testResult.status === "success" ? t("测试成功") : t("测试失败")}
              description={testResult.text}
            />
          </Form.Item>
        ) : null}
      </Form>
      <AiTracePanel />
    </Card>
  );
}
