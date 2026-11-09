<?php
require __DIR__ . '/stats-lib.php';

$to = stats_safe_redirect((string) ($_GET['to'] ?? ''));
if ($to === '') {
    $to = stats_safe_redirect(stats_default_apk());
}
if ($to === '') {
    http_response_code(400);
    header('Content-Type: text/plain; charset=utf-8');
    echo 'missing apk url';
    exit;
}

stats_append_event([
    'type' => 'download',
    'sid' => $_GET['sid'] ?? '',
    'fbclid' => $_GET['fbclid'] ?? '',
    'source_ad' => $_GET['source_ad'] ?? '',
    'utm_campaign' => $_GET['utm_campaign'] ?? '',
    'utm_source' => $_GET['utm_source'] ?? '',
    'ad_id' => $_GET['ad_id'] ?? '',
    'referrer' => $_SERVER['HTTP_REFERER'] ?? '',
]);

header('Cache-Control: no-store');
header('Location: ' . $to, true, 302);
exit;
