package com.truongdinh.waiterapp.controller

import com.truongdinh.waiterapp.service.OrderService
import com.truongdinh.waiterapp.dto.order.PlaceOrderRequest
import com.truongdinh.waiterapp.dto.order.PlaceOrderResponse
import com.truongdinh.waiterapp.dto.orderitem.OrderDetailResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("api/v1/orders")
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping
    fun placeOrder(@RequestBody request: PlaceOrderRequest): ResponseEntity<PlaceOrderResponse> {
        val response = orderService.placeOrder(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{orderId}")
    fun getOrderDetail(@PathVariable orderId: Int): ResponseEntity<OrderDetailResponse> {
        val response = orderService.getOrderDetail(orderId)
        return ResponseEntity.ok(response)
    }
}