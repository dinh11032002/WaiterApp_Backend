package com.truongdinh.waiterapp.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.truongdinh.waiterapp.repository.MenuItemRepository
import com.truongdinh.waiterapp.repository.OrderRepository
import com.truongdinh.waiterapp.repository.OrderItemRepository
import com.truongdinh.waiterapp.dto.order.PlaceOrderRequest
import com.truongdinh.waiterapp.dto.order.PlaceOrderResponse
import com.truongdinh.waiterapp.dto.orderitem.OrderDetailResponse
import com.truongdinh.waiterapp.dto.orderitem.OrderItemDetailResponse
import com.truongdinh.waiterapp.entity.OrderEntity
import com.truongdinh.waiterapp.entity.OrderItemEntity
import com.truongdinh.waiterapp.entity.OrderStatus
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val menuItemRepository: MenuItemRepository
) {
    @Transactional
    fun placeOrder(request: PlaceOrderRequest): PlaceOrderResponse {
        require(request.items.isNotEmpty()) { "Order must have at least one item" }

        val order = orderRepository.save(
            OrderEntity(
                tableId = request.tableId,
                staffId = 1,
                status = OrderStatus.PREPARING,
                createdAt = LocalDateTime.now()
            )
        )

        request.items.forEach { itemRequest ->
            val menuItem = menuItemRepository.findById(itemRequest.menuItemId)
                .orElseThrow { IllegalArgumentException("MenuItem ${itemRequest.menuItemId} not found") }

            orderItemRepository.save(
                OrderItemEntity(
                    orderId = order.id,
                    menuItemId = menuItem.id,
                    quantity = itemRequest.quantity,
                    unitPrice = menuItem.price
                )
            )
        }

        return PlaceOrderResponse(orderId = order.id.toLong(), status = order.status)
    }

    @Transactional(readOnly = true)
    fun getOrderDetail(orderId: Int): OrderDetailResponse {
        val order = orderRepository.findById(orderId)
            .orElseThrow { IllegalArgumentException("Order with id $orderId not found") }

        val items = orderItemRepository.findByOrderId(orderId)

       val menuById = menuItemRepository
            .findAllById(items.map { it.menuItemId })
            .associateBy { it.id }
       
       return OrderDetailResponse(
            orderId = order.id.toLong(),
            tableId = order.tableId,
            staffId = order.staffId,
            status = order.status,
            createdAt = order.createdAt,
            items = items.map { item ->
            val menu = menuById[item.menuItemId]
                OrderItemDetailResponse(
                    orderItemId = item.id,
                    menuItemId = item.menuItemId,
                    name = menu?.name ?: "Món không xác định",
                    quantity = item.quantity,
                    unitPrice = item.unitPrice,
                    image = menu?.image
                )
            }
       )
    }
}