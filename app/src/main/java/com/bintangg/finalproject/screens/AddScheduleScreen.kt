package com.bintangg.finalproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
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
fun AddScheduleScreen() {
    // State untuk form
    var namaTugas by remember { mutableStateOf("Sprint Planning & Review Q4") }
    var keterangan by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf("Dijadwalkan") }

    val primaryPurple = Color(0xFF5C3399) // Warna ungu utama
    val backgroundLight = Color(0xFFF8F9FA)

    Scaffold(
        containerColor = Color(0xFFF4F5F7)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Konten utama yang bisa di-scroll
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Custom Header (Tanpa TopAppBar)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { /* Aksi kembali */ }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Tambah\nSchedule",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            lineHeight = 24.sp
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { /* Aksi Simpan */ },
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = primaryPurple),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Simpan", fontSize = 12.sp)
                        }

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEAEAEA))
                                .clickable { /* Aksi Profil */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color.DarkGray)
                        }
                    }
                }

                // Banner Banner Atas (Jadwalkan Tugas Baru)
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEEF0FA)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(primaryPurple),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Jadwalkan Tugas Baru", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                            Text(text = "Atur pengingat tepat waktu & si...", fontSize = 11.sp, color = Color.Gray)
                        }
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White
                        ) {
                            Text(
                                text = "Draft Aktif",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryPurple
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card Section 1: Informasi Inti
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = primaryPurple, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Informasi Inti", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = Color(0xFFF0F2F5)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "# #TSK-8942", fontSize = 11.sp, color = Color.DarkGray)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(12.dp), tint = Color.Gray)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Nama Tugas / Agenda *", fontSize = 12.sp, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = namaTugas,
                            onValueChange = { namaTugas = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = backgroundLight,
                                unfocusedContainerColor = backgroundLight
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Keterangan Lengkap", fontSize = 12.sp, color = Color.DarkGray)
                            Text(text = "0 / 250", fontSize = 11.sp, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = keterangan,
                            onValueChange = { keterangan = it },
                            placeholder = { Text("Tambahkan catatan khusus, tautan dokumen, atau instruksi rapat...", fontSize = 12.sp) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = backgroundLight,
                                unfocusedContainerColor = backgroundLight
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Template cepat:", fontSize = 11.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFF0F2F5)) {
                                Text(text = "+ Target Tim", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 11.sp, color = primaryPurple)
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFF0F2F5)) {
                                Text(text = "+ Laporan", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 11.sp, color = primaryPurple)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card Section 2: Status & Penjadwalan
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = primaryPurple, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Status & Penjadwalan", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }
                            Text(text = "WIB (UTC+7)", fontSize = 11.sp, color = Color.Gray)
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Status Tugas", fontSize = 12.sp, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(8.dp))

                        // Grid Status
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                StatusOptionChip("Pending", false, Modifier.weight(1f)) {}
                                StatusOptionChip("Berjalan", false, Modifier.weight(1f)) {}
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                StatusOptionChip("Dijadwalkan", true, Modifier.weight(1f), primaryPurple) {}
                                StatusOptionChip("Selesai", false, Modifier.weight(1f)) {}
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Remind At (Waktu Pengingat)", fontSize = 12.sp, color = Color.DarkGray)
                            Text(text = "Notifikasi Push", fontSize = 11.sp, color = primaryPurple, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(6.dp))

                        // Waktu Pengingat Card
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = backgroundLight),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = primaryPurple)
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = "24 Okt 2024, 09:00 WIB", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text(text = "Pengingat 15 menit sebelum acara", fontSize = 11.sp, color = Color.Gray)
                                }
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(Color.White, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.DarkGray)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card Section 3: Riwayat & Jejak Audit
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = primaryPurple, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Riwayat & Jejak Audit", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }
                            Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFF0F2F5)) {
                                Text(text = "Sistem", modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp), fontSize = 10.sp, color = Color.DarkGray)
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        AuditRowItem("Created At", "24 Okt 2024, 08:30 WIB")
                        Spacer(modifier = Modifier.height(6.dp))
                        AuditRowItem("Updated At", "Baru saja (Just now)", isHighlight = true, primaryColor = primaryPurple)
                    }
                }
            }

            // Tombol Aksi Bagian Bawah (Tanpa BottomAppBar)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { /* Simpan Schedule */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryPurple)
                    ) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Simpan Schedule", fontWeight = FontWeight.Bold, color = Color.White)
                    }

                    OutlinedButton(
                        onClick = { /* Batal / Reset */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.DarkGray)
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Batal / Reset", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun StatusOptionChip(title: String, isSelected: Boolean, modifier: Modifier = Modifier, primaryColor: Color = Color.Transparent, onClick: () -> Unit) {
    val bgColor = if (isSelected) Color(0xFFEEF0FA) else Color(0xFFF8F9FA)
    val contentColor = if (isSelected) primaryColor else Color.DarkGray

    Surface(
        modifier = modifier.clip(RoundedCornerShape(8.dp)).clickable { onClick() },
        color = bgColor
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(8.dp).background(if (isSelected) primaryColor else Color.Gray, CircleShape))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = contentColor, modifier = Modifier.weight(1f))
            if (isSelected) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = primaryColor, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun AuditRowItem(label: String, value: String, isHighlight: Boolean = false, primaryColor: Color = Color.Black) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF8F9FA))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Info, contentDescription = null, modifier = Modifier.size(14.dp), tint = Color.Gray)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = label, fontSize = 11.sp, color = Color.DarkGray)
        }
        Text(
            text = value,
            fontSize = 11.sp,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal,
            color = if (isHighlight) primaryColor else Color.DarkGray
        )
    }
}

// Tambahan Preview sesuai permintaan
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddScheduleScreenPreview() {
    AddScheduleScreen()
}