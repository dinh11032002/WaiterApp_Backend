package com.truongdinh.waiterapp.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType

@Entity
@Table(name = "order_items")
data class OrderItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val orderId: Int = 0,
    val menuItemId: Int = 0,
    val quantity: Int = 0,
    val unitPrice: Long = 0L
)