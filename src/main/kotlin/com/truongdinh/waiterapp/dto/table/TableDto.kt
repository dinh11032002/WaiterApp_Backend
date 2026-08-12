package com.truongdinh.waiterapp.dto.table

import com.truongdinh.waiterapp.entity.TableStatus

data class TableDto(
    val id: Int,
    val name: String,
    val status: TableStatus
)
