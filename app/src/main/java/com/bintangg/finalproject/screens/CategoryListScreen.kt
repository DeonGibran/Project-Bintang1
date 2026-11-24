package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
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

@Composable
fun CategoryListScreen(onAddCategory: () -> Unit, onEditCategory: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("Semua (5)") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppBg
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Header Judul & Deskripsi
                Text(
                    text = "Kelola Kategori 📚",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Atur pengelompokan mata pelajaran dan materi belajarmu.",
                    fontSize = 12.sp,
                    color = Muted,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Cari kategori mata pelajaran...", fontSize = 13.sp, color = Muted) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Muted)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Panel,
                        unfocusedContainerColor = Panel,
                        focusedTextColor = MainText,
                        unfocusedTextColor = MainText,
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Border
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tab Filter
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TabItem("Semua (5)", selectedTab == "Semua (5)", Blue) { selectedTab = "Semua (5)" }
                    TabItem("Aktif (4)", selectedTab == "Aktif (4)", Blue) { selectedTab = "Aktif (4)" }
                    TabItem("Perlu Tinjau (1)", selectedTab == "Perlu Tinjau (1)", Blue) { selectedTab = "Perlu Tinjau (1)" }
                    TabItem("Arsip (0)", selectedTab == "Arsip (0)", Blue) { selectedTab = "Arsip (0)" }
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
                            color = MainText
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = PubBlue
                        ) {
                            Text(
                                text = "5 Total",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Blue
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Terbaru ↕", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Blue)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Daftar Item Kategori Card
                KategoriCard(
                    title = "Fisika Dasar",
                    subtitle = "Lab Fisika Modern • 8 Tugas ...",
                    tagText = "Mendesak",
                    tagColor = Red,
                    tagBgColor = Panel2,
                    badgeCount = "8 Tugas",
                    iconColor = Blue,
                    onEditCategory = onEditCategory
                )

                Spacer(modifier = Modifier.height(8.dp))

                KategoriCard(
                    title = "Matematika...",
                    subtitle = "Gedung A, Ruang 101 • 4 Tugar...",
                    tagText = "Berlangsung",
                    tagColor = Blue,
                    tagBgColor = PubBlue,
                    badgeCount = "4 Tugas",
                    iconColor = Blue,
                    onEditCategory = onEditCategory
                )

                Spacer(modifier = Modifier.height(8.dp))

                KategoriCard(
                    title = "Biologi",
                    subtitle = "Laboratorium Utama • 6 Tug...",
                    tagText = "Aktif",
                    tagColor = Green,
                    tagBgColor = Panel2,
                    badgeCount = "6 Tugas",
                    iconColor = Green,
                    onEditCategory = onEditCategory
                )

                Spacer(modifier = Modifier.height(8.dp))

                KategoriCard(
                    title = "Sastra Indone...",
                    subtitle = "Perpustakaan Pusat • 2 Tuga...",
                    tagText = "Aktif",
                    tagColor = Blue,
                    tagBgColor = PubBlue,
                    badgeCount = "2 Tugas",
                    iconColor = Purple,
                    onEditCategory = onEditCategory
                )

                Spacer(modifier = Modifier.height(8.dp))

                KategoriCard(
                    title = "Sejarah Dunia",
                    subtitle = "Modul Baru • 1 Silabus Belum L...",
                    tagText = "Review",
                    tagColor = Amber,
                    tagBgColor = Panel2,
                    badgeCount = "Draft",
                    isDraftBadge = true,
                    iconColor = Amber,
                    cardBg = Panel,
                    onEditCategory = onEditCategory
                )

                Spacer(modifier = Modifier.height(80.dp))
            }

            // Tombol Tambah Melayang di Kanan Bawah
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingActionButton(
                    onClick = onAddCategory,
                    containerColor = Purple,
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
            color = if (isSelected) primaryColor else Muted
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
    cardBg: Color = Panel,
    onEditCategory: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, Border),
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
                    .background(PubBlue),
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
                        color = MainText
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
                    color = Muted
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Badge Kanan & Tombol Menu Opsi
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Panel2
                ) {
                    Text(
                        text = badgeCount,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDraftBadge) Amber else Blue
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Opsi Lainnya",
                    tint = Muted,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(onClick = onEditCategory)
                    )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryListScreenPreview() {
    CategoryListScreen(onAddCategory = {}, onEditCategory = {})
}
