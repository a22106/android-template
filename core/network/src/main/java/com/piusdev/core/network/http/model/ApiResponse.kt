package com.piusdev.core.network.http.model

import kotlinx.serialization.Serializable

@Serializable
private data class ApiResponse<T>(
    val data: T,
)