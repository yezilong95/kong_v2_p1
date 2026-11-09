plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    // FCM 用：只登记到 classpath，是否真正 apply 由 :app 按 google-services.json 是否存在/匹配决定
    id("com.google.gms.google-services") version "4.4.4" apply false
}
