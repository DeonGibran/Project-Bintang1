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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.components.TaskCard
import com.bintangg.finalproject.ui.theme.FinalProjectTheme

// --- Palet Warna (Kompatibilitas Backward) ---
val TabTextSelected = Blue
val TabTextUnselected = Muted
val BorderCard = Border
val TextTitleDark = MainText
val TagBgBlue = PubBlue
val TagTextBlue = Blue
val UrgentTextRed = Red
val TimeTextGrey = Muted
val FabBlue = Purple

@Composable
fun TaskListScreen(onAddTask: () -> Unit) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Belum Selesai", "Mendekati Deadline", "Selesai")

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTask,
                containerColor = Purple,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Tugas")
            }
        },
        containerColor = AppBg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // --- Tab Row Section ---
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp,
                containerColor = Panel,
                divider = { HorizontalDivider(color = Border) },
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Blue
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
                                color = if (selectedTabIndex == index) Blue else Muted,
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

@Preview(showBackground = true, backgroundColor = 0xFF070B17)
@Composable
fun TaskListScreenPreview() {
    FinalProjectTheme {
        TaskListScreen(onAddTask = {})
    }
}
