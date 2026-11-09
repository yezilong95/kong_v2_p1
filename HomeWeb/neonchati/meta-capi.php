<?php
/**
 * Meta Conversions API 发送助手（服务端）
 */
if (basename($_SERVER['SCRIPT_FILENAME'] ?? '') === basename(__FILE__)) {
    http_response_code(403);
    exit;
}

function meta_config(): array
{
    static $cfg = null;
    if ($cfg !== null) {
        return $cfg;
    }
    $path = __DIR__ . '/meta-config.php';
    if (!is_file($path)) {
        $path = __DIR__ . '/meta-config.example.php';
    }
    $loaded = is_file($path) ? (include $path) : [];
    $cfg = is_array($loaded) ? $loaded : [];
    return $cfg;
}

function meta_client_ip(): string
{
    foreach (['HTTP_CF_CONNECTING_IP', 'HTTP_X_REAL_IP', 'HTTP_X_FORWARDED_FOR', 'REMOTE_ADDR'] as $key) {
        if (empty($_SERVER[$key])) {
            continue;
        }
        $ip = trim(explode(',', (string) $_SERVER[$key])[0]);
        if (filter_var($ip, FILTER_VALIDATE_IP)) {
            return $ip;
        }
    }
    return '';
}

/**
 * @param array $payload 前端传来的字段：event_name, event_id, event_source_url, fbp, fbc, custom_data
 * @return array{ok:bool,skipped?:bool,http?:int,body?:string,error?:string}
 */
function meta_capi_send(array $payload): array
{
    $cfg = meta_config();
    $pixelId = trim((string) ($cfg['pixel_id'] ?? ''));
    $token = trim((string) ($cfg['access_token'] ?? ''));
    $version = trim((string) ($cfg['api_version'] ?? 'v21.0')) ?: 'v21.0';
    $testCode = trim((string) ($cfg['test_event_code'] ?? ''));

    if ($pixelId === '' || $token === '') {
        return ['ok' => false, 'skipped' => true, 'error' => 'capi_not_configured'];
    }

    $eventName = trim((string) ($payload['event_name'] ?? ''));
    if ($eventName === '') {
        return ['ok' => false, 'error' => 'event_name_required'];
    }

    $eventId = trim((string) ($payload['event_id'] ?? ''));
    $sourceUrl = trim((string) ($payload['event_source_url'] ?? ''));
    if ($sourceUrl === '') {
        $https = (!empty($_SERVER['HTTPS']) && $_SERVER['HTTPS'] !== 'off')
            || (($_SERVER['HTTP_X_FORWARDED_PROTO'] ?? '') === 'https');
        $host = $_SERVER['HTTP_HOST'] ?? 'localhost';
        $uri = $_SERVER['REQUEST_URI'] ?? '/';
        $sourceUrl = ($https ? 'https' : 'http') . '://' . $host . $uri;
    }

    $fbp = trim((string) ($payload['fbp'] ?? ($_COOKIE['_fbp'] ?? '')));
    $fbc = trim((string) ($payload['fbc'] ?? ($_COOKIE['_fbc'] ?? '')));
    $ua = trim((string) ($payload['client_user_agent'] ?? ($_SERVER['HTTP_USER_AGENT'] ?? '')));
    $ip = meta_client_ip();

    $userData = [];
    if ($ip !== '') {
        $userData['client_ip_address'] = $ip;
    }
    if ($ua !== '') {
        $userData['client_user_agent'] = $ua;
    }
    if ($fbp !== '') {
        $userData['fbp'] = $fbp;
    }
    if ($fbc !== '') {
        $userData['fbc'] = $fbc;
    }

    $event = [
        'event_name' => $eventName,
        'event_time' => time(),
        'event_source_url' => $sourceUrl,
        'action_source' => 'website',
        'user_data' => $userData,
    ];
    if ($eventId !== '') {
        $event['event_id'] = $eventId;
    }

    $custom = $payload['custom_data'] ?? null;
    if (is_array($custom) && $custom) {
        $event['custom_data'] = $custom;
    }

    $body = ['data' => [$event]];
    if ($testCode !== '') {
        $body['test_event_code'] = $testCode;
    }

    $url = 'https://graph.facebook.com/' . rawurlencode($version) . '/'
        . rawurlencode($pixelId) . '/events?access_token=' . rawurlencode($token);

    $json = json_encode($body, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
    if ($json === false) {
        return ['ok' => false, 'error' => 'json_encode_failed'];
    }

    if (function_exists('curl_init')) {
        $ch = curl_init($url);
        curl_setopt_array($ch, [
            CURLOPT_POST => true,
            CURLOPT_HTTPHEADER => ['Content-Type: application/json'],
            CURLOPT_POSTFIELDS => $json,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_TIMEOUT => 8,
            CURLOPT_CONNECTTIMEOUT => 4,
        ]);
        $resp = curl_exec($ch);
        $http = (int) curl_getinfo($ch, CURLINFO_HTTP_CODE);
        $err = curl_error($ch);
        curl_close($ch);
        if ($resp === false) {
            return ['ok' => false, 'error' => $err ?: 'curl_failed', 'http' => $http];
        }
        return [
            'ok' => $http >= 200 && $http < 300,
            'http' => $http,
            'body' => substr((string) $resp, 0, 1000),
        ];
    }

    $ctx = stream_context_create([
        'http' => [
            'method' => 'POST',
            'header' => "Content-Type: application/json\r\n",
            'content' => $json,
            'timeout' => 8,
            'ignore_errors' => true,
        ],
    ]);
    $resp = @file_get_contents($url, false, $ctx);
    $http = 0;
    if (isset($http_response_header[0]) && preg_match('/\s(\d{3})\s/', $http_response_header[0], $m)) {
        $http = (int) $m[1];
    }
    if ($resp === false) {
        return ['ok' => false, 'error' => 'http_failed', 'http' => $http];
    }
    return [
        'ok' => $http >= 200 && $http < 300,
        'http' => $http,
        'body' => substr((string) $resp, 0, 1000),
    ];
}
