package com.example.ucp2pam.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AuditDao {
    @Insert
    suspend fun insertLog(log: LogAudit)

    @Query("SELECT * FROM log_audit ORDER BY waktu DESC")
    fun getAllLogs(): Flow<List<LogAudit>>
}
