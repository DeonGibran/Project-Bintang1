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
import com.bintangg.finalproject.components.CustomInputField
import com.bintangg.finalproject.components.PriorityBox
import com.bintangg.finalproject.data.Task
import com.bintangg.finalproject.data.TaskRepository
import kotlinx.coroutines.launch

// --- Palet Warna (Kompatibilitas Backward) ---
val OverlayDark = Color(0x99000000)
val TextLabelGrey = Muted
val TextPlaceholderGrey = Muted
val BorderGrey = Border
val DragHandleGrey = Border

@Composable
fun AddTaskBottomSheetScreen(onBack: () -> Unit) {
    val scope = rememberCoroutineScope()
    var title by remember { mutableStateOf(value = "") }
    var subject by remember { mutableStateOf("") }
    var start_date by remember { mutableStateOf("") }
    var due_date by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Sedang") }
    var isSaving by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OverlayDark)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = Panel,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .border(
                    BorderStroke(1.dp, Border),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Drag Handle
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .background(Border, RoundedCornerShape(2.dp))
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
                    color = MainText
                )
                IconButton(
                    onClick = { onBack() },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Tutup",
                        tint = MainText
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Input: Judul Tugas
            CustomInputField(
                label = "Judul Tugas",
                value = title,
                onValueChange = { title = it },
                placeholder = "Masukkan judul..."
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input: Mata Pelajaran (Dropdown)
            CustomInputField(
                label = "Mata Pelajaran",
                value = subject,
                onValueChange = { subject = it },
                placeholder = "Pilih mata pelajaran",
                trailingIcon = Icons.Outlined.KeyboardArrowDown
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Row: Tanggal Mulai & Tanggal Selesai
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    CustomInputField(
                        label = "Tanggal Mulai",
                        value = start_date,
                        onValueChange = { start_date = it },
                        placeholder = "dd/mm/yyyy",
                        trailingIcon = Icons.Outlined.CalendarToday
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    CustomInputField(
                        label = "Tanggal Selesai",
                        value = due_date,
                        onValueChange = { due_date = it },
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
                onValueChange = { priority = it },
                placeholder = "priority",
                trailingIcon = Icons.Outlined.Schedule
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PriorityBox(text = "Tinggi", isSelected = priority == "Tinggi")
                PriorityBox(text = "Sedang", isSelected = priority == "Sedang")
                PriorityBox(text = "Rendah", isSelected = priority == "Rendah")
            }

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider(color = Border, thickness = 1.dp)
            Spacer(modifier = Modifier.height(20.dp))

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
                            onBack()
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
                colors = ButtonDefaults.buttonColors(containerColor = Purple),
                shape = RoundedCornerShape(24.dp)
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
                Text(text = it, color = Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskBottomSheetScreenPreview() {
    AddTaskBottomSheetScreen(onBack = {})
}
