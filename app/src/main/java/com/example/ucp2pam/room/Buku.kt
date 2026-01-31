package com.example.ucp2pam.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "buku",
    foreignKeys = [
        ForeignKey(
            entity = Kategori::class,
            parentColumns = ["idKategori"],
            childColumns = ["idKategori"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Penulis::class,
            parentColumns = ["idPenulis"],
            childColumns = ["idPenulis"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Buku(
    @PrimaryKey(autoGenerate = true)
    val idBuku: Int = 0,
    val judul: String,
    val idKategori: Int?,
    val idPenulis: Int,
    val isDeleted: Boolean = false
)
