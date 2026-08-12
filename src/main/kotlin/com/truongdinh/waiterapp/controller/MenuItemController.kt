package com.truongdinh.waiterapp.controller

import com.truongdinh.waiterapp.dto.menu.CreateMenuItemRequest
import com.truongdinh.waiterapp.dto.menu.MenuItemDto
import com.truongdinh.waiterapp.service.MenuItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/menu-items")
class MenuItemController(
    private val menuItemService: MenuItemService
) {
    @GetMapping
    fun getAllMenuItems(
        @RequestParam(required = false) categoryId: Int?,
        @RequestParam(required = false) name: String?
    ): ResponseEntity<List<MenuItemDto>> {
        val result = when {
            categoryId != null -> menuItemService.getMenuItemsByCategoryId(categoryId)
            !name.isNullOrBlank() -> menuItemService.searchMenuItemsByName(name)
            else -> menuItemService.getAllMenuItems()
        }
        return ResponseEntity.ok(result)
    }
    
    
    
    @PostMapping
    fun createMenuItems(@RequestBody request: CreateMenuItemRequest): ResponseEntity<List<MenuItemDto>> {
        val menuItems = menuItemService.createMenuItems(request)
        return ResponseEntity.ok(menuItems)
    }
}