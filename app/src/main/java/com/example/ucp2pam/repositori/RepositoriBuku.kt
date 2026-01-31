package com.example.ucp2pam.repositori

import com.example.ucp2pam.room.AuditDao
import com.example.ucp2pam.room.Buku
import com.example.ucp2pam.room.BukuDao
import com.example.ucp2pam.room.Kategori
import com.example.ucp2pam.room.KategoriDao
import com.example.ucp2pam.room.LogAudit
import com.example.ucp2pam.room.Penulis
import com.example.ucp2pam.room.PenulisDao
import kotlinx.coroutines.flow.Flow

interface RepositoriBuku {
    fun getAllBuku(): Flow<List<Buku>>
    fun getBuku(id: Int): Flow<Buku>
    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(buku: Buku)
    suspend fun deleteBuku(buku: Buku)

    fun getAllKategori(): Flow<List<Kategori>>
    fun getKategori(id: Int): Flow<Kategori>
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateKategori(kategori: Kategori)
    suspend fun deleteKategori(kategori: Kategori)

    fun getAllPenulis(): Flow<List<Penulis>>
    fun getPenulis(id: Int): Flow<Penulis>
    suspend fun insertPenulis(penulis: Penulis)
    suspend fun updatePenulis(penulis: Penulis)
    suspend fun deletePenulis(penulis: Penulis)
}

class OfflineRepositoriBuku(
    private val bukuDao: BukuDao,
    private val kategoriDao: KategoriDao,
    private val penulisDao: PenulisDao,
    private val auditDao: AuditDao
) : RepositoriBuku {

    override fun getAllBuku(): Flow<List<Buku>> = bukuDao.getAllBuku()

    override fun getBuku(id: Int): Flow<Buku> = bukuDao.getBukuById(id)

    override suspend fun insertBuku(buku: Buku) {
        bukuDao.insertBuku(buku)
        logAudit("Buku", "INSERT", buku.judul)
    }

    override suspend fun updateBuku(buku: Buku) {
        bukuDao.updateBuku(buku)
        logAudit("Buku", "UPDATE", buku.judul)
    }

    override suspend fun deleteBuku(buku: Buku) {
        bukuDao.softDeleteBuku(buku.idBuku)
        logAudit("Buku", "DELETE (Soft)", buku.judul)
    }

    override fun getAllKategori(): Flow<List<Kategori>> = kategoriDao.getAllKategori()

    override fun getKategori(id: Int): Flow<Kategori> = kategoriDao.getKategoriById(id)

    override suspend fun insertKategori(kategori: Kategori) {
        kategoriDao.insertKategori(kategori)
        logAudit("Kategori", "INSERT", kategori.nama)
    }

    override suspend fun updateKategori(kategori: Kategori) {
        kategoriDao.updateKategori(kategori)
        logAudit("Kategori", "UPDATE", kategori.nama)
    }

    override suspend fun deleteKategori(kategori: Kategori) {
        kategoriDao.softDeleteKategori(kategori.idKategori)
        bukuDao.removeCategoryFromBooks(kategori.idKategori)
        logAudit("Kategori", "DELETE (Soft)", kategori.nama)
    }

    override fun getAllPenulis(): Flow<List<Penulis>> = penulisDao.getAllPenulis()
    
    override fun getPenulis(id: Int): Flow<Penulis> = penulisDao.getPenulisById(id)

    override suspend fun insertPenulis(penulis: Penulis) {
        penulisDao.insertPenulis(penulis)
        logAudit("Penulis", "INSERT", penulis.namaPenulis)
    }

    override suspend fun updatePenulis(penulis: Penulis) {
    }

    override suspend fun deletePenulis(penulis: Penulis) {
        logAudit("Penulis", "DELETE", penulis.namaPenulis)
    }

    private suspend fun logAudit(tabel: String, aksi: String, dataBaru: String) {
        auditDao.insertLog(
            LogAudit(
                tabel = tabel,
                aksi = aksi,
                waktu = System.currentTimeMillis(),
                dataLama = null,
                dataBaru = dataBaru
            )
        )
    }
}
