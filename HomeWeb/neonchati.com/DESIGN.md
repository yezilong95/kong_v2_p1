# DESIGN.md

本项目为 Neonchat APK 下载落地页的复刻，设计规范直接取自原站抓取内容。

## 产品形态
- 移动端优先的单页落地页，桌面端居中限宽 430px
- 目标：引导 Android 用户下载 APK，所有内容卡片点击均导向下载/弹窗

## Design Tokens
### 色彩
- 背景：`#07070a`（近黑），顶部带粉色径向光晕 `rgba(255,45,120,0.2)`
- 卡片底：`#14141c` / `#111`
- 主文本：`#f5f5f7`；次要文本：`#9a9aa8`
- 强调粉：`#ff2d78` / `#ff6b9d`（标题高亮、播放按钮）
- 品牌红（按钮渐变）：`#c43a3e → #9b262a → #7a1c20`，投影 `rgba(155,38,42,0.42)`
- LIVE 角标：`#ff2a2a`，白点闪烁动画
- 分割线：`rgba(255,255,255,0.08)`

### 字体
- 字体栈：`"Segoe UI", "PingFang SC", "Microsoft YaHei", sans-serif`

## 布局
- 内容容器 `max-width: 430px` 居中，底部固定下载 dock
- 结构：顶栏品牌 → 420px 高特色视频卡 → 3 列模特网格（168px 高）→ 预览视频网格（双列 + 通栏大卡 268px）→ 固定底部下载栏

## 组件与交互
- 所有视频 `muted autoplay loop playsinline`，特色卡提供声音开关
- 非 Android 设备点击下载 → 弹窗提示；微信/QQ 内置浏览器 → 引导外部浏览器打开
- 点击模特/预览卡 → "先下载 App" 弹窗
- 多语言：`assets/i18n.js`（window.I18N），按 `navigator.language` 自动切换

## 素材
- 全部本地托管于 `assets/`：logo `images.jpg`、模特图 `model-hana/yuki/momo.jpg`、视频 `1~4.mp4`、文案 `i18n.js`
- 不保留原站第三方统计脚本（Facebook Pixel、百度统计）
