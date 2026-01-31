package com.example.ucp2pam

import android.app.Application
import com.example.ucp2pam.repositori.AppContainer
import com.example.ucp2pam.repositori.AppContainerImpl

class AplikasiPerpustakaan : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}
