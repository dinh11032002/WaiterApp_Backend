package com.truongdinh.waiterapp.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType

@Entity
@Table(name = "staffs")
data class StaffEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val fullName: String = "",
    val username: String = "",
    val password: String = "",
    @Enumerated(EnumType.STRING)
    val shift: Shift = Shift.MORNING
)
