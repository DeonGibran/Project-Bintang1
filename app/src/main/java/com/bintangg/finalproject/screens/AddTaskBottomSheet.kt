package com.bintangg.finalproject.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bintangg.finalproject.components.CustomInputField
import com.bintangg.finalproject.components.PriorityBox
import com.bintangg.finalproject.data.Task
import com.bintangg.finalproject.data.TaskRepository
import kotlinx.coroutines.launch

// --- Palet Warna ---
val OverlayDark = Color(0x66000000) // Transparan gelap untuk background
val TextLabelGrey = Color(0xFF475569)
val TextPlaceholderGrey = Color(0xFF94A3B8)
val BorderGrey = Color(0xFFE2E8F0)
val DragHandleGrey = Color(0xFFCBD5E1)
@Composable
fun AddTaskBottomSheetScreen(navController: NavController) {
    val scope = rememberCoroutineScope ()
    var title by remember { mutableStateOf(value = "")  }
    var subject by remember { mutableStateOf("") }
    var start_date by remember { mutableStateOf("") }
    var due_date by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Sedang") }
    var isSaving by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    // Kontainer layar penuh dengan background gelap semi-transparan
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OverlayDark)
    ) {
        // Konten Bottom Sheet
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = BackgroundWhite,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Drag Handle
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .background(DragHandleGrey, RoundedCornerShape(2.dp))
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Header (Judul & Tombol Close)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tambah Tugas",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Tutup",
                        tint = TextDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Input: Judul Tugas
            CustomInputField(
                label = "Judul Tugas",
                value = title,
                onValueChange = {
                    title = it
                },
                placeholder = "Masukkan judul..."
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input: Mata Pelajaran (Dropdown)
            CustomInputField(
                label = "Mata Pelajaran",
                value = subject,
                onValueChange = {
                    subject = it
                },
                placeholder = "Pilih mata pelajaran",
                trailingIcon = Icons.Outlined.KeyboardArrowDown
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Row: Tenggat Waktu & Jam
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    CustomInputField(
                        label = "Tanggal Mulai",
                        value = start_date,
                        onValueChange = {
                            start_date = it
                        },
                        placeholder = "dd/mm/yyyy",
                        trailingIcon = Icons.Outlined.CalendarToday
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    CustomInputField(
                        label = "Tanggal Selesai",
                        value = due_date,
                        onValueChange = {
                            due_date = it
                        },
                        placeholder = "--:--",
                        trailingIcon = Icons.Outlined.Schedule
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Input: Prioritas
            CustomInputField(
                label = "Priority",
                value = priority,
                onValueChange = {
                    priority = it
                },
                placeholder = "priority",
                trailingIcon = Icons.Outlined.Schedule
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PriorityBox(text = "Tinggi", isSelected = false)
                PriorityBox(text = "Sedang", isSelected = true)
                PriorityBox(text = "Rendah", isSelected = false)
            }

            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(color = BorderGrey, thickness = 1.dp)
            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Simpan Tugas
            Button(
                onClick = {
                    scope.launch {
                        isSaving = true
                        errorMessage = null
                        try {
                            TaskRepository.addTask(
                                Task(
                                   nama_task = title,
                                    category_id = null,
                                    user_id = null,
                                    status = "ongoing",
                                    start_date = start_date,
                                    due_date = due_date,
                                    priority = priority
                                )
                            )
                            navController.popBackStack()
                        } catch (error: Exception) {
                            errorMessage = error.message ?: "Data gagal disimpan ke Supabase."
                        } finally {
                            isSaving = false
                        }
                    }
                },
                enabled = title.isNotBlank() && subject.isNotBlank() && due_date.isNotBlank() && !isSaving,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = if (isSaving) "Menyimpan..." else "Simpan Tugas",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            errorMessage?.let {
                Text(text = it, color = Color(0xFFB42318), fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// --- Komponen Reusable untuk Input Field ---


// --- Komponen Reusable untuk Kotak Prioritas ---


@Preview(showBackground = true)
@Composable
fun AddTaskBottomSheetScreenPreview() {
    AddTaskBottomSheetScreen(rememberNavController())
}