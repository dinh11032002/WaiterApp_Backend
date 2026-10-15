package com.truongdinh.waiterapp.dto.order

import com.truongdinh.waiterapp.entity.OrderStatus

data class PlaceOrderResponse(
    val orderId: Long,
    val status: OrderStatus
)