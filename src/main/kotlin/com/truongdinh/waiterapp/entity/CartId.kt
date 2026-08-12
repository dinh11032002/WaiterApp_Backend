package com.truongdinh.waiterapp.entity

import java.io.Serializable

data class CartId(
    val tableId: Int = 0,
    val menuItemId: Int = 0
) : Serializable
