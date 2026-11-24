package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.data.Category
import com.bintangg.finalproject.data.CategoryRepository
import com.bintangg.finalproject.ui.theme.FinalProjectTheme
import kotlinx.coroutines.launch

@Composable
fun AddCategoryScreen(onBack: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    var namaKategori by remember { mutableStateOf("") }
    var keterangan by remember { mutableStateOf("") }

    var isSaving by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

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
            // Header Layar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    tint = MainText,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onBack() }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Tambah Kategori Baru",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainText
                )
            }

            // Card Form Input
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Panel),
                border = BorderStroke(1.dp, Border),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Informasi Kategori",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MainText
                    )
                    Text(
                        text = "Masukkan pengelompokan mata pelajaran baru ke dalam sistem.",
                        fontSize = 12.sp,
                        color = Muted,
                        modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
                    )

                    // Input Nama Kategori (Wajib Diisi)
                    Text(
                        text = "Nama Kategori *",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Muted
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = namaKategori,
                        onValueChange = { namaKategori = it },
                        placeholder = { Text("Contoh: Kalkulus & Aljabar", fontSize = 13.sp, color = Muted) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
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

                    // Input Keterangan (Opsional)
                    Text(
                        text = "Keterangan",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Muted
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = keterangan,
                        onValueChange = { keterangan = it },
                        placeholder = { Text("Deskripsi materi atau silabus kategori...", fontSize = 13.sp, color = Muted) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
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

                    // Tampilkan Error Box jika terjadi kesalahan
                    errorMessage?.let { msg ->
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = msg,
                            color = Red,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tombol Aksi Batal dan Simpan
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = { onBack() },
                            enabled = !isSaving,
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
                            onClick = {
                                coroutineScope.launch {
                                    isSaving = true
                                    errorMessage = null
                                    try {
                                        val newCategory = Category(
                                            name = namaKategori.trim(),
                                            description = keterangan.trim().ifBlank { null }
                                        )
                                        CategoryRepository.addCategory(newCategory)
                                        onBack()
                                    } catch (e: Exception) {
                                        errorMessage = "Gagal menyimpan: ${e.localizedMessage}"
                                    } finally {
                                        isSaving = false
                                    }
                                }
                            },
                            enabled = namaKategori.isNotBlank() && !isSaving,
                            modifier = Modifier
                                .weight(1.5f)
                                .height(48.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Purple)
                        ) {
                            if (isSaving) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(16.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "Menyimpan...", fontSize = 12.sp, color = Color.White)
                            } else {
                                Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color.White)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Simpan Kategori", fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF070B17, showSystemUi = true)
@Composable
fun AddCategoryScreenPreview() {
    FinalProjectTheme {
        AddCategoryScreen(onBack = {})
    }
}
