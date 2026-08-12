package com.truongdinh.waiterapp.service

import com.truongdinh.waiterapp.dto.table.CreateTableRequest
import com.truongdinh.waiterapp.dto.table.TableDto
import com.truongdinh.waiterapp.entity.TableEntity
import com.truongdinh.waiterapp.entity.TableStatus
import com.truongdinh.waiterapp.repository.TableRepository
import org.springframework.stereotype.Service

@Service
class TableService(
    private val tableRepository: TableRepository
) {
    fun getAllTables(): List<TableDto> {
        return tableRepository.findAll().map { it.toDto() }
    }
    
    fun getTablesByStatus(status: TableStatus?): List<TableDto> {
        if (status == null) {
            return tableRepository.findAll().map { it.toDto() }
        }
        
        return tableRepository.findByStatus(status).map { it.toDto() }
    }
    
    fun searchTables(query: String): List<TableDto> {
        return tableRepository.findByNameContainingIgnoreCase(query).map { it.toDto() }
    }
    
    fun createTable(request: CreateTableRequest): List<TableDto> {
        val tables = request.names.map { name ->
            TableEntity(
                name = name,
                status = TableStatus.EMPTY
            )
        }
        val saved = tableRepository.saveAll(tables)
        return saved.map { 
            it.toDto()
        }
    }
    
    private fun TableEntity.toDto() = TableDto(
        id = id,
        name = name,
        status = status
    )
}