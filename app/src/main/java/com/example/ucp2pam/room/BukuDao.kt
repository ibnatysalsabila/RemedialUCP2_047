package com.example.ucp2pam.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BukuDao {
    @Insert
    suspend fun insertBuku(buku: Buku)

    @Update
    suspend fun updateBuku(buku: Buku)

    @Query("SELECT * FROM buku WHERE isDeleted = 0")
    fun getAllBuku(): Flow<List<Buku>>

    @Query("SELECT * FROM buku WHERE idBuku = :id")
    fun getBukuById(id: Int): Flow<Buku>

    @Query("UPDATE buku SET isDeleted = 1 WHERE idBuku = :id")
    suspend fun softDeleteBuku(id: Int)
    
    @Query("UPDATE buku SET idKategori = NULL WHERE idKategori = :kategoriId")
    suspend fun removeCategoryFromBooks(kategoriId: Int)
}
