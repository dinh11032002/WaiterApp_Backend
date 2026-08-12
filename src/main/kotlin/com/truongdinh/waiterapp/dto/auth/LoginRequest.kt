package com.truongdinh.waiterapp.dto.auth

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class LoginRequest(
    @field:NotBlank(message = "Tên đăng nhập không được để trống")
    val username: String,
        
    @field:NotBlank(message = "Mật khẩu không được để trống")
    @field:Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    val password: String,
)