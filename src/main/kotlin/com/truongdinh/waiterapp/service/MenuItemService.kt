package com.truongdinh.waiterapp.service

import com.truongdinh.waiterapp.dto.menu.CreateMenuItemRequest
import com.truongdinh.waiterapp.dto.menu.MenuItemDto
import com.truongdinh.waiterapp.entity.MenuItemEntity
import com.truongdinh.waiterapp.repository.MenuItemRepository
import org.springframework.stereotype.Service

@Service
class MenuItemService(
    private val menuItemRepository: MenuItemRepository
) {
    fun getAllMenuItems(): List<MenuItemDto> {
        return menuItemRepository.findAll().map { it.toDto() }
    }
    
    fun getMenuItemsByCategoryId(categoryId: Int): List<MenuItemDto> {
        return menuItemRepository.findByCategoryId(categoryId).map { it.toDto() }
    }
    
    fun searchMenuItemsByName(name: String): List<MenuItemDto> {
        return menuItemRepository.searchByName(name).map { it.toDto() }
    }
    
    fun createMenuItems(request: CreateMenuItemRequest): List<MenuItemDto> {
        val menuItems = request.menuItems.map { menuItems ->
            MenuItemEntity(
                id = menuItems.id,
                name = menuItems.name,
                price = menuItems.price,
                image = menuItems.image,
                categoryId = menuItems.categoryId,
                isAvailable = menuItems.isAvailable
            )
        }
        val saved = menuItemRepository.saveAll(menuItems)
        return saved.map {
            it.toDto()
        }
    }
    
    private fun MenuItemEntity.toDto(): MenuItemDto = MenuItemDto(
        id = id,
        name = name,
        price = price,
        image = image,
        categoryId = categoryId,
        isAvailable = isAvailable
    )
}