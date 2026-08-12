package com.truongdinh.waiterapp.dto.cart

data class CartReplaceRequest(
    val tableId: Int,
    val oldMenuItemId: Int,
    val newMenuItemId: Int
)
