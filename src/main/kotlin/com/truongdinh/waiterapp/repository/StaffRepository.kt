package com.truongdinh.waiterapp.repository

import com.truongdinh.waiterapp.entity.StaffEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StaffRepository : JpaRepository<StaffEntity, Int> {
    fun findByUsernameAndPassword(username: String, password: String): StaffEntity?
}