/** AI 模型下拉：展示文案与截图一致；apiModel 为发往 APIYI 的 model 字段 */

export const AI_MODEL_CUSTOM = "__custom__";

export interface AiModelOption {
  id: string;
  /** 发往 APIYI 的 model */
  apiModel: string;
  label: string;
}

export interface AiModelGroup {
  groupLabel: string;
  options: AiModelOption[];
}

export const AI_MODEL_GROUPS: AiModelGroup[] = [
  {
    groupLabel: "—— 控手机 (可选这些) ——",
    options: [
      {
        id: "phone-zhipu-autoglm-phone",
        apiModel: "autoglm-phone",
        label: "【控手机】智谱 · AutoGLM Phone (看图 + do 动作)",
      },
      {
        id: "phone-zhipu-glm-4-plus",
        apiModel: "glm-4-plus",
        label: "【控手机】智谱 · GLM-4-Plus (工具调用)",
      },
      {
        id: "phone-zhipu-glm-4-flash",
        apiModel: "glm-4-flash",
        label: "【控手机】智谱 · GLM-4-Flash (工具 · 便宜)",
      },
      {
        id: "phone-zhipu-glm-5.2",
        apiModel: "glm-5.2",
        label: "【控手机】智谱 · GLM-5.2 (识图+工具)",
      },
      {
        id: "phone-openai-gpt-4o",
        apiModel: "gpt-4o",
        label: "【控手机】OpenAI · GPT-4o (识图+工具)",
      },
      {
        id: "phone-openai-gpt-4.1",
        apiModel: "gpt-4.1",
        label: "【控手机】OpenAI · GPT-4.1 (识图+工具)",
      },
      {
        id: "phone-openai-gpt-5.5-pro",
        apiModel: "gpt-5.5-pro",
        label: "【控手机】OpenAI · GPT-5.5 Pro (识图+工具)",
      },
      {
        id: "phone-openai-gpt-5.5",
        apiModel: "gpt-5.5",
        label: "【控手机】OpenAI · GPT-5.5 (识图+工具)",
      },
      {
        id: "phone-openrouter-gpt-4.1",
        apiModel: "openai/gpt-4.1",
        label: "【控手机】OpenRouter · GPT-4.1",
      },
      {
        id: "phone-openrouter-claude-sonnet-4",
        apiModel: "anthropic/claude-sonnet-4",
        label: "【控手机】OpenRouter · Claude Sonnet 4",
      },
      {
        id: "phone-openrouter-gemini-2.5-flash",
        apiModel: "google/gemini-2.5-flash",
        label: "【控手机】OpenRouter · Gemini 2.5 Flash",
      },
      {
        id: "phone-xai-grok-4.6",
        apiModel: "grok-4.6",
        label: "【控手机】xAI · Grok 4.6 (识图+工具)",
      },
      {
        id: "phone-xai-grok-4.5",
        apiModel: "grok-4.5",
        label: "【控手机】xAI · Grok 4.5 (识图+工具)",
      },
      {
        id: "phone-xai-grok-4.3",
        apiModel: "grok-4.3",
        label: "【控手机】xAI · Grok 4.3 (识图+工具)",
      },
      {
        id: "phone-openai-gpt-4o-mini",
        apiModel: "gpt-4o-mini",
        label: "【控手机】OpenAI · GPT-4o mini",
      },
      {
        id: "phone-openrouter-gpt-4o-mini",
        apiModel: "openai/gpt-4o-mini",
        label: "【控手机】OpenRouter · GPT-4o mini",
      },
    ],
  },
  {
    groupLabel: "—— 做注入 / 仅识图 (勿用来点屏幕) ——",
    options: [
      {
        id: "vision-openrouter-ui-tars",
        apiModel: "bytedance-research/ui-tars",
        label: "【未对接】OpenRouter · UI-TARS (动作格式未解析，勿控机)",
      },
      {
        id: "vision-zhipu-glm-4v-flash",
        apiModel: "glm-4v-flash",
        label: "【仅识图】智谱 · GLM-4V Flash (不可控机)",
      },
      {
        id: "vision-zhipu-glm-4v-plus",
        apiModel: "glm-4v-plus",
        label: "【仅识图】智谱 · GLM-4V-Plus (不可控机)",
      },
      {
        id: "vision-zhipu-glm-4.1v-thinking",
        apiModel: "glm-4.1v-thinking",
        label: "【仅识图】智谱 · GLM-4.1V Thinking (不可控机)",
      },
      {
        id: "vision-openrouter-qwen25-vl-72b",
        apiModel: "qwen/qwen2.5-vl-72b-instruct",
        label: "OpenRouter · Qwen2.5-VL-72B (做注入)",
      },
      {
        id: "vision-dashscope-qwen-vl-max",
        apiModel: "qwen-vl-max",
        label: "通义 · Qwen-VL-Max (做注入)",
      },
      {
        id: "vision-siliconflow-qwen25-vl-72b",
        apiModel: "Qwen/Qwen2.5-VL-72B-Instruct",
        label: "硅基流动 · Qwen2.5-VL-72B (做注入)",
      },
    ],
  },
  {
    groupLabel: "—— 通用对话 ——",
    options: [
      {
        id: "chat-deepseek-v4-flash",
        apiModel: "deepseek-v4-flash",
        label: "DeepSeek · V4 Flash",
      },
      {
        id: "chat-deepseek-v4-pro",
        apiModel: "deepseek-v4-pro",
        label: "DeepSeek · V4 Pro (不可控机)",
      },
      {
        id: "chat-deepseek-chat",
        apiModel: "deepseek-chat",
        label: "DeepSeek · Chat->V4 Flash",
      },
      {
        id: "chat-deepseek-reasoner",
        apiModel: "deepseek-reasoner",
        label: "DeepSeek · Reasoner->V4 Pro",
      },
      {
        id: "chat-openrouter-deepseek-r1",
        apiModel: "deepseek/deepseek-r1",
        label: "OpenRouter · DeepSeek R1 (不可控机)",
      },
      {
        id: "chat-openai-gpt-4.1-mini",
        apiModel: "gpt-4.1-mini",
        label: "OpenAI · GPT-4.1 mini",
      },
      {
        id: "chat-xai-grok-4.6",
        apiModel: "grok-4.6",
        label: "xAI · Grok 4.6",
      },
      {
        id: "chat-xai-grok-4.5",
        apiModel: "grok-4.5",
        label: "xAI · Grok 4.5",
      },
      {
        id: "chat-xai-grok-4.3",
        apiModel: "grok-4.3",
        label: "xAI · Grok 4.3",
      },
      {
        id: "chat-moonshot-kimi",
        apiModel: "moonshot-v1-128k",
        label: "月之暗面 · Kimi",
      },
      {
        id: "chat-moonshot-kimi-latest",
        apiModel: "kimi-latest",
        label: "月之暗面 · kimi-latest",
      },
      {
        id: "chat-siliconflow-deepseek-v3",
        apiModel: "deepseek-ai/DeepSeek-V3",
        label: "硅基流动 · DeepSeek-V3",
      },
      {
        id: "chat-dashscope-qwen-plus",
        apiModel: "qwen-plus",
        label: "通义千问 · Qwen-Plus",
      },
      {
        id: "chat-dashscope-qwen-max",
        apiModel: "qwen-max",
        label: "通义千问 · Qwen-Max",
      },
    ],
  },
  {
    groupLabel: "—— 其它 ——",
    options: [
      {
        id: AI_MODEL_CUSTOM,
        apiModel: "",
        label: "自定义...",
      },
    ],
  },
];

