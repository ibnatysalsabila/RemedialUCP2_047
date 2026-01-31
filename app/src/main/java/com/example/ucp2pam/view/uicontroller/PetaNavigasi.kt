package com.example.ucp2pam.view.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2pam.view.HalamanDaftarBuku
import com.example.ucp2pam.view.HalamanHome
import com.example.ucp2pam.view.HalamanTambahBuku
import com.example.ucp2pam.view.HalamanTambahKategori
import com.example.ucp2pam.view.HalamanTambahPenulis
import com.example.ucp2pam.view.route.DestinasiBuku
import com.example.ucp2pam.view.route.DestinasiHome
import com.example.ucp2pam.view.route.DestinasiTambahBuku
import com.example.ucp2pam.view.route.DestinasiTambahKategori
import com.example.ucp2pam.view.route.DestinasiTambahPenulis
import com.example.ucp2pam.view.route.DestinasiKategori
import com.example.ucp2pam.view.route.DestinasiPenulis

@Composable
fun PetaNavigasi(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HalamanHome(
                onBukuClick = { navController.navigate(DestinasiBuku.route) },
                onPenulisClick = { navController.navigate(DestinasiTambahPenulis.route) },
                onKategoriClick = { navController.navigate(DestinasiTambahKategori.route) }
            )
        }

        composable(DestinasiBuku.route) {
            HalamanDaftarBuku(
                onBack = { navController.popBackStack() },
                onAddBuku = { navController.navigate(DestinasiTambahBuku.route) },
                onDetailClick = { }
            )
        }
        
        composable(DestinasiTambahBuku.route) {
            HalamanTambahBuku(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }

        composable(DestinasiTambahKategori.route) {
            HalamanTambahKategori(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }

        composable(DestinasiTambahPenulis.route) {
            HalamanTambahPenulis(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }
    }
}
