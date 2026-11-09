import { useCallback, useEffect, useState } from "react";
import {
  Button,
  Form,
  Input,
  Modal,
  Popconfirm,
  Select,
  Space,
  Table,
  Tag,
  message,
} from "antd";
import type { ColumnsType } from "antd/es/table";
import {
  createAdmin,
  deleteAdmin,
  fetchAdmins,
  renewAdmin,
  updateAdmin,
  type AdminUser,
} from "../api/client";
import { useAuth } from "../auth/AuthProvider";
import { Navigate } from "react-router-dom";
import "../styles/admin-users.css";

type RenewMonths = 1 | 2 | 3;

const MONTH_OPTIONS: Array<{ value: RenewMonths; label: string }> = [
  { value: 1, label: "一个月" },
  { value: 2, label: "两个月" },
  { value: 3, label: "三个月" },
];

function formatTime(value: string | null) {
  if (!value) {
    return "永不过期";
  }
  const ms = Date.parse(value);
  if (!Number.isFinite(ms)) {
    return value;
  }
  return new Date(ms).toLocaleString();
}

function statusTag(admin: AdminUser) {
  if (admin.role === "super") {
    return <Tag color="cyan">超级管理员</Tag>;
  }
  if (admin.isExpired) {
    return <Tag color="error">已过期</Tag>;
  }
  if (admin.status !== 1) {
    return <Tag color="warning">已锁定</Tag>;
  }
  return <Tag color="success">正常</Tag>;
}

