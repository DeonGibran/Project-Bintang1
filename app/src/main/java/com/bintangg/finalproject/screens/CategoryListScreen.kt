package com.bintangg.finalproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CategoryListScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("Semua (5)") }

    val primaryBlue = Color(0xFF2962FF)
    val backgroundLight = Color(0xFFF8F9FA)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Header Judul & Deskripsi (Tanpa TopAppBar)
                Text(
                    text = "Kelola Kategori 📚",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Atur pengelompokan mata pelajaran dan materi belajarmu.",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Cari kategori mata pelajaran...", fontSize = 13.sp, color = Color.Gray) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = backgroundLight,
                        unfocusedContainerColor = backgroundLight,
                        focusedBorderColor = primaryBlue,
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tab Filter (Semua, Aktif, Perlu Tinjau, Arsip)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TabItem("Semua (5)", selectedTab == "Semua (5)", primaryBlue) { selectedTab = "Semua (5)" }
                    TabItem("Aktif (4)", selectedTab == "Aktif (4)", primaryBlue) { selectedTab = "Aktif (4)" }
                    TabItem("Perlu Tinjau (1)", selectedTab == "Perlu Tinjau (1)", primaryBlue) { selectedTab = "Perlu Tinjau (1)" }
                    TabItem("Arsip (0)", selectedTab == "Arsip (0)", primaryBlue) { selectedTab = "Arsip (0)" }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Bagian Daftar Kategori & Sortir
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Daftar Kategori",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFFE8F0FE)
                        ) {
                            Text(
                                text = "5 Total",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryBlue
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Terbaru ↕", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = primaryBlue)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Daftar Item Kategori Card
                KategoriCard(
                    title = "Fisika Dasar",
                    subtitle = "Lab Fisika Modern • 8 Tugas ...",
                    tagText = "Mendesak",
                    tagColor = Color(0xFFD32F2F),
                    tagBgColor = Color(0xFFFFEBEE),
                    badgeCount = "8 Tugas",
                    iconColor = Color(0xFF2962FF)
                )

                Spacer(modifier = Modifier.height(8.dp))

                KategoriCard(
                    title = "Matematika...",
                    subtitle = "Gedung A, Ruang 101 • 4 Tugar...",
                    tagText = "Berlangsung",
                    tagColor = Color.White,
                    tagBgColor = Color(0xFF1565C0),
                    badgeCount = "4 Tugas",
                    iconColor = Color(0xFF1565C0)
                )

                Spacer(modifier = Modifier.height(8.dp))

                KategoriCard(
                    title = "Biologi",
                    subtitle = "Laboratorium Utama • 6 Tug...",
                    tagText = "Aktif",
                    tagColor = Color(0xFF1565C0),
                    tagBgColor = Color(0xFFE8F0FE),
                    badgeCount = "6 Tugas",
                    iconColor = Color(0xFF00ACC1)
                )

                Spacer(modifier = Modifier.height(8.dp))

                KategoriCard(
                    title = "Sastra Indone...",
                    subtitle = "Perpustakaan Pusat • 2 Tuga...",
                    tagText = "Aktif",
                    tagColor = Color(0xFF1565C0),
                    tagBgColor = Color(0xFFE8F0FE),
                    badgeCount = "2 Tugas",
                    iconColor = Color(0xFF5E35B1)
                )

                Spacer(modifier = Modifier.height(8.dp))

                KategoriCard(
                    title = "Sejarah Dunia",
                    subtitle = "Modul Baru • 1 Silabus Belum L...",
                    tagText = "Review",
                    tagColor = Color(0xFFD32F2F),
                    tagBgColor = Color(0xFFFFEBEE),
                    badgeCount = "Draft",
                    isDraftBadge = true,
                    iconColor = Color(0xFFC62828),
                    cardBg = Color(0xFFFFF8F8)
                )

                // Ruang kosong bawah agar tidak tertutup tombol Floating
                Spacer(modifier = Modifier.height(80.dp))
            }

            // Tombol Tambah Melayang di Kanan Bawah (Pengganti BottomAppBar Action)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingActionButton(
                    onClick = { /* Aksi Tambah */ },
                    containerColor = primaryBlue,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah")
                }
            }
        }
    }
}

@Composable
fun TabItem(title: String, isSelected: Boolean, primaryColor: Color, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) primaryColor else Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(2.dp)
                    .background(primaryColor, RoundedCornerShape(2.dp))
            )
        } else {
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Composable
fun KategoriCard(
    title: String,
    subtitle: String,
    tagText: String,
    tagColor: Color,
    tagBgColor: Color,
    badgeCount: String,
    isDraftBadge: Boolean = false,
    iconColor: Color,
    cardBg: Color = Color.White
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = CardDefaults.outlinedCardBorder(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Kiri
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconColor.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Informasi Tengah
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = tagBgColor
                    ) {
                        Text(
                            text = tagText,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = tagColor
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Badge Kanan & Tombol Menu Opsi
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isDraftBadge) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFFFEBEE)
                    ) {
                        Text(
                            text = badgeCount,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD32F2F)
                        )
                    }
                } else {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFE8F0FE)
                    ) {
                        Text(
                            text = badgeCount,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2962FF)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Opsi Lainnya",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { /* Aksi opsi */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryListScreenPreview() {
    CategoryListScreen(rememberNavController())
}