package com.example.ucp2pam.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2pam.repositori.RepositoriBuku
import com.example.ucp2pam.room.Kategori

class TambahKategoriViewModel(private val repositoriBuku: RepositoriBuku) : ViewModel() {
    var uiState by mutableStateOf(KategoriUiState())
        private set

    fun updateUiState(kategoriEvent: KategoriEvent) {
        uiState = KategoriUiState(kategoriEvent = kategoriEvent)
    }

    suspend fun saveKategori() {
        repositoriBuku.insertKategori(uiState.kategoriEvent.toKategori())
    }
}

data class KategoriUiState(
    val kategoriEvent: KategoriEvent = KategoriEvent()
)

data class KategoriEvent(
    val idKategori: Int = 0,
    val nama: String = "",
    val deskripsi: String = "",
    val parentKategoriId: Int? = null
)

fun KategoriEvent.toKategori(): Kategori = Kategori(
    idKategori = idKategori,
    nama = nama,
    deskripsi = deskripsi,
    parentKategoriId = parentKategoriId
)
