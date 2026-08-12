package com.truongdinh.waiterapp.controller

import com.truongdinh.waiterapp.dto.ApiStatus
import com.truongdinh.waiterapp.dto.auth.LoginRequest
import com.truongdinh.waiterapp.dto.auth.LoginResponse
import com.truongdinh.waiterapp.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val authService: AuthService) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val response = authService.login(request)
        
        return if (response.status == ApiStatus.SUCCESS) {
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
        }
    }
}