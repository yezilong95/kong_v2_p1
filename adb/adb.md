https://developer.android.com/tools/releases/platform-tools
-从上面的地址下载适用于 Linux 的 SDK Platform-Tools，或直接上传下载好的压缩包 adb/platform-tools-latest-linux.zip
-上传到宝塔的/var/目录并解压
-在 api 项目的.env中配置一行 ADB_BIN=/var/platform-tools/adb
-重启 api 项目

nginx /api/ 加这两行
proxy_read_timeout 180s;
proxy_send_timeout 180s;