package com.example.ucp2pam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.ucp2pam.ui.theme.UCP2PAMTheme
import com.example.ucp2pam.view.uicontroller.PetaNavigasi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UCP2PAMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PetaNavigasi()
                }
            }
        }
    }
}