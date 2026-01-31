package com.example.ucp2pam.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.view.uicontroller.CustomTopAppBar
import com.example.ucp2pam.viewmodel.TambahPenulisViewModel
import com.example.ucp2pam.viewmodel.PenyediaViewModel
import com.example.ucp2pam.viewmodel.PenulisEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanTambahPenulis(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TambahPenulisViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                judul = "Tambah Penulis",
                canNavigateBack = true,
                navigateUp = onBack
            )
        }
    ) { innerPadding ->
        EntryPenulisBody(
            penulisUiState = viewModel.uiState,
            onPenulisValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.savePenulis()
                    onNavigate()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun EntryPenulisBody(
    penulisUiState: com.example.ucp2pam.viewmodel.PenulisUiState,
    onPenulisValueChange: (PenulisEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInputPenulis(
            penulisEvent = penulisUiState.penulisEvent,
            onValueChange = onPenulisValueChange,
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
fun FormInputPenulis(
    penulisEvent: PenulisEvent,
    modifier: Modifier = Modifier,
    onValueChange: (PenulisEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = penulisEvent.namaPenulis,
            onValueChange = { onValueChange(penulisEvent.copy(namaPenulis = it)) },
            label = { Text("Nama Penulis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}
