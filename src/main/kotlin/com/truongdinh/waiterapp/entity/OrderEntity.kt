package com.truongdinh.waiterapp.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import java.time.LocalDateTime
import com.truongdinh.waiterapp.entity.OrderStatus

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val tableId: Int = 0,
    val staffId: Int = 0,
    
    @Enumerated(EnumType.STRING)
    val status: OrderStatus = OrderStatus.OPEN,

    val createdAt: LocalDateTime = LocalDateTime.now()
)