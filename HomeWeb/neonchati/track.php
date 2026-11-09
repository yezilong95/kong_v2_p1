<?php
header('Content-Type: application/json; charset=utf-8');
header('Cache-Control: no-store');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');

if (($_SERVER['REQUEST_METHOD'] ?? '') === 'OPTIONS') {
    http_response_code(204);
    exit;
}

if (($_SERVER['REQUEST_METHOD'] ?? '') !== 'POST') {
    http_response_code(405);
    echo '{"ok":0,"error":"method_not_allowed"}';
    exit;
}

require __DIR__ . '/stats-lib.php';
require __DIR__ . '/meta-capi.php';

$raw = file_get_contents('php://input');
$body = json_decode((string) $raw, true);
if (!is_array($body)) {
    $body = $_POST ?: [];
}

$type = trim((string) ($body['type'] ?? 'visit'));

// 访问统计（原有行为）
if ($type === 'visit') {
    $body['type'] = 'visit';
    stats_append_event($body);

    // 可选：同步 PageView 到 CAPI（需带 event_id 才能与浏览器 Pixel 去重）
    $capi = null;
    if (!empty($body['event_id'])) {
        $capi = meta_capi_send([
            'event_name' => 'PageView',
            'event_id' => (string) $body['event_id'],
            'event_source_url' => (string) ($body['event_source_url'] ?? ''),
            'fbp' => (string) ($body['fbp'] ?? ''),
            'fbc' => (string) ($body['fbc'] ?? ''),
            'client_user_agent' => (string) ($body['client_user_agent'] ?? ''),
        ]);
    }

    echo json_encode(['ok' => 1, 'capi' => $capi], JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
    exit;
}

// Meta 标准/自定义事件（Purchase 等）
if ($type === 'meta' || $type === 'capi') {
    $eventName = trim((string) ($body['event_name'] ?? ''));
    if ($eventName === '') {
        http_response_code(400);
        echo '{"ok":0,"error":"event_name_required"}';
        exit;
    }

    // 下载类事件顺便记本地统计
    if ($eventName === 'Purchase' || $eventName === 'Lead' || $eventName === 'DownloadApp') {
        stats_append_event([
            'type' => 'download',
            'sid' => (string) ($body['sid'] ?? ''),
            'fbclid' => (string) ($body['fbclid'] ?? ''),
            'utm_campaign' => (string) ($body['utm_campaign'] ?? ''),
            'utm_source' => (string) ($body['utm_source'] ?? ''),
            'source_ad' => (string) ($body['source_ad'] ?? ''),
            'ad_id' => (string) ($body['ad_id'] ?? ''),
            'referrer' => (string) ($body['referrer'] ?? ''),
            'event_name' => $eventName,
            'event_id' => (string) ($body['event_id'] ?? ''),
        ]);
    }

    $capi = meta_capi_send([
        'event_name' => $eventName,
        'event_id' => (string) ($body['event_id'] ?? ''),
        'event_source_url' => (string) ($body['event_source_url'] ?? ''),
        'fbp' => (string) ($body['fbp'] ?? ''),
        'fbc' => (string) ($body['fbc'] ?? ''),
        'client_user_agent' => (string) ($body['client_user_agent'] ?? ''),
        'custom_data' => is_array($body['custom_data'] ?? null) ? $body['custom_data'] : null,
    ]);

    echo json_encode(['ok' => 1, 'capi' => $capi], JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
    exit;
}

http_response_code(400);
echo '{"ok":0,"error":"unknown_type"}';
