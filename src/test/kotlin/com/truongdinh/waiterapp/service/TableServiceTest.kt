package com.truongdinh.waiterapp.service

import com.truongdinh.waiterapp.entity.TableEntity
import com.truongdinh.waiterapp.entity.TableStatus
import com.truongdinh.waiterapp.repository.TableRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class TableServiceTest {
    @Mock
    lateinit var tableRepository: TableRepository
    
    @Test
    fun `search table by name`() {
        val tableService = TableService(tableRepository)
        
        val tables = TableEntity(
                id = 1,
                name = "Bàn 1",
                status = TableStatus.EMPTY
        )
        
        `when`(tableRepository.findByNameContainingIgnoreCase("Bàn 1"))
            .thenReturn(listOf(tables))
            
        val result = tableService.searchTables("Bàn 1")
            
        assertEquals(1, result.size)
        assertEquals("Bàn 1", result[0].name)
    }
    
    @Test
    fun `filter table by status`() {
        val tableService = TableService(tableRepository)
        
        val tables = TableEntity(
            id = 1,
            name = "Bàn 1",
            status = TableStatus.EMPTY
        )
        
        `when`(tableRepository.findByStatus(TableStatus.EMPTY))
            .thenReturn(listOf(tables))
            
        val result = tableService.getTablesByStatus(TableStatus.EMPTY)
        
        assertEquals(1, result.size)
        assertEquals(TableStatus.EMPTY, result[0].status)
    }
}