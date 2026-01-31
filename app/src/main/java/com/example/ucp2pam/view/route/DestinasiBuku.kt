package com.example.ucp2pam.view.route

object DestinasiBuku : DestinasiNavigasi {
    override val route = "buku"
    override val titleRes = "Daftar Buku"
}

object DestinasiTambahBuku : DestinasiNavigasi {
    override val route = "tambah_buku"
    override val titleRes = "Tambah Buku"
}

object DestinasiDetailBuku : DestinasiNavigasi {
    override val route = "detail_buku"
    override val titleRes = "Detail Buku"
    const val idBuku = "idBuku"
    val routeWithArgs = "$route/{$idBuku}"
}

object DestinasiUpdateBuku : DestinasiNavigasi {
    override val route = "update_buku"
    override val titleRes = "Update Buku"
    const val idBuku = "idBuku"
    val routeWithArgs = "$route/{$idBuku}"
}
