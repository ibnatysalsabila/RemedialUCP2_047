package com.example.ucp2pam.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.repositori.RepositoriBuku
import com.example.ucp2pam.room.Buku
import com.example.ucp2pam.room.Kategori
import com.example.ucp2pam.room.Penulis
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TambahBukuViewModel(private val repositoriBuku: RepositoriBuku) : ViewModel() {
    var uiState by mutableStateOf(BukuUiState())
        private set

    val penulisList: StateFlow<List<Penulis>> = repositoriBuku.getAllPenulis()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val kategoriList: StateFlow<List<Kategori>> = repositoriBuku.getAllKategori()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateUiState(bukuEvent: BukuEvent) {
        uiState = BukuUiState(bukuEvent = bukuEvent)
    }

    suspend fun saveBuku() {
        repositoriBuku.insertBuku(uiState.bukuEvent.toBuku())
    }
}

data class BukuUiState(
    val bukuEvent: BukuEvent = BukuEvent()
)

data class BukuEvent(
    val idBuku: Int = 0,
    val judul: String = "",
    val idKategori: Int? = null,
    val idPenulis: Int = 0
)

fun BukuEvent.toBuku(): Buku = Buku(
    idBuku = idBuku,
    judul = judul,
    idKategori = idKategori,
    idPenulis = idPenulis
)
