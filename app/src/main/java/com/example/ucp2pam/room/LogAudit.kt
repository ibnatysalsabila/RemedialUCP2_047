package com.example.ucp2pam.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "log_audit")
data class LogAudit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tabel: String,
    val aksi: String,
    val waktu: Long,
    val dataLama: String?,
    val dataBaru: String?
)
