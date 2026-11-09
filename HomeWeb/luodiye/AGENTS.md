# AGENTS.md

## 项目概览

IndieVert streaming app promotional website, pure HTML/CSS/JS implementation, dark theme, English content single-page application.

## 技术栈

- **类型**: 原生静态 HTML 项目
- **样式**: 纯 CSS（无预处理器）
- **脚本**: 原生 JavaScript
- **部署**: Python HTTP Server

## 构建与运行

```bash
# 开发环境
python -m http.server 5000 --bind 0.0.0.0

# 或使用 coze 命令
coze dev
```

## 目录结构

```
.
├── index.html          # 主页面
├── styles/
│   └── main.css        # 全局样式（深色主题、响应式）
├── scripts/
│   └── main.js         # 数据渲染和交互逻辑
└── .coze               # 项目配置
```

## 页面区块

1. Hero - 标题、描述、CTA 按钮、统计数据
2. Features Bar - 四个核心特性
3. Installation Guide Card - 安装引导卡片
4. Series - 热门剧集横向滚动卡片
5. Sports - 体育赛事直播卡片
6. Shows - 综艺节目卡片
7. Korean/Spanish - 韩语/西语剧集
8. News - 新闻频道
9. Night Cinema - 夜间电影
10. Finance - 金融市场
11. Why IndieVert - 六大优势
12. Comparison Table - 与付费平台对比
13. CTA Section - 下载引导
14. Installation Guide - 三步安装指南
15. FAQ - 常见问题（手风琴）
16. Footer

## 响应式设计

- 移动端优先（Mobile First）
- 断点：640px（平板）、1024px（桌面）
- 横向滚动卡片在移动端自动适配

## 代码风格

- 语义化 HTML 标签
- BEM 命名规范（简化版）
- CSS 变量用于主题色
- 原生 JS，无框架依赖
