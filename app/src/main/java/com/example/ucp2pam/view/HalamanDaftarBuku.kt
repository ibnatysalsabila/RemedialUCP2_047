package com.example.ucp2pam.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.room.Buku
import com.example.ucp2pam.view.uicontroller.CustomTopAppBar
import com.example.ucp2pam.viewmodel.BukuViewModel
import com.example.ucp2pam.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanDaftarBuku(
    onAddBuku: () -> Unit,
    onBack: () -> Unit,
    onDetailClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiStateBuku by viewModel.uiStateBuku.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                judul = "Daftar Buku",
                canNavigateBack = true,
                navigateUp = onBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBuku,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Buku")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (uiStateBuku.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text("Tidak ada data buku.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiStateBuku) { buku ->
                        CardBuku(
                            buku = buku,
                            modifier = Modifier.fillMaxWidth().clickable { onDetailClick(buku.idBuku) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardBuku(
    buku: Buku,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = buku.judul, style = MaterialTheme.typography.titleMedium)
            Text(text = "ID: ${buku.idBuku}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
