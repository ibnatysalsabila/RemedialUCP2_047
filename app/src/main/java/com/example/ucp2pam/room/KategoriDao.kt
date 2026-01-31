package com.example.ucp2pam.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface KategoriDao {
    @Insert
    suspend fun insertKategori(kategori: Kategori)

    @Update
    suspend fun updateKategori(kategori: Kategori)

    @Query("SELECT * FROM kategori WHERE isDeleted = 0")
    fun getAllKategori(): Flow<List<Kategori>>

    @Query("SELECT * FROM kategori WHERE idKategori = :id")
    fun getKategoriById(id: Int): Flow<Kategori>

    @Query("UPDATE kategori SET isDeleted = 1 WHERE idKategori = :id")
    suspend fun softDeleteKategori(id: Int)

    @Query("SELECT * FROM kategori WHERE parentKategoriId = :parentId AND isDeleted = 0")
    suspend fun getSubKategori(parentId: Int): List<Kategori>
}
