package com.vibelink.client.model

data class RegisterDeviceRequest(
    val androidId: String,
    val model: String?,
)

data class RegisterDeviceResponse(
    val deviceId: String,
    val deviceToken: String,
)

data class ApiEnvelope<T>(
    val ok: Boolean,
    val data: T?,
    val message: String? = null,
)
