package com.example.ucp2pam.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2pam.repositori.RepositoriBuku
import com.example.ucp2pam.room.Penulis

class TambahPenulisViewModel(private val repositoriBuku: RepositoriBuku) : ViewModel() {
    var uiState by mutableStateOf(PenulisUiState())
        private set

    fun updateUiState(penulisEvent: PenulisEvent) {
        uiState = PenulisUiState(penulisEvent = penulisEvent)
    }

    suspend fun savePenulis() {
        repositoriBuku.insertPenulis(uiState.penulisEvent.toPenulis())
    }
}

data class PenulisUiState(
    val penulisEvent: PenulisEvent = PenulisEvent()
)

data class PenulisEvent(
    val idPenulis: Int = 0,
    val namaPenulis: String = ""
)

fun PenulisEvent.toPenulis(): Penulis = Penulis(
    idPenulis = idPenulis,
    namaPenulis = namaPenulis
)
