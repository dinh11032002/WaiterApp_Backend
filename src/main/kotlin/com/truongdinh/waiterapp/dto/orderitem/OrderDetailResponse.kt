package com.truongdinh.waiterapp.dto.orderitem

import com.truongdinh.waiterapp.entity.OrderStatus
import com.truongdinh.waiterapp.dto.orderitem.OrderItemDetailResponse
import java.time.LocalDateTime

data class OrderDetailResponse(
    val orderId: Long,
    val tableId: Int,
    val staffId: Int,
    val status: OrderStatus,
    val createdAt: LocalDateTime,
    val items: List<OrderItemDetailResponse>
) 