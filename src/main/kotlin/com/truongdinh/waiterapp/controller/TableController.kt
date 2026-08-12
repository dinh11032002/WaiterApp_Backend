package com.truongdinh.waiterapp.controller

import com.truongdinh.waiterapp.dto.table.CreateTableRequest
import com.truongdinh.waiterapp.dto.table.TableDto
import com.truongdinh.waiterapp.entity.TableStatus
import com.truongdinh.waiterapp.service.TableService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/tables")
class TableController(
    private val tableService: TableService
) {
    @GetMapping
    fun getTables(
        @RequestParam(required = false) status: TableStatus?,
        @RequestParam(required = false) query: String?
    ): ResponseEntity<List<TableDto>> {
        val result = when {
            status != null -> tableService.getTablesByStatus(status)
            !query.isNullOrBlank() -> tableService.searchTables(query)
            else -> tableService.getAllTables()
        }
        return ResponseEntity.ok(result)
    }
    
    @PostMapping
    fun createTable(@RequestBody request: CreateTableRequest): ResponseEntity<List<TableDto>> {
        val tables = tableService.createTable(request)
        return ResponseEntity.ok(tables)
    }
}