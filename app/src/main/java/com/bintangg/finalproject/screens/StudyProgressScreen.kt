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
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.outlined.Swipe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.components.SubjectItem
import com.bintangg.finalproject.ui.theme.FinalProjectTheme

// -- Palet Warna (Kompatibilitas Backward) --
val PrimaryBlue = Blue
val TextDark = MainText
val TextGray = Muted
val TrackGray = Panel2
val BorderLightGray = Border
val IconBgLight = Panel2
val IconBlueGray = Blue

@Composable
fun StudyProgressScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                color = MainText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "12/20",
                color = Blue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { 12f / 20f },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = Blue,
            trackColor = Panel2,
            strokeCap = StrokeCap.Round
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Bagian Kartu Soal ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Panel),
            border = BorderStroke(1.dp, Border),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "FISIKA - HUKUM NEWTON",
                    color = Blue,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Berapa besar gaya yang\ndiperlukan untuk\nmemberikan percepatan\n2 m/s² pada massa 5 kg?",
                    color = MainText,
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
                    colors = ButtonDefaults.buttonColors(containerColor = Purple),
                    shape = RoundedCornerShape(8.dp)
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
            color = MainText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SubjectItem(
                icon = Icons.Outlined.Science,
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
                icon = Icons.Outlined.Swipe,
                title = "Sejarah - Perang Dunia II",
                subtitle = "Selesai hari ini",
                isCompleted = true
            )
        }
    }
}
}

@Preview(showBackground = true, backgroundColor = 0xFF070B17)
@Composable
fun StudyProgressScreenPreview() {
    FinalProjectTheme {
        StudyProgressScreen()
    }
}
