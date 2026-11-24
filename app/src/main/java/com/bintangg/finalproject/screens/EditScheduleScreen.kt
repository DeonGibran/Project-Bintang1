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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
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
import com.bintangg.finalproject.ui.theme.FinalProjectTheme

@Composable
fun EditScheduleScreen(onBack: () -> Unit) {
    var namaTugas by remember { mutableStateOf("Sprint Planning & Review Q4") }
    var keterangan by remember { mutableStateOf("Tambahkan catatan khusus, tautan dokumen, atau instruksi rapat...") }
    var selectedStatus by remember { mutableStateOf("Dijadwalkan") }

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
                // Custom Header
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
                                .clickable { onBack() },
                            tint = MainText
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Edit\nSchedule",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MainText,
                            lineHeight = 24.sp
                        )
                    }
                }

                // Banner Atas
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
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(PubBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = Blue)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Perbarui Jadwal Tugas", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MainText)
                            Text(text = "Atur pengingat tepat waktu & si...", fontSize = 11.sp, color = Muted)
                        }
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = PubBlue
                        ) {
                            Text(
                                text = "Draft Aktif",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Blue
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card Section 1: Informasi Inti
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    border = BorderStroke(1.dp, Border),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Blue, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Informasi Inti", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MainText)
                            }
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = Panel2
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "# #TSK-8942", fontSize = 11.sp, color = Muted)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(12.dp), tint = Muted)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Nama Tugas / Agenda *", fontSize = 12.sp, color = Muted)
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = namaTugas,
                            onValueChange = { namaTugas = it },
                            modifier = Modifier.fillMaxWidth(),
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

                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Keterangan Lengkap", fontSize = 12.sp, color = Muted)
                            Text(text = "0 / 250", fontSize = 11.sp, color = Muted)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = keterangan,
                            onValueChange = { keterangan = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp),
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

                Spacer(modifier = Modifier.height(16.dp))

                // Card Section 2: Status & Penjadwalan
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    border = BorderStroke(1.dp, Border),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = Blue, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Status & Penjadwalan", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MainText)
                            }
                            Text(text = "WIB (UTC+7)", fontSize = 11.sp, color = Muted)
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Status Tugas", fontSize = 12.sp, color = Muted)
                        Spacer(modifier = Modifier.height(8.dp))

                        // Grid Status
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                StatusOptionChip("Pending", selectedStatus == "Pending", Modifier.weight(1f), Amber) { selectedStatus = "Pending" }
                                StatusOptionChip("Berjalan", selectedStatus == "Berjalan", Modifier.weight(1f), Blue) { selectedStatus = "Berjalan" }
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                StatusOptionChip("Dijadwalkan", selectedStatus == "Dijadwalkan", Modifier.weight(1f), Purple) { selectedStatus = "Dijadwalkan" }
                                StatusOptionChip("Selesai", selectedStatus == "Selesai", Modifier.weight(1f), Green) { selectedStatus = "Selesai" }
                            }
                        }
                    }
                }
            }

            // Tombol Aksi Bawah
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Panel,
                border = BorderStroke(1.dp, Border),
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { onBack() },
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

                    OutlinedButton(
                        onClick = { onBack() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        border = BorderStroke(1.dp, Border),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Muted)
                    ) {
                        Text(text = "Batal", fontWeight = FontWeight.Bold, color = Muted)
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Edit Schedule",
    showBackground = true,
    backgroundColor = 0xFF070B17,
    showSystemUi = true,
)
@Composable
fun EditScheduleScreenPreview() {
    FinalProjectTheme {
        EditScheduleScreen(onBack = {})
    }
}
