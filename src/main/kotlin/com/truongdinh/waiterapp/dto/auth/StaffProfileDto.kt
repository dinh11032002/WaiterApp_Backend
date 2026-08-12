package com.truongdinh.waiterapp.dto.auth

import com.truongdinh.waiterapp.entity.Shift

data class StaffProfileDto(
    val id: Int,
    val username: String,
    val fullName: String,
    val shift: Shift
)
