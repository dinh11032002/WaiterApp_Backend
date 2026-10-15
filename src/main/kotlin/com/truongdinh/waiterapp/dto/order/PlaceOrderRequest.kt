package com.truongdinh.waiterapp.dto.order

import com.truongdinh.waiterapp.dto.orderitem.OrderItemRequest

data class PlaceOrderRequest(
    val tableId: Int,
    val items: List<OrderItemRequest>
)