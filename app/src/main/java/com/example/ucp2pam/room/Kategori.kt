package com.example.ucp2pam.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "kategori",
    foreignKeys = [
        ForeignKey(
            entity = Kategori::class,
            parentColumns = ["idKategori"],
            childColumns = ["parentKategoriId"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Kategori(
    @PrimaryKey(autoGenerate = true)
    val idKategori: Int = 0,
    val nama: String,
    val deskripsi: String,
    val parentKategoriId: Int? = null,
    val isDeleted: Boolean = false
)
