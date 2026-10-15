package com.truongdinh.waiterapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.truongdinh.waiterapp.entity.OrderItemEntity

interface OrderItemRepository : JpaRepository<OrderItemEntity, Int> {
    fun findByOrderId(orderId: Int): List<OrderItemEntity>
}