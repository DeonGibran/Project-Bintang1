package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.bintangg.finalproject.ui.theme.FinalProjectTheme

@Composable
fun EditRoleScreen(onBack: () -> Unit) {
    var namaPeran by remember { mutableStateOf("Product Auditor") }
    var deskripsiSingkat by remember { mutableStateOf("Bertanggung jawab mengaudit transaksi dan data operasional tim.") }
    var selectedLevel by remember { mutableStateOf("Admin") }

    var aksesPengguna by remember { mutableStateOf(true) }
    var kelolaTransaksi by remember { mutableStateOf(true) }
    var unduhLaporan by remember { mutableStateOf(true) }
    var pengaturanSistem by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppBg
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Header
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
                            .clickable { onBack() },
                        tint = MainText
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Ubah Peran",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MainText
                    )
                }

                // Informasi Peran Section
                Text(
                    text = "Informasi Peran",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainText
                )
                Text(
                    text = "Atur profil peran dan tentukan batas akses otorisasi sistem.",
                    fontSize = 12.sp,
                    color = Muted,
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                // Input Nama Peran
                Text(text = "Nama Peran", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Muted)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = namaPeran,
                    onValueChange = { namaPeran = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Border,
                        focusedContainerColor = Panel2,
                        unfocusedContainerColor = Panel2,
                        focusedTextColor = MainText,
                        unfocusedTextColor = MainText
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Input Deskripsi Singkat
                Text(text = "Deskripsi Singkat", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Muted)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = deskripsiSingkat,
                    onValueChange = { deskripsiSingkat = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Border,
                        focusedContainerColor = Panel2,
                        unfocusedContainerColor = Panel2,
                        focusedTextColor = MainText,
                        unfocusedTextColor = MainText
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Level Akses Chips
                Text(text = "Level Akses", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Muted)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LevelChip(
                        title = "Admin",
                        icon = Icons.Default.Person,
                        isSelected = selectedLevel == "Admin",
                        primaryColor = Purple,
                        onClick = { selectedLevel = "Admin" }
                    )
                    LevelChip(
                        title = "Editor",
                        icon = Icons.Default.Person,
                        isSelected = selectedLevel == "Editor",
                        primaryColor = Purple,
                        onClick = { selectedLevel = "Editor" }
                    )
                    LevelChip(
                        title = "Viewer",
                        icon = Icons.Default.Person,
                        isSelected = selectedLevel == "Viewer",
                        primaryColor = Purple,
                        onClick = { selectedLevel = "Viewer" }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Izin Akses Esensial Section
                Text(
                    text = "Izin Akses Esensial",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainText
                )
                Text(
                    text = "Pilih kewenangan yang diberikan kepada peran ini",
                    fontSize = 12.sp,
                    color = Muted,
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                // Checkbox List Item
                PermissionCard(
                    title = "Akses Pengguna",
                    subtitle = "Melihat profil & riwayat anggota tim",
                    icon = Icons.Default.Person,
                    checked = aksesPengguna,
                    onCheckedChange = { aksesPengguna = it },
                    primaryColor = Purple
                )
                Spacer(modifier = Modifier.height(8.dp))
                PermissionCard(
                    title = "Kelola Transaksi",
                    subtitle = "Memproses & menyetujui mutasi saldo",
                    icon = Icons.Default.Person,
                    checked = kelolaTransaksi,
                    onCheckedChange = { kelolaTransaksi = it },
                    primaryColor = Purple
                )
                Spacer(modifier = Modifier.height(8.dp))
                PermissionCard(
                    title = "Unduh Laporan",
                    subtitle = "Ekspor data berkala format CSV / PDF",
                    icon = Icons.Default.Person,
                    checked = unduhLaporan,
                    onCheckedChange = { unduhLaporan = it },
                    primaryColor = Purple
                )
                Spacer(modifier = Modifier.height(8.dp))
                PermissionCard(
                    title = "Pengaturan Sistem",
                    subtitle = "Konfigurasi integrasi API & keamanan",
                    icon = Icons.Default.Person,
                    checked = pengaturanSistem,
                    onCheckedChange = { pengaturanSistem = it },
                    primaryColor = Purple
                )
            }

            // Bagian Tombol Aksi Bawah
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Panel,
                border = BorderStroke(1.dp, Border),
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { onBack() },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        border = BorderStroke(1.dp, Border),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Muted)
                    ) {
                        Text(text = "Batal", fontWeight = FontWeight.Bold, color = Muted)
                    }

                    Button(
                        onClick = { /* Aksi Simpan Peran */ },
                        modifier = Modifier
                            .weight(1.5f)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Purple)
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color.White)
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
    val backgroundColor = if (isSelected) primaryColor else Panel2
    val contentColor = if (isSelected) Color.White else Muted
    val borderColor = if (isSelected) primaryColor else Border

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, borderColor), RoundedCornerShape(16.dp))
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

@Preview(showBackground = true, backgroundColor = 0xFF070B17, showSystemUi = true)
@Composable
fun EditRoleScreenPreview() {
    FinalProjectTheme {
        EditRoleScreen(onBack = {})
    }
}
