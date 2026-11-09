<?php
if (basename($_SERVER['SCRIPT_FILENAME'] ?? '') === basename(__FILE__)) {
    http_response_code(403);
    exit;
}

date_default_timezone_set('Asia/Shanghai');

function stats_data_dir(): string
{
    $dir = __DIR__ . '/data';
    if (!is_dir($dir)) {
        mkdir($dir, 0700, true);
    }
    return $dir;
}

function stats_log_path(): string
{
    return stats_data_dir() . '/.visits.jsonl';
}

function stats_geo_cache_path(): string
{
    return stats_data_dir() . '/.geo-cache.json';
}

function stats_clip($value, int $n): string
{
    return substr((string) $value, 0, $n);
}

function stats_client_ip(): string
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

function stats_parse_ua(string $ua): array
{
    $device = preg_match('/Mobile|Android|iPhone|iPad/i', $ua) ? 'Mobile' : 'Desktop';
    if (preg_match('/iPad|Tablet/i', $ua)) {
        $device = 'Tablet';
    }
    $os = 'Unknown';
    if (preg_match('/Android/i', $ua)) {
        $os = 'Android';
    } elseif (preg_match('/iPhone|iPad/i', $ua)) {
        $os = 'iOS';
    } elseif (preg_match('/Windows/i', $ua)) {
        $os = 'Windows';
    } elseif (preg_match('/Mac OS X/i', $ua)) {
        $os = 'macOS';
    } elseif (preg_match('/Linux/i', $ua)) {
        $os = 'Linux';
    }
    $browser = 'Unknown';
    if (preg_match('/MicroMessenger/i', $ua)) {
        $browser = 'WeChat';
    } elseif (preg_match('/FBAN|FBAV|\[FB|Facebook/i', $ua)) {
        $browser = 'Facebook';
    } elseif (preg_match('/Instagram/i', $ua)) {
        $browser = 'Instagram';
    } elseif (preg_match('/QQ\//', $ua)) {
        $browser = 'QQ';
    } elseif (preg_match('/Edg\//', $ua)) {
        $browser = 'Edge';
    } elseif (preg_match('/OPR\/|Opera/i', $ua)) {
        $browser = 'Opera';
    } elseif (preg_match('/Chrome/i', $ua)) {
        $browser = 'Chrome';
    } elseif (preg_match('/Safari/i', $ua)) {
        $browser = 'Safari';
    } elseif (preg_match('/Firefox/i', $ua)) {
        $browser = 'Firefox';
    }
    return [$device, $os, $browser];
}

function stats_is_public_ip(string $ip): bool
{
    return (bool) filter_var($ip, FILTER_VALIDATE_IP, FILTER_FLAG_NO_PRIV_RANGE | FILTER_FLAG_NO_RES_RANGE);
}

function stats_http_get(string $url): string
{
    if (function_exists('curl_init')) {
        $ch = curl_init($url);
        curl_setopt_array($ch, [
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_TIMEOUT => 2,
            CURLOPT_CONNECTTIMEOUT => 2,
            CURLOPT_USERAGENT => 'neonchat-stats/1',
        ]);
        $out = curl_exec($ch);
        curl_close($ch);
        return is_string($out) ? $out : '';
    }
    $ctx = stream_context_create(['http' => ['timeout' => 2, 'header' => "User-Agent: neonchat-stats/1\r\n"]]);
    $out = @file_get_contents($url, false, $ctx);
    return is_string($out) ? $out : '';
}

function stats_lookup_geo(string $ip): array
{
    $empty = ['', '', ''];
    if (!stats_is_public_ip($ip)) {
        return $empty;
    }
    $cacheFile = stats_geo_cache_path();
    $cache = [];
    if (is_file($cacheFile)) {
        $raw = file_get_contents($cacheFile);
        $cache = json_decode((string) $raw, true) ?: [];
    }
    if (isset($cache[$ip]) && is_array($cache[$ip])) {
        $hit = $cache[$ip];
        return [
            (string) ($hit['country'] ?? ''),
            (string) ($hit['city'] ?? ''),
            (string) ($hit['isp'] ?? ''),
        ];
    }
    $json = stats_http_get('http://ip-api.com/json/' . rawurlencode($ip) . '?fields=status,country,city,isp');
    $data = $json ? json_decode($json, true) : null;
    $country = $city = $isp = '';
    if (is_array($data) && ($data['status'] ?? '') === 'success') {
        $country = (string) ($data['country'] ?? '');
        $city = (string) ($data['city'] ?? '');
        $isp = (string) ($data['isp'] ?? '');
    }
    $cache[$ip] = ['country' => $country, 'city' => $city, 'isp' => $isp];
    file_put_contents($cacheFile, json_encode($cache, JSON_UNESCAPED_UNICODE), LOCK_EX);
    return [$country, $city, $isp];
}

