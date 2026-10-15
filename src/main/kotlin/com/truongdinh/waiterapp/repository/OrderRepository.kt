package com.truongdinh.waiterapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.truongdinh.waiterapp.entity.OrderEntity

interface OrderRepository : JpaRepository<OrderEntity, Int>