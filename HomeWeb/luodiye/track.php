<?php
header('Cache-Control: no-store, no-cache, must-revalidate');
header('Content-Type: application/json; charset=utf-8');

try {
    require __DIR__ . '/stats-lib.php';

    $method = strtoupper((string) ($_SERVER['REQUEST_METHOD'] ?? 'GET'));
    if ($method === 'POST') {
        $raw = file_get_contents('php://input');
        $decoded = json_decode((string) $raw, true);
        $body = is_array($decoded) ? $decoded : ($_POST ?: []);
    } else {
        $body = $_GET;
    }
    $body['type'] = 'visit';

    $row = stats_append_event($body);
    $dir = stats_data_dir();
    echo json_encode([
        'ok' => 1,
        'id' => $row['id'] ?? '',
        'writable' => is_writable($dir) ? 1 : 0,
    ], JSON_UNESCAPED_UNICODE);
} catch (Throwable $e) {
    http_response_code(500);
    echo json_encode([
        'ok' => 0,
        'error' => $e->getMessage(),
    ], JSON_UNESCAPED_UNICODE);
}
