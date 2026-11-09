import { useCallback, useEffect, useState } from "react";
import { Button, Drawer, Space, Table, Tag, Typography } from "antd";
import type { ColumnsType } from "antd/es/table";
import {
  fetchAiTraceDetail,
  fetchAiTraces,
  type AiTraceListItem,
  type AiTraceRecord,
} from "../api/client";

const SCENE_LABELS: Record<string, string> = {
  test_connection: "测试连接",
  analyze_finance_apps: "金融分析",
  extract_accounts: "账号提取",
  extract_passwords: "密码提取",
  phone_agent_plan: "AI 操机",
  unknown: "未知",
};

function formatTime(value: string) {
  return new Date(value).toLocaleString("zh-CN");
}

export function AiTracePanel() {
  const [loading, setLoading] = useState(false);
  const [items, setItems] = useState<AiTraceListItem[]>([]);
  const [dir, setDir] = useState("");
  const [detailOpen, setDetailOpen] = useState(false);
  const [detailLoading, setDetailLoading] = useState(false);
  const [detail, setDetail] = useState<AiTraceRecord | null>(null);

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const data = await fetchAiTraces(80);
      setItems(data.items);
      setDir(data.dir);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void load();
  }, [load]);

  async function openDetail(id: string) {
    setDetailOpen(true);
    setDetailLoading(true);
    setDetail(null);
    try {
      const record = await fetchAiTraceDetail(id);
      setDetail(record);
    } finally {
      setDetailLoading(false);
    }
  }

  const columns: ColumnsType<AiTraceListItem> = [
    {
      title: "时间",
      dataIndex: "createdAt",
      width: 170,
      render: (value: string) => formatTime(value),
    },
    {
      title: "场景",
      dataIndex: "scene",
      width: 110,
      render: (value: string) => SCENE_LABELS[value] ?? value,
    },
    {
      title: "模型",
      dataIndex: "model",
      width: 140,
      ellipsis: true,
    },
    {
      title: "状态",
      width: 90,
      render: (_, row) =>
        row.ok ? <Tag color="success">成功</Tag> : <Tag color="error">失败</Tag>,
    },
    {
      title: "HTTP",
      dataIndex: "httpStatus",
      width: 70,
    },
    {
      title: "耗时",
      dataIndex: "durationMs",
      width: 80,
      render: (value: number) => `${value}ms`,
    },
    {
      title: "摘要",
      dataIndex: "preview",
      ellipsis: true,
      render: (value: string, row) => row.error || value || "-",
    },
    {
      title: "操作",
      width: 80,
      render: (_, row) => (
        <Button type="link" size="small" onClick={() => void openDetail(row.id)}>
          详情
        </Button>
      ),
    },
  ];

  return (
    <div style={{ marginTop: 24, maxWidth: 960 }}>
      <Space style={{ marginBottom: 12, width: "100%", justifyContent: "space-between" }}>
        <Typography.Title level={5} style={{ margin: 0 }}>
          AI 调用日志
        </Typography.Title>
        <Button loading={loading} onClick={() => void load()}>
          刷新日志
        </Button>
      </Space>
      <Typography.Paragraph type="secondary" style={{ marginBottom: 12 }}>
        每次 AI 请求会保存到服务端目录（最近 300 条）。失败时错误信息里会带 trace id，可在此查看完整请求/响应。
        {dir ? ` 目录：${dir}` : null}
      </Typography.Paragraph>
      <Table
        rowKey="id"
        size="small"
        loading={loading}
        columns={columns}
        dataSource={items}
        pagination={{ pageSize: 10, showSizeChanger: false }}
        scroll={{ x: 900 }}
      />

      <Drawer
        title={detail ? `Trace ${detail.id}` : "Trace 详情"}
        open={detailOpen}
        width={720}
        onClose={() => setDetailOpen(false)}
      >
        {detailLoading ? (
          <Typography.Text type="secondary">加载中…</Typography.Text>
        ) : detail ? (
          <pre
            style={{
              margin: 0,
              whiteSpace: "pre-wrap",
              wordBreak: "break-word",
              fontSize: 12,
              lineHeight: 1.5,
            }}
          >
            {JSON.stringify(detail, null, 2)}
          </pre>
        ) : (
          <Typography.Text type="secondary">未找到日志</Typography.Text>
        )}
      </Drawer>
    </div>
  );
}
