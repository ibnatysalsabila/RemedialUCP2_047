package com.example.ucp2pam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.repositori.RepositoriBuku
import com.example.ucp2pam.room.Buku
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class BukuViewModel(private val repositoriBuku: RepositoriBuku) : ViewModel() {

    val uiStateBuku: StateFlow<List<Buku>> = repositoriBuku.getAllBuku()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
