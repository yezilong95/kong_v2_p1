package com.vibelink.client.network

/**
 * 识别服务端「这个 token 我不认识」的关闭码。
 *
 * 服务端在 `getDeviceByToken` 查库落空时执行 `socket.close(4401, "invalid_token")`，
 * 三个通道（控制 / 画面 / ADB 中继）用的是同一个写法。4400-4999 是 WebSocket
 * 留给应用自定义的区间，所以 4401 本身不带标准语义，必须由两端约定。
 *
 * reason 也一起参与判定：将来同一个码若被复用作别的含义，只看码会误清凭据。
 * 但 reason 允许为空——OkHttp 在个别关闭路径上拿不到 reason，那时按码认定，
 * 因为服务端目前只在这个场景下用 4401。
 */
object DeviceAuthRejection {
    private const val CODE_INVALID_TOKEN = 4401
    private const val REASON_INVALID_TOKEN = "invalid_token"

    /** 是否需要丢弃本地凭据并重新注册。 */
    fun isInvalidToken(code: Int, reason: String): Boolean {
        if (code != CODE_INVALID_TOKEN) {
            return false
        }
        if (reason.isBlank()) {
            return true
        }
        return reason.contains(REASON_INVALID_TOKEN, ignoreCase = true)
    }
}
