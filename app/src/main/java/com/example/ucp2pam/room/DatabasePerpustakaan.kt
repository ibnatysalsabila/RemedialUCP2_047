package com.example.ucp2pam.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Buku::class,
        Kategori::class,
        Penulis::class,
        LogAudit::class
    ],
    version = 3,
    exportSchema = false
)
abstract class DatabasePerpustakaan : RoomDatabase() {
    abstract fun bukuDao(): BukuDao
    abstract fun kategoriDao(): KategoriDao
    abstract fun penulisDao(): PenulisDao
    abstract fun auditDao(): AuditDao

    companion object {
        @Volatile
        private var Instance: DatabasePerpustakaan? = null

        fun getDatabase(context: Context): DatabasePerpustakaan {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabasePerpustakaan::class.java,
                    "perpustakaan_db"
                )
                .fallbackToDestructiveMigration()
                .build().also { Instance = it }
            }
        }
    }
}