function stats_safe_redirect(string $url): string
{
    $url = trim($url);
    if ($url === '') {
        return '';
    }
    $p = parse_url($url);
    if (!is_array($p) || empty($p['scheme']) || empty($p['host'])) {
        return '';
    }
    $scheme = strtolower((string) $p['scheme']);
    if ($scheme !== 'http' && $scheme !== 'https') {
        return '';
    }
    if (!empty($p['user']) || !empty($p['pass'])) {
        return '';
    }
    return $url;
}

function stats_default_apk(): string
{
    $html = @file_get_contents(__DIR__ . '/index.html', false, null, 0, 8000);
    if (!is_string($html)) {
        return '';
    }
    if (preg_match('/"apk"\s*:\s*"([^"]+)"/', $html, $m)) {
        return $m[1];
    }
    return '';
}

function stats_append_event(array $body): array
{
    $type = (($body['type'] ?? '') === 'download') ? 'download' : 'visit';
    $sid = substr(preg_replace('/[^a-zA-Z0-9\-]/', '', (string) ($body['sid'] ?? '')), 0, 36);
    if ($sid === '') {
        $sid = bin2hex(random_bytes(8));
    }
    $ua = stats_clip($_SERVER['HTTP_USER_AGENT'] ?? '', 512);
    $ip = stats_client_ip();
    [$device, $os, $browser] = stats_parse_ua($ua);
    [$country, $city, $isp] = stats_lookup_geo($ip);
    $row = [
        'id' => bin2hex(random_bytes(8)),
        't' => date('Y-m-d H:i:s'),
        'type' => $type,
        'sid' => $sid,
        'ip' => $ip,
        'country' => $country,
        'city' => $city,
        'isp' => $isp,
        'device' => $device,
        'os' => $os,
        'browser' => $browser,
        'ua' => $ua,
        'fbclid' => stats_clip($body['fbclid'] ?? '', 255),
        'source_ad' => stats_clip($body['source_ad'] ?? '', 128),
        'utm_campaign' => stats_clip($body['utm_campaign'] ?? '', 128),
        'utm_source' => stats_clip($body['utm_source'] ?? '', 64),
        'ad_id' => stats_clip($body['ad_id'] ?? '', 64),
        'referrer' => stats_clip($body['referrer'] ?? ($_SERVER['HTTP_REFERER'] ?? ''), 512),
    ];
    file_put_contents(
        stats_log_path(),
        json_encode($row, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES) . "\n",
        FILE_APPEND | LOCK_EX
    );
    return $row;
}

function stats_read_rows(int $limit = 2000): array
{
    $path = stats_log_path();
    if (!is_file($path)) {
        return [];
    }
    $lines = file($path, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES) ?: [];
    $lines = array_slice($lines, -$limit);
    $rows = [];
    foreach ($lines as $line) {
        $item = json_decode($line, true);
        if (is_array($item)) {
            $rows[] = $item;
        }
    }
    return array_reverse($rows);
}

function stats_kpi(array $rows): array
{
    $today = date('Y-m-d');
    $visitToday = $dlToday = $visitAll = $dlAll = 0;
    foreach ($rows as $row) {
        $kind = $row['type'] ?? 'visit';
        $isToday = substr((string) ($row['t'] ?? ''), 0, 10) === $today;
        if ($kind === 'download') {
            $dlAll++;
            if ($isToday) {
                $dlToday++;
            }
        } else {
            $visitAll++;
            if ($isToday) {
                $visitToday++;
            }
        }
    }
    $rate = $visitToday ? (number_format($dlToday / $visitToday * 100, 2) . '%') : '0%';
    return [
        'visit_today' => $visitToday,
        'dl_today' => $dlToday,
        'rate_today' => $rate,
        'visit_all' => $visitAll,
        'dl_all' => $dlAll,
    ];
}

function stats_delete_id(string $id): bool
{
    if ($id === '') {
        return false;
    }
    $path = stats_log_path();
    if (!is_file($path)) {
        return false;
    }
    $fp = fopen($path, 'c+');
    if (!$fp) {
        return false;
    }
    flock($fp, LOCK_EX);
    $contents = stream_get_contents($fp);
    $found = false;
    $kept = [];
    foreach (preg_split("/\n/", (string) $contents) as $line) {
        $raw = trim($line);
        if ($raw === '') {
            continue;
        }
        $item = json_decode($raw, true);
        if (is_array($item) && (string) ($item['id'] ?? '') === $id) {
            $found = true;
            continue;
        }
        $kept[] = is_array($item)
            ? json_encode($item, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES)
            : $raw;
    }
    ftruncate($fp, 0);
    rewind($fp);
    fwrite($fp, $kept ? (implode("\n", $kept) . "\n") : '');
    fflush($fp);
    flock($fp, LOCK_UN);
    fclose($fp);
    return $found;
}
