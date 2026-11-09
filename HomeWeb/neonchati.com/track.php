<?php
header('Content-Type: application/json; charset=utf-8');
header('Cache-Control: no-store');
if (($_SERVER['REQUEST_METHOD'] ?? '') !== 'POST') {
    http_response_code(405);
    echo '{"ok":0}';
    exit;
}

require __DIR__ . '/stats-lib.php';

$raw = file_get_contents('php://input');
$body = json_decode((string) $raw, true);
if (!is_array($body)) {
    $body = $_POST ?: [];
}
$body['type'] = 'visit';

stats_append_event($body);
echo '{"ok":1}';
