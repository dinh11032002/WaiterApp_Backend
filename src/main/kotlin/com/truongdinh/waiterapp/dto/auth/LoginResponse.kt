package com.truongdinh.waiterapp.dto.auth

import com.truongdinh.waiterapp.dto.ApiStatus
import com.truongdinh.waiterapp.dto.auth.StaffProfileDto

data class LoginResponse(
    val status: ApiStatus,
    val message: String,
    val token: String? = null,
    val staff: StaffProfileDto? = null
)