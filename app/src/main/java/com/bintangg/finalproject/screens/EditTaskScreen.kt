package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun EditTaskScreen(navController: NavController) {
    var judulTugas by remember { mutableStateOf("Tugas Fisika Dasar") }
    var mataPelajaran by remember { mutableStateOf("Fisika Dasar") }
    var tanggalMulai by remember { mutableStateOf("20/04/2024") }
    var tanggalSelesai by remember { mutableStateOf("23:59") }
    var selectedPriority by remember { mutableStateOf("Sedang") }

    val primaryBlue = Color(0xFF2962FF)
    val backgroundLight = Color(0xFFF8F9FA)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header Kustom (Tanpa TopAppBar) dengan tombol Close di kanan
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Edit Tugas",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Tutup",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* Aksi tutup */ },
                    tint = Color.Black
                )
            }

            // Input Judul Tugas
            Text(
                text = "Judul Tugas",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = judulTugas,
                onValueChange = { judulTugas = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = backgroundLight,
                    unfocusedContainerColor = backgroundLight
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input Mata Pelajaran
            Text(
                text = "Mata Pelajaran",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = mataPelajaran,
                onValueChange = { mataPelajaran = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = backgroundLight,
                    unfocusedContainerColor = backgroundLight
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Baris Tanggal Mulai & Tanggal Selesai
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Tanggal Mulai",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = tanggalMulai,
                        onValueChange = { tanggalMulai = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = Color.Gray)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = backgroundLight,
                            unfocusedContainerColor = backgroundLight
                        ),
                        singleLine = true
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Tanggal Selesai",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = tanggalSelesai,
                        onValueChange = { tanggalSelesai = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        trailingIcon = {
                            Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Color.Gray)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = backgroundLight,
                            unfocusedContainerColor = backgroundLight
                        ),
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Input Priority
            Text(
                text = "Priority",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = selectedPriority,
                onValueChange = { selectedPriority = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Color.Gray)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = backgroundLight,
                    unfocusedContainerColor = backgroundLight
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Pilihan Chip Priority (Tinggi, Sedang, Rendah)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PriorityChip(
                    title = "Tinggi",
                    isSelected = selectedPriority == "Tinggi",
                    primaryColor = primaryBlue,
                    modifier = Modifier.weight(1f)
                ) { selectedPriority = "Tinggi" }

                PriorityChip(
                    title = "Sedang",
                    isSelected = selectedPriority == "Sedang",
                    primaryColor = primaryBlue,
                    modifier = Modifier.weight(1f)
                ) { selectedPriority = "Sedang" }

                PriorityChip(
                    title = "Rendah",
                    isSelected = selectedPriority == "Rendah",
                    primaryColor = primaryBlue,
                    modifier = Modifier.weight(1f)
                ) { selectedPriority = "Rendah" }
            }
        }
    }
}

@Composable
fun PriorityChip(
    title: String,
    isSelected: Boolean,
    primaryColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) primaryColor else Color.LightGray
    val textColor = if (isSelected) primaryColor else Color.DarkGray
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium

    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(46.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) Color(0xFFF0F4FF) else Color.White,
            contentColor = textColor
        ),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Text(text = title, fontSize = 13.sp, fontWeight = fontWeight)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditTaskScreenPreview() {
    EditTaskScreen(rememberNavController())
}