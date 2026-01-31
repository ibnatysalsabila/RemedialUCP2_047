package com.example.ucp2pam.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2pam.AplikasiPerpustakaan

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            BukuViewModel(aplikasiPerpustakaan().container.repositoriBuku)
        }
        initializer {
            TambahPenulisViewModel(aplikasiPerpustakaan().container.repositoriBuku)
        }
        initializer {
            TambahKategoriViewModel(aplikasiPerpustakaan().container.repositoriBuku)
        }
        initializer {
            TambahBukuViewModel(aplikasiPerpustakaan().container.repositoriBuku)
        }
    }
}

fun CreationExtras.aplikasiPerpustakaan(): AplikasiPerpustakaan =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiPerpustakaan)
