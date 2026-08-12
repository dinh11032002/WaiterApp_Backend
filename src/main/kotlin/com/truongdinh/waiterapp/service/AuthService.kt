package com.truongdinh.waiterapp.service

import com.truongdinh.waiterapp.dto.ApiStatus
import com.truongdinh.waiterapp.dto.auth.LoginRequest
import com.truongdinh.waiterapp.dto.auth.LoginResponse
import com.truongdinh.waiterapp.dto.auth.StaffProfileDto
import com.truongdinh.waiterapp.entity.Shift
import com.truongdinh.waiterapp.repository.StaffRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val staffRepository: StaffRepository
) {
    fun login(request: LoginRequest): LoginResponse {
        val staff = staffRepository.findByUsernameAndPassword(
            request.username,
            request.password
        )
        
        return if (staff != null) {
            LoginResponse(
                status = ApiStatus.SUCCESS,
                message = "Đăng nhập thành công!",
                token = "mock-token-for-${request.username}",
                staff = StaffProfileDto(
                    id = staff.id,
                    fullName = staff.fullName,
                    username = staff.username,
                    shift = staff.shift
                )
            )
        } else {
            LoginResponse(
                status = ApiStatus.FAIL,
                message = "Tài khoản hoặc mật khẩu không đúng!"
            )
        }
    }
}