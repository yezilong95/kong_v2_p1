<?php
header('Content-Type: application/json; charset=utf-8');
header('Cache-Control: no-store');

require __DIR__ . '/stats-lib.php';

$method = $_SERVER['REQUEST_METHOD'] ?? 'GET';

if ($method === 'POST') {
    $raw = file_get_contents('php://input');
    $body = json_decode((string) $raw, true);
    if (!is_array($body)) {
        $body = $_POST ?: [];
    }
    if (($body['action'] ?? '') === 'delete') {
        $ok = stats_delete_id((string) ($body['id'] ?? ''));
        echo json_encode(['ok' => $ok ? 1 : 0], JSON_UNESCAPED_UNICODE);
        exit;
    }
    http_response_code(400);
    echo '{"ok":0}';
    exit;
}

$rows = stats_read_rows();
echo json_encode([
    'ok' => 1,
    'today' => date('Y-m-d'),
    'kpi' => stats_kpi($rows),
    'rows' => $rows,
], JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
