package com.example.ucp2pam.repositori

import android.content.Context
import com.example.ucp2pam.room.DatabasePerpustakaan

interface AppContainer {
    val repositoriBuku: RepositoriBuku
}

class AppContainerImpl(private val context: Context) : AppContainer {
    override val repositoriBuku: RepositoriBuku by lazy {
        val db = DatabasePerpustakaan.getDatabase(context)
        OfflineRepositoriBuku(db.bukuDao(), db.kategoriDao(), db.penulisDao(), db.auditDao())
    }
}
