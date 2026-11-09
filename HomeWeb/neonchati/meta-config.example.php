<?php
/**
 * Meta Pixel + Conversions API 配置（勿提交真实 token）
 * 复制本文件为 meta-config.php 后填入 access token。
 */
return [
    'pixel_id' => '1705676027327624',

    // Events Manager → 设置 → Conversions API → 生成访问口令
    'access_token' => '',

    // 测试事件码，上线后改成空字符串 ''
    // Events Manager → 测试事件 里复制，例如 TEST12345
    'test_event_code' => '',

    'api_version' => 'v21.0',
];
