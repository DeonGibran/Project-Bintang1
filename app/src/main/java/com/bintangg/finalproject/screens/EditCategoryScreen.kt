package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditCategoryScreen(onBack: () -> Unit) {
    var namaKategori by remember { mutableStateOf("Matematika Tingkat Lanjut") }
    var keterangan by remember {
        mutableStateOf("Materi kalkulus, aljabar linear, dan trigonometri untuk siswa SMA & persiapan ujian masuk kuliah.")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header Kustom
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali",
                    tint = MainText,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onBack() }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Edit Kategori",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainText
                )
            }

            // Tag & ID Kategori
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = PubBlue
                ) {
                    Text(
                        text = "● MANAJEMEN MATERI",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Blue
                    )
                }

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Panel2
                ) {
                    Text(
                        text = "🕒 ID: KAT-804",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        fontSize = 11.sp,
                        color = Muted
                    )
                }
            }

            // Judul & Deskripsi Header
            Text(
                text = "Edit Kategori",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MainText
            )
            Text(
                text = "Perbarui detail kategori pelajaran dan keterangan informasinya.",
                fontSize = 12.sp,
                color = Muted,
                modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
            )

            // Card Preview Kategori Aktif
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Panel),
                border = BorderStroke(1.dp, Border),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(PubBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = Blue)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Matematika Tingkat Lanjut",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MainText
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = PubBlue
                        ) {
                            Text(
                                text = "Kategori Aktif",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Blue
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Card Form Informasi Kategori
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Panel),
                border = BorderStroke(1.dp, Border),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Blue, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Informasi Kategori", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MainText)
                        }
                        Text(text = "Wajib diisi *", fontSize = 11.sp, color = Muted)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Nama Kategori *", fontSize = 12.sp, color = Muted, fontWeight = FontWeight.Medium)
                        Text(text = "25/60", fontSize = 11.sp, color = Muted)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = namaKategori,
                        onValueChange = { namaKategori = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Panel2,
                            unfocusedContainerColor = Panel2,
                            focusedTextColor = MainText,
                            unfocusedTextColor = MainText,
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Border
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Keterangan", fontSize = 12.sp, color = Muted, fontWeight = FontWeight.Medium)
                        Text(text = "Opsional", fontSize = 11.sp, color = Muted)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = keterangan,
                        onValueChange = { keterangan = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Panel2,
                            unfocusedContainerColor = Panel2,
                            focusedTextColor = MainText,
                            unfocusedTextColor = MainText,
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Border
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Tombol Simpan Perubahan
            Button(
                onClick = { /* Aksi Simpan */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Purple)
            ) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Simpan Perubahan", fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tombol Batal dan Hapus Kategori di Bagian Bawah
            Row(
                modifier = Modifier.fillMaxWidth(),
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

                OutlinedButton(
                    onClick = { /* Aksi Hapus Kategori */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Panel2,
                        contentColor = Red
                    ),
                    border = BorderStroke(1.dp, Red.copy(alpha = 0.5f))
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null, modifier = Modifier.size(16.dp), tint = Red)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Hapus Kategori", fontWeight = FontWeight.Bold, color = Red)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditCategoryScreenPreview() {
    EditCategoryScreen(onBack = {})
}
