package com.piusdev.core.network.http.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    val statusCode: Int,
    val description: String,
    val message: String,
    val code: String
)
