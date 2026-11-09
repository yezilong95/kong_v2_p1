# Android_O 独立安装助手工程

与 `Android/`（远控主程序）完全分离，单独用 Android Studio 打开本目录。

## 包名

- 安装助手：`com.vibelink.installer`
- 远控服务：`com.vibelink.client`（由 `Android/app` 编译）

## 设计原则

- **安装助手**（本工程）：桌面图标 **永不隐藏**，作为用户长期打开的主 App
- **远控服务**（`Android/`）：授权完成后可隐藏桌面图标，从安装助手进入

## 流程

```
MainActivity（桌面 LAUNCHER，始终保留）
  ├─ 未装远控 → 点「安装远控服务」→ InstallActivity
  └─ 已装远控 → 主页 + 打开远控 / 重装 + 后续功能入口
```

## 配置

编辑 `app/build.gradle.kts`：

- `PAYLOAD_APK_URL`：远控 APK 下载地址（默认 `http://<ip>:3000/downloads/client.apk`）
- `CLIENT_PACKAGE`：远控包名

## 部署步骤

1. `Android/app` 编译 release APK → 复制到 `Manage/api/downloads/client.apk`
2. 启动 API
3. Android Studio **Open** → 选择 `Android_O/` 目录
4. Run `app` 模块 → 安装「安装助手」到手机

## 与 Android/ 的关系

| 目录 | 用途 |
|------|------|
| `Android/` | 远控主程序 + 授权向导 + 后台服务 |
| `Android_O/` | 仅协助安装远控的独立 App |

两个工程互不依赖，可分别编译打包。