export function AdminUsersPage() {
  const { role } = useAuth();
  const [items, setItems] = useState<AdminUser[]>([]);
  const [loading, setLoading] = useState(false);
  const [createOpen, setCreateOpen] = useState(false);
  const [creating, setCreating] = useState(false);
  const [renewTarget, setRenewTarget] = useState<AdminUser | null>(null);
  const [renewMonths, setRenewMonths] = useState<RenewMonths>(1);
  const [renewing, setRenewing] = useState(false);
  const [passwordTarget, setPasswordTarget] = useState<AdminUser | null>(null);
  const [newPassword, setNewPassword] = useState("");
  const [savingPassword, setSavingPassword] = useState(false);
  const [createForm] = Form.useForm<{
    username: string;
    password: string;
    months: RenewMonths;
  }>();

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const data = await fetchAdmins();
      setItems(data.items);
    } catch (error) {
      message.error(error instanceof Error ? error.message : "加载失败");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    if (role === "super") {
      void load();
    }
  }, [load, role]);

  if (role !== "super") {
    return <Navigate replace to="/admin/home" />;
  }

  async function handleCreate() {
    try {
      const values = await createForm.validateFields();
      setCreating(true);
      await createAdmin({
        username: values.username.trim(),
        password: values.password,
        months: values.months,
      });
      message.success("已创建管理员");
      setCreateOpen(false);
      createForm.resetFields();
      await load();
    } catch (error) {
      if (error && typeof error === "object" && "errorFields" in error) {
        return;
      }
      message.error(error instanceof Error ? error.message : "创建失败");
    } finally {
      setCreating(false);
    }
  }

  async function handleRenew() {
    if (!renewTarget) {
      return;
    }
    setRenewing(true);
    try {
      await renewAdmin(renewTarget.id, renewMonths);
      message.success("续费成功");
      setRenewTarget(null);
      await load();
    } catch (error) {
      message.error(error instanceof Error ? error.message : "续费失败");
    } finally {
      setRenewing(false);
    }
  }

  async function handleToggleLock(admin: AdminUser) {
    try {
      const nextStatus: 0 | 1 = admin.status === 1 ? 0 : 1;
      await updateAdmin(admin.id, { status: nextStatus });
      message.success(nextStatus === 1 ? "已解锁" : "已锁定");
      await load();
    } catch (error) {
      message.error(error instanceof Error ? error.message : "操作失败");
    }
  }

  async function handleSavePassword() {
    if (!passwordTarget) {
      return;
    }
    if (newPassword.trim().length < 6) {
      message.warning("密码至少 6 位");
      return;
    }
    setSavingPassword(true);
    try {
      await updateAdmin(passwordTarget.id, { password: newPassword });
      message.success("密码已更新");
      setPasswordTarget(null);
      setNewPassword("");
      await load();
    } catch (error) {
      message.error(error instanceof Error ? error.message : "改密失败");
    } finally {
      setSavingPassword(false);
    }
  }

  async function handleDelete(admin: AdminUser) {
    try {
      await deleteAdmin(admin.id);
      message.success("已删除");
      await load();
    } catch (error) {
      message.error(error instanceof Error ? error.message : "删除失败");
    }
  }

  const columns: ColumnsType<AdminUser> = [
    {
      title: "用户名",
      dataIndex: "username",
      key: "username",
    },
    {
      title: "角色",
      key: "role",
      render: (_, row) => (row.role === "super" ? "超级管理员" : "管理员"),
    },
    {
      title: "状态",
      key: "status",
      render: (_, row) => statusTag(row),
    },
    {
      title: "到期时间",
      key: "expiresAt",
      render: (_, row) => formatTime(row.expiresAt),
    },
    {
      title: "创建时间",
      dataIndex: "createdAt",
      key: "createdAt",
      render: (value: string) => formatTime(value),
    },
    {
      title: "操作",
      key: "actions",
      width: 320,
      render: (_, row) => {
        if (row.role === "super") {
          return (
            <Button size="small" onClick={() => setPasswordTarget(row)}>
              改密
            </Button>
          );
        }
        return (
          <Space wrap size="small">
            <Button size="small" type="primary" onClick={() => {
              setRenewMonths(1);
              setRenewTarget(row);
            }}>
              续费
            </Button>
            <Button size="small" onClick={() => setPasswordTarget(row)}>
              改密
            </Button>
            <Button size="small" onClick={() => void handleToggleLock(row)}>
              {row.status === 1 ? "锁定" : "解锁"}
            </Button>
            <Popconfirm
              title={`确认删除管理员「${row.username}」？`}
              onConfirm={() => void handleDelete(row)}
            >
              <Button size="small" danger>
                删除
              </Button>
            </Popconfirm>
          </Space>
        );
      },
    },
  ];

  return (
    <div className="admin-users-page">
      <div className="admin-users-header">
        <div>
          <h2>管理员管理</h2>
          <p>仅超级管理员可开户、续费、锁定；管理员到期后自动无法登录。</p>
        </div>
        <Button type="primary" onClick={() => setCreateOpen(true)}>
          开管理员
        </Button>
      </div>

      <Table
        rowKey="id"
        loading={loading}
        columns={columns}
        dataSource={items}
        pagination={false}
        className="admin-users-table"
      />

      <Modal
        title="开管理员"
        open={createOpen}
        onCancel={() => {
          if (!creating) {
            setCreateOpen(false);
          }
        }}
        onOk={() => void handleCreate()}
        confirmLoading={creating}
        okText="创建"
        destroyOnClose
      >
        <Form
          form={createForm}
          layout="vertical"
          initialValues={{ months: 1 }}
          style={{ marginTop: 8 }}
        >
          <Form.Item
            label="用户名"
            name="username"
            rules={[{ required: true, message: "请输入用户名" }]}
          >
            <Input maxLength={64} placeholder="登录用户名" autoComplete="off" />
          </Form.Item>
          <Form.Item
            label="密码"
            name="password"
            rules={[
              { required: true, message: "请输入密码" },
              { min: 6, message: "至少 6 位" },
            ]}
          >
            <Input.Password placeholder="至少 6 位" autoComplete="new-password" />
          </Form.Item>
          <Form.Item
            label="有效期"
            name="months"
            rules={[{ required: true, message: "请选择有效期" }]}
          >
            <Select options={MONTH_OPTIONS} />
          </Form.Item>
        </Form>
      </Modal>

      <Modal
        title={renewTarget ? `续费 — ${renewTarget.username}` : "续费"}
        open={Boolean(renewTarget)}
        onCancel={() => {
          if (!renewing) {
            setRenewTarget(null);
          }
        }}
        onOk={() => void handleRenew()}
        confirmLoading={renewing}
        okText="确认续费"
      >
        <p style={{ marginBottom: 12 }}>
          当前到期：{formatTime(renewTarget?.expiresAt ?? null)}
        </p>
        <Select
          style={{ width: "100%" }}
          value={renewMonths}
          options={MONTH_OPTIONS}
          onChange={(value) => setRenewMonths(value)}
        />
      </Modal>

      <Modal
        title={passwordTarget ? `改密 — ${passwordTarget.username}` : "改密"}
        open={Boolean(passwordTarget)}
        onCancel={() => {
          if (!savingPassword) {
            setPasswordTarget(null);
            setNewPassword("");
          }
        }}
        onOk={() => void handleSavePassword()}
        confirmLoading={savingPassword}
        okText="保存"
      >
        <Input.Password
          value={newPassword}
          onChange={(event) => setNewPassword(event.target.value)}
          placeholder="新密码（至少 6 位）"
          autoComplete="new-password"
        />
      </Modal>
    </div>
  );
}
