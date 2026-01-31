package com.example.ucp2pam.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PenulisDao {
    @Insert
    suspend fun insertPenulis(penulis: Penulis)

    @Query("SELECT * FROM penulis ORDER BY namaPenulis ASC")
    fun getAllPenulis(): Flow<List<Penulis>>
    
    @Query("SELECT * FROM penulis WHERE idPenulis = :id")
    fun getPenulisById(id: Int): Flow<Penulis>
}
