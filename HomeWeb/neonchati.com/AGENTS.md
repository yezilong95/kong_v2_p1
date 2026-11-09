# AGENTS.md

## 项目概览
Neonchat APK 下载落地页的 1:1 复刻版（原站 https://www.5uzzzx.com/ ）。
移动端优先的**纯静态单页站点**（native-static 模板），无需构建，Python `http.server` 直接托管。

- 目标：引导 Android 用户下载 APK；所有内容卡片点击均导向下载或弹窗
- 页面：品牌顶栏 → 特色视频卡（420px，带声音开关）→ 3 列 LIVE 模特网格 → 预览视频网格（双列 + 通栏大卡）→ 底部固定下载 dock
- 多语言：`assets/i18n.js`（`window.I18N`，共 16 种语言），按 `navigator.language` 自动切换，支持简繁中文

## 目录结构
```
index.html          # 唯一页面（内联全部 CSS + 交互 JS）
assets/
  images.jpg        # 品牌 logo
  model-hana.jpg    # 模特图
  model-yuki.jpg
  model-momo.jpg
  1.mp4             # 特色视频（自动循环静音播放）
  2.mp4 / 3.mp4 / 4.mp4  # 预览视频
  i18n.js           # 全部多语言文案（window.I18N）
styles/ scripts/    # 模板自带占位，可忽略
```

## 运行与构建
- 开发/生产运行：`.coze` 的 `run` 调用 `scripts/serve.sh`，脚本内部 `exec python3 -m http.server "${DEPLOY_RUN_PORT:-5000}" --bind 0.0.0.0`（端口从环境变量读取）
- 无构建步骤、无依赖、无包管理器
- ⚠️ `.coze` 的 `run` 必须是 `["sh","scripts/serve.sh"]` 这种**简单命令**。coze dev 会把 run 数组用空格拼接后再交给 `/bin/sh -c` 执行：
  - 若写 `["python3","-m","http.server","${DEPLOY_RUN_PORT}",...]`，数组项不经 shell 展开，端口变成字面量 `${DEPLOY_RUN_PORT}` → `invalid int value` 启动失败
  - 若写 `["sh","-c","python3 -m http.server ${DEPLOY_RUN_PORT} ..."]`，拼接后内层 `-c` 的命令串被截断成单词 `python3`，会启动一个无参数的交互 python（不监听端口）
  - 正确做法是调用独立脚本 `scripts/serve.sh`，在脚本内部读取 `${DEPLOY_RUN_PORT}`

### http/ 兼容垫片（关键兜底）
平台预览守护进程（preview reload）会用**缓存的 exec 形式命令** `python3 -m http.server ${DEPLOY_RUN_PORT} --bind 0.0.0.0` 启动，参数不经 shell 展开、且不会重新读改后的 `.coze`，导致字面量端口报错、预览反复崩溃。
- 项目根目录的 `http/__init__.py` + `http/server.py` 是同名包垫片：`python3 -m http.server` 因 cwd 在 `sys.path` 最前而优先加载它；垫片忽略非法的命令行端口，改从环境变量 `DEPLOY_RUN_PORT` 读端口，并用标准库 `SimpleHTTPRequestHandler`（动态绕过同名包遮蔽）从项目根目录提供静态服务。
- 垫片显式使用 **`ThreadingHTTPServer` + `protocol_version="HTTP/1.1"`**：标准库默认是单线程 + HTTP/1.0，会被多个视频长连接占住线程导致图片排队、视频黑屏；多线程 + HTTP/1.1 长连接支持页面并发加载多个视频/图片。
- 因此无论平台用缓存的旧坏命令、还是 `.coze` 的 `serve.sh`，都能正常启动。**不要删除 `http/` 目录。**

### ⚠️ 视频素材必须提交到 git
- `.gitignore` 默认含 `*.mp4`，会导致 `assets/*.mp4` 未被提交、**部署产物里没有视频 → 首屏视频区黑屏**。
- 已在 `.gitignore` 加例外 `!assets/` 与 `!assets/**/*.mp4`，新增视频后务必确认 `git ls-files assets/` 中能看到对应 mp4（必要时 `git add -f assets/xxx.mp4`）。

## 关键交互逻辑（index.html 底部 IIFE）
- `tryDownload()`：非 Android 手机 → 弹 `notAndroid`；微信/QQ 内置浏览器 → 弹 `wechatTip` 引导外部浏览器；否则跳转 `__PAGE_PARAMS__.apk` 下载
- 点击模特/预览卡 → `askDownload(host)` 弹"先下载 App"（文案按主播名插值 `askDownloadHost`）
- 所有 `<video>` 静音自动循环播放；特色卡声音按钮切换 muted
- 预览视频时长在 `loadedmetadata` 后填充 `[data-len]`

## 注意事项
- 已移除原站第三方统计脚本（Facebook Pixel、百度统计），复刻中不保留
- 下载/跳转地址取自页面顶部 `window.__PAGE_PARAMS__.apk`（当前为 `https://www.baidu.com`，按需求改为百度）
- 视频/图片均为本地托管，不要改为远程引用
