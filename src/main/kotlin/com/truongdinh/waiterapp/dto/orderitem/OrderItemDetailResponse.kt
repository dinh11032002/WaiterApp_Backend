package com.truongdinh.waiterapp.dto.orderitem

data class OrderItemDetailResponse (
    val orderItemId: Int,
    val menuItemId: Int,
    val name: String,
    val quantity: Int,
    val unitPrice: Long,
    val image: String?
)