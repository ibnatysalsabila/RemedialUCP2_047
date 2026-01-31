package com.example.ucp2pam.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.view.uicontroller.CustomTopAppBar
import com.example.ucp2pam.viewmodel.TambahBukuViewModel
import com.example.ucp2pam.viewmodel.PenyediaViewModel
import com.example.ucp2pam.viewmodel.BukuEvent
import com.example.ucp2pam.room.Penulis
import com.example.ucp2pam.room.Kategori
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanTambahBuku(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TambahBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val penulisList by viewModel.penulisList.collectAsState()
    val kategoriList by viewModel.kategoriList.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                judul = "Tambah Buku",
                canNavigateBack = true,
                navigateUp = onBack
            )
        }
    ) { innerPadding ->
        EntryBukuBody(
            bukuUiState = viewModel.uiState,
            penulisList = penulisList,
            kategoriList = kategoriList,
            onBukuValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveBuku()
                    onNavigate()
                }
            },
            modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun EntryBukuBody(
    bukuUiState: com.example.ucp2pam.viewmodel.BukuUiState,
    penulisList: List<Penulis>,
    kategoriList: List<Kategori>,
    onBukuValueChange: (BukuEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInputBuku(
            bukuEvent = bukuUiState.bukuEvent,
            penulisList = penulisList,
            kategoriList = kategoriList,
            onValueChange = onBukuValueChange,
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
fun FormInputBuku(
    bukuEvent: BukuEvent,
    penulisList: List<Penulis>,
    kategoriList: List<Kategori>,
    modifier: Modifier = Modifier,
    onValueChange: (BukuEvent) -> Unit = {},
    enabled: Boolean = true
) {
    var expandedPenulis by remember { mutableStateOf(false) }
    var expandedKategori by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = bukuEvent.judul,
            onValueChange = { onValueChange(bukuEvent.copy(judul = it)) },
            label = { Text("Judul Buku") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        ExposedDropdownMenuBox(
            expanded = expandedPenulis,
            onExpandedChange = { expandedPenulis = !expandedPenulis }
        ) {
            OutlinedTextField(
                value = penulisList.find { it.idPenulis == bukuEvent.idPenulis }?.namaPenulis ?: "Pilih Penulis",
                onValueChange = {},
                readOnly = true,
                label = { Text("Penulis") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPenulis) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                enabled = enabled
            )
            ExposedDropdownMenu(
                expanded = expandedPenulis,
                onDismissRequest = { expandedPenulis = false }
            ) {
                penulisList.forEach { penulis ->
                    DropdownMenuItem(
                        text = { Text(penulis.namaPenulis) },
                        onClick = {
                            onValueChange(bukuEvent.copy(idPenulis = penulis.idPenulis))
                            expandedPenulis = false
                        }
                    )
                }
            }
        }

        ExposedDropdownMenuBox(
            expanded = expandedKategori,
            onExpandedChange = { expandedKategori = !expandedKategori }
        ) {
            OutlinedTextField(
                value = kategoriList.find { it.idKategori == bukuEvent.idKategori }?.nama ?: "Pilih Kategori",
                onValueChange = {},
                readOnly = true,
                label = { Text("Kategori") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedKategori) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                enabled = enabled
            )
            ExposedDropdownMenu(
                expanded = expandedKategori,
                onDismissRequest = { expandedKategori = false }
            ) {
                kategoriList.forEach { kategori ->
                    DropdownMenuItem(
                        text = { Text(kategori.nama) },
                        onClick = {
                            onValueChange(bukuEvent.copy(idKategori = kategori.idKategori))
                            expandedKategori = false
                        }
                    )
                }
            }
        }
    }
}