export const AI_MODEL_FLAT: AiModelOption[] = AI_MODEL_GROUPS.flatMap(
  (group) => group.options,
);

export const AI_TRANSLATE_MODEL_OPTIONS: AiModelOption[] = [
  {
    id: "translate-openai-gpt-4o-mini",
    apiModel: "gpt-4o-mini",
    label: "OpenAI · GPT-4o mini（推荐）",
  },
  {
    id: "translate-openai-gpt-4.1-mini",
    apiModel: "gpt-4.1-mini",
    label: "OpenAI · GPT-4.1 mini",
  },
  {
    id: "translate-openrouter-gpt-4o-mini",
    apiModel: "openai/gpt-4o-mini",
    label: "OpenRouter · GPT-4o mini",
  },
  {
    id: "translate-deepseek-v4-flash",
    apiModel: "deepseek-v4-flash",
    label: "DeepSeek · V4 Flash",
  },
  {
    id: AI_MODEL_CUSTOM,
    apiModel: "",
    label: "自定义...",
  },
];

export function findAiModelOption(idOrApiModel: string): AiModelOption | undefined {
  const key = idOrApiModel.trim();
  if (!key) {
    return undefined;
  }
  return (
    AI_TRANSLATE_MODEL_OPTIONS.find((item) => item.id === key) ??
    AI_MODEL_FLAT.find((item) => item.id === key) ??
    AI_TRANSLATE_MODEL_OPTIONS.find((item) => item.apiModel === key && item.id !== AI_MODEL_CUSTOM) ??
    AI_MODEL_FLAT.find((item) => item.apiModel === key && item.id !== AI_MODEL_CUSTOM)
  );
}

export function resolveTranslateModelSelection(storedModel: string, storedOptionId?: string) {
  if (storedOptionId === AI_MODEL_CUSTOM) {
    return { optionId: AI_MODEL_CUSTOM, customModel: storedModel };
  }
  if (storedOptionId) {
    const byId = AI_TRANSLATE_MODEL_OPTIONS.find((item) => item.id === storedOptionId);
    if (byId && byId.id !== AI_MODEL_CUSTOM) {
      return { optionId: byId.id, customModel: "" };
    }
  }
  const byModel = AI_TRANSLATE_MODEL_OPTIONS.find(
    (item) => item.apiModel === storedModel && item.id !== AI_MODEL_CUSTOM,
  );
  if (byModel) {
    return { optionId: byModel.id, customModel: "" };
  }
  return {
    optionId: AI_MODEL_CUSTOM,
    customModel: storedModel,
  };
}

export function resolveAiModelSelection(storedModel: string, storedOptionId?: string) {
  if (storedOptionId === AI_MODEL_CUSTOM) {
    return { optionId: AI_MODEL_CUSTOM, customModel: storedModel };
  }
  if (storedOptionId) {
    const byId = findAiModelOption(storedOptionId);
    if (byId && byId.id !== AI_MODEL_CUSTOM) {
      return { optionId: byId.id, customModel: "" };
    }
  }
  const byModel = findAiModelOption(storedModel);
  if (byModel && byModel.id !== AI_MODEL_CUSTOM) {
    return { optionId: byModel.id, customModel: "" };
  }
  return {
    optionId: AI_MODEL_CUSTOM,
    customModel: storedModel,
  };
}
