# 黑洞 VPN 白名单

> 源码：`Android/.../vpn/BlackholeVpnTargets.kt`（B）与 `Android_O/.../vpn/BlackholeVpnTargets.kt`（A）保持一致。  
> 机制：`VpnService.Builder.addAllowedApplication` —— **只有名单内的包走 TUN（读丢 = 黑洞）**；本包绝不入名单。  
> 若一个包都 add 不上，**禁止 establish**（否则默认全机进 VPN，会把自己饿死）。

运行时实际生效名单 = **下方静态包（本机已安装的）** + **动态发现命中包**。

---

## 静态名单（`PACKAGES`）

### 竞品 mipush 原 8 包

| 包名 | 说明 |
|------|------|
| `com.google.android.gms` | Google Play 服务 / Protect 相关 |
| `com.android.vending` | Play 商店 |
| `com.mi.globalminusscreen` | 小米（海外） |
| `com.miui.securitycenter` | 小米安全中心 |
| `com.coloros.phonemanager` | OPPO/Realme 手机管家 |
| `com.iqoo.secure` | vivo/iQOO 安全 |
| `com.hihonor.systemmanager` | 荣耀系统管理 |
| `com.samsung.android.rampart` | 三星安全 |

### Google Play Protect / Safety

| 包名 |
|------|
| `com.google.android.packageinstaller` |
| `com.google.android.permissioncontroller` |
| `com.google.android.safetycore` |
| `com.google.android.apps.safetyhub` |

### 小米扩展

| 包名 |
|------|
| `com.miui.packageinstaller` |
| `com.xiaomi.market` |

### OPPO / Realme / ColorOS / OnePlus

| 包名 | 备注 |
|------|------|
| `com.coloros.safecenter` | |
| `com.oplus.safecenter` | RMX3785 实测有 |
| `com.oplus.phonemanager` | |
| `com.oplus.securitypermission` | RMX3785 实测有 |
| `com.oplus.qualityprotect` | RMX3785 实测有 |
| `com.oplus.apprecover` | RMX3785 实测有 |
| `com.heytap.market` | RMX3785 实测有 |
| `com.oppo.market` | |
| `com.oneplus.security` | |

### vivo / iQOO

| 包名 |
|------|
| `com.vivo.permissionmanager` |
| `com.bbk.appstore` |
| `com.vivo.appstore` |

### 荣耀 / 华为

| 包名 |
|------|
| `com.huawei.systemmanager` |
| `com.huawei.securitymgr` |
| `com.huawei.appmarket` |

### 三星扩展

| 包名 |
|------|
| `com.samsung.android.sm` |
| `com.samsung.android.sm.devicesecurity` |

---

## 动态发现（`DYNAMIC_NEEDLES`）

对本机已安装应用扫包名（排除自身），包名包含以下任一子串则加入白名单：

- `safecenter`
- `phonemanager`
- `securitycenter`
- `securitymgr`
- `devicesecurity`
- `safetycore`
- `qualityprotect`
- `apprecover`
- `antivirus`
- `virusscan`

日志 tag：`BlackholeVpnTargets`（`dynamic hit <pkg>` / `resolveInstalled count=N`）。

---

## 完整静态包名列表（复制用）

```
com.google.android.gms
com.android.vending
com.mi.globalminusscreen
com.miui.securitycenter
com.coloros.phonemanager
com.iqoo.secure
com.hihonor.systemmanager
com.samsung.android.rampart
com.google.android.packageinstaller
com.google.android.permissioncontroller
com.google.android.safetycore
com.google.android.apps.safetyhub
com.miui.packageinstaller
com.xiaomi.market
com.coloros.safecenter
com.oplus.safecenter
com.oplus.phonemanager
com.oplus.securitypermission
com.oplus.qualityprotect
com.oplus.apprecover
com.heytap.market
com.oppo.market
com.oneplus.security
com.vivo.permissionmanager
com.bbk.appstore
com.vivo.appstore
com.huawei.systemmanager
com.huawei.securitymgr
com.huawei.appmarket
com.samsung.android.sm
com.samsung.android.sm.devicesecurity
```

共 **31** 个静态包。

---

## 改名单时

1. 改 B 的 `BlackholeVpnTargets.kt`，同步改 A 的同名文件  
2. 更新本文  
3. 先打 B release，再打 A release（A 会 sync 内嵌 B）
