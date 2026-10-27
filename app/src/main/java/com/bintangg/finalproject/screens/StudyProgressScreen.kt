package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.outlined.Swipe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bintangg.finalproject.components.SubjectItem

// -- Palet Warna --
val PrimaryBlue = Color(0xFF0A56D0)       // Warna biru utama untuk border, button, dan progress
val TextDark = Color(0xFF1E1E1E)          // Warna teks hitam utama
val TextGray = Color(0xFF5F6368)          // Warna teks abu-abu subtitle
val TrackGray = Color(0xFFE0E0E0)         // Warna abu-abu background progress bar
val BorderLightGray = Color(0xFFF1F3F4)   // Warna border pada list antrean
val IconBgLight = Color(0xFFF0F4F9)       // Warna background icon kotak
val IconBlueGray = Color(0xFF4A6583)      // Warna icon di dalam kotak

@Composable
fun StudyProgressScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp, horizontal = 20.dp)
    ) {
        // --- Bagian Progress ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Progress",
                color = TextDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "12/20",
                color = PrimaryBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { 12f / 20f },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            color = PrimaryBlue,
            trackColor = TrackGray,
            strokeCap = StrokeCap.Round
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Bagian Kartu Soal ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = BackgroundWhite),
            border = BorderStroke(1.dp, PrimaryBlue),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "FISIKA - HUKUM NEWTON",
                    color = IconBlueGray,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Berapa besar gaya yang\ndiperlukan untuk\nmemberikan percepatan\n2 m/s² pada massa 5 kg?",
                    color = TextDark,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { /* Tangani aksi */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "Tampilkan Jawaban",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- Bagian List Antrean Subjek ---
        Text(
            text = "Antrean Subjek",
            color = TextDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SubjectItem(
                icon = Icons.Outlined.Science, // Ganti dengan icon flask sesuai kebutuhan
                title = "Biologi - Sel",
                subtitle = "8 Kartu Siap Di-review",
                isCompleted = false
            )

            SubjectItem(
                icon = Icons.Outlined.Calculate,
                title = "Matematika - Aljabar",
                subtitle = "3 Kartu Siap Di-review",
                isCompleted = false
            )

            SubjectItem(
                icon = Icons.Outlined.Swipe, // Pendekatan visual untuk icon kartu
                title = "Sejarah - Perang Dunia II",
                subtitle = "Selesai hari ini",
                isCompleted = true
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun StudyProgressScreenPreview() {
    StudyProgressScreen(rememberNavController())
}