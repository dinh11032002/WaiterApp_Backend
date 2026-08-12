package com.truongdinh.waiterapp.service

import com.truongdinh.waiterapp.dto.ApiStatus
import com.truongdinh.waiterapp.dto.auth.LoginRequest
import com.truongdinh.waiterapp.entity.Shift
import com.truongdinh.waiterapp.entity.StaffEntity
import com.truongdinh.waiterapp.repository.StaffRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals


@ExtendWith(MockitoExtension::class)
class AuthServiceTest {
    @Mock
    lateinit var staffRepository: StaffRepository
    
    @Test
    fun `login success`() {
        val authService = AuthService(staffRepository)
        
        val staff = StaffEntity(
            id = 1,
            username = "waiter01",
            fullName = "Trương Đình",
            password = "123456",
            shift = Shift.MORNING
        )
        
        `when`(staffRepository.findByUsernameAndPassword("waiter01", "123456"))
            .thenReturn(staff)
            
        val response = authService.login(LoginRequest("waiter01", "123456"))
        
        assertEquals(ApiStatus.SUCCESS, response.status)
        assertEquals("waiter01", response.staff?.username)
    }
    
    @Test
    fun `login fail`() {
        val authService = AuthService(staffRepository)
        
        val staff = StaffEntity(
            id = 1,
            username = "waiter01",
            fullName = "Trương Đình",
            password = "654321",
            shift = Shift.MORNING
        )
        
        `when`(staffRepository.findByUsernameAndPassword("waiter01", "654321"))
            .thenReturn(null)
            
        val response = authService.login(LoginRequest("waiter01", "654321"))
            
        assertEquals(ApiStatus.FAIL, response.status)
        assertEquals("Tài khoản hoặc mật khẩu không đúng!", response.message)
    }
}