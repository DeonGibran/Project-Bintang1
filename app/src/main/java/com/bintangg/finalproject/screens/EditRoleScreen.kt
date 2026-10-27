package com.bintangg.finalproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun EditRoleScreen(navController: NavController) {
    // State untuk form
    var namaPeran by remember { mutableStateOf("Product Auditor") }
    var deskripsiSingkat by remember { mutableStateOf("Bertanggung jawab mengaudit transaksi dan data operasional tim.") }
    var selectedLevel by remember { mutableStateOf("Admin") }

    // State untuk checkbox izin akses
    var aksesPengguna by remember { mutableStateOf(true) }
    var kelolaTransaksi by remember { mutableStateOf(true) }
    var unduhLaporan by remember { mutableStateOf(true) }
    var pengaturanSistem by remember { mutableStateOf(false) }

    val primaryPurple = Color(0xFF6750A4) // Perbaikan: Warna ungu utama yang valid
    val backgroundLight = Color(0xFFF8F9FA)

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Konten yang bisa di-scroll
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Header (Pengganti TopAppBar kustom)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navController.popBackStack() }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Ubah Peran",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Informasi Peran Section
                Text(
                    text = "Informasi Peran",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Atur profil peran dan tentukan batas akses otorisasi sistem.",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                // Input Nama Peran
                Text(text = "Nama Peran", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = namaPeran,
                    onValueChange = { namaPeran = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryPurple,
                        unfocusedBorderColor = Color.LightGray,
                        focusedContainerColor = backgroundLight,
                        unfocusedContainerColor = backgroundLight
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Input Deskripsi Singkat
                Text(text = "Deskripsi Singkat", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = deskripsiSingkat,
                    onValueChange = { deskripsiSingkat = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryPurple,
                        unfocusedBorderColor = Color.LightGray,
                        focusedContainerColor = backgroundLight,
                        unfocusedContainerColor = backgroundLight
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Level Akses Chips
                Text(text = "Level Akses", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LevelChip(
                        title = "Admin",
                        icon = Icons.Default.Person,
                        isSelected = selectedLevel == "Admin",
                        primaryColor = Color(0xFF1976D2),
                        onClick = { selectedLevel = "Admin" }
                    )
                    LevelChip(
                        title = "Editor",
                        icon = Icons.Default.Person,
                        isSelected = selectedLevel == "Editor",
                        primaryColor = primaryPurple,
                        onClick = { selectedLevel = "Editor" }
                    )
                    LevelChip(
                        title = "Viewer",
                        icon = Icons.Default.Person,
                        isSelected = selectedLevel == "Viewer",
                        primaryColor = primaryPurple,
                        onClick = { selectedLevel = "Viewer" }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Izin Akses Esensial Section
                Text(
                    text = "Izin Akses Esensial",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Pilih kewenangan yang diberikan kepada peran ini",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                // Checkbox List Item
                PermissionCard(
                    title = "Akses Pengguna",
                    subtitle = "Melihat profil & riwayat anggota tim",
                    icon = Icons.Default.Person,
                    checked = aksesPengguna,
                    onCheckedChange = { aksesPengguna = it },
                    primaryColor = primaryPurple
                )
                Spacer(modifier = Modifier.height(8.dp))
                PermissionCard(
                    title = "Kelola Transaksi",
                    subtitle = "Memproses & menyetujui mutasi saldo",
                    icon = Icons.Default.Person,
                    checked = kelolaTransaksi,
                    onCheckedChange = { kelolaTransaksi = it },
                    primaryColor = primaryPurple
                )
                Spacer(modifier = Modifier.height(8.dp))
                PermissionCard(
                    title = "Unduh Laporan",
                    subtitle = "Ekspor data berkala format CSV / PDF",
                    icon = Icons.Default.Person,
                    checked = unduhLaporan,
                    onCheckedChange = { unduhLaporan = it },
                    primaryColor = primaryPurple
                )
                Spacer(modifier = Modifier.height(8.dp))
                PermissionCard(
                    title = "Pengaturan Sistem",
                    subtitle = "Konfigurasi integrasi API & keamanan",
                    icon = Icons.Default.Person,
                    checked = pengaturanSistem,
                    onCheckedChange = { pengaturanSistem = it },
                    primaryColor = primaryPurple
                )
            }

            // Bagian Tombol Aksi Bawah (Pengganti BottomAppBar)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { /* Aksi Batal */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.DarkGray),
                        border = ButtonDefaults.outlinedButtonBorder
                    ) {
                        Text(text = "Batal", fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { /* Aksi Simpan Peran */ },
                        modifier = Modifier
                            .weight(1.5f)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Simpan Peran", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun LevelChip(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    primaryColor: Color,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) primaryColor else Color(0xFFF2F3F7)
    val contentColor = if (isSelected) Color.White else Color.DarkGray

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = title,
            color = contentColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun EditRoleScreenPreview() {
    EditRoleScreen(rememberNavController())
}