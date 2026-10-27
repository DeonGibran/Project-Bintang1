package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bintangg.finalproject.components.TaskCard

// --- Palet Warna ---
val TabTextSelected = Color(0xFF0052CC) // Biru pekat
val TabTextUnselected = Color(0xFF475569) // Abu-abu
val BorderCard = Color(0xFFE2E8F0)
val TextTitleDark = Color(0xFF0F172A)
val TagBgBlue = Color(0xFFEFF6FF)
val TagTextBlue = Color(0xFF2563EB)
val UrgentTextRed = Color(0xFFDC2626)
val TimeTextGrey = Color(0xFF475569)
val FabBlue = Color(0xFF0052CC)

@Composable
fun TaskListScreen(navController: NavController) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Belum Selesai", "Mendekati Deadline", "Selesai")

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("AddTask") },
                containerColor = FabBlue,
                contentColor = Color.White,
                shape = RoundedCornerShape(12.dp) // Radius melengkung mirip gambar
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Tugas")
            }
        },
        containerColor = BackgroundWhite
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // --- Tab Row Section ---
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp, // Agar tab menempel di sisi kiri
                containerColor = BackgroundWhite,
                divider = { HorizontalDivider(color = BorderCard) },
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = TabTextSelected
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                color = if (selectedTabIndex == index) TabTextSelected else TabTextUnselected,
                                fontWeight = if (selectedTabIndex == index) FontWeight.SemiBold else FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }

            // --- List of Task Cards ---
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TaskCard(
                    title = "Laporan Praktikum Kimia",
                    tag = "Kimia Dasar",
                    timeText = "Sisa 2 jam",
                    isUrgent = true,
                    timeIcon = Icons.Outlined.Schedule
                )

                TaskCard(
                    title = "Tugas Esai Sejarah",
                    tag = "Sejarah Dunia",
                    timeText = "Besok, 10:00",
                    isUrgent = false,
                    timeIcon = Icons.Outlined.CalendarToday
                )

                TaskCard(
                    title = "Persiapan Presentasi Proyek Akhir",
                    tag = "Manajemen Proyek",
                    timeText = "Jumat, 14:00",
                    isUrgent = false,
                    timeIcon = Icons.Outlined.CalendarToday
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    TaskListScreen(rememberNavController())
}