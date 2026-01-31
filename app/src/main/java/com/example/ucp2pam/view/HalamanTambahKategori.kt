package com.example.ucp2pam.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.view.uicontroller.CustomTopAppBar
import com.example.ucp2pam.viewmodel.TambahKategoriViewModel
import com.example.ucp2pam.viewmodel.PenyediaViewModel
import com.example.ucp2pam.viewmodel.KategoriEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanTambahKategori(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TambahKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                judul = "Tambah Kategori",
                canNavigateBack = true,
                navigateUp = onBack
            )
        }
    ) { innerPadding ->
        EntryKategoriBody(
            kategoriUiState = viewModel.uiState,
            onKategoriValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveKategori()
                    onNavigate()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun EntryKategoriBody(
    kategoriUiState: com.example.ucp2pam.viewmodel.KategoriUiState,
    onKategoriValueChange: (KategoriEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInputKategori(
            kategoriEvent = kategoriUiState.kategoriEvent,
            onValueChange = onKategoriValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputKategori(
    kategoriEvent: KategoriEvent,
    modifier: Modifier = Modifier,
    onValueChange: (KategoriEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = kategoriEvent.nama,
            onValueChange = { onValueChange(kategoriEvent.copy(nama = it)) },
            label = { Text("Nama Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = kategoriEvent.deskripsi,
            onValueChange = { onValueChange(kategoriEvent.copy(deskripsi = it)) },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = false,
            minLines = 3
        )
    }
}
