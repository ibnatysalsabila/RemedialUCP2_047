package com.example.ucp2pam.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ucp2pam.view.uicontroller.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanHome(
    onBukuClick: () -> Unit,
    onPenulisClick: () -> Unit,
    onKategoriClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                judul = "Perpustakaan Universitas",
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onBukuClick,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Manajemen Buku")
            }
            Button(
                onClick = onPenulisClick,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Manajemen Penulis")
            }
            Button(
                onClick = onKategoriClick,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Manajemen Kategori")
            }
        }
    }
}
