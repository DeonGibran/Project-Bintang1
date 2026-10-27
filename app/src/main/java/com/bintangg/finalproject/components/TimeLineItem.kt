package com.bintangg.finalproject.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.data.ScheduleStatus
import com.bintangg.finalproject.screens.BackgroundWhite
import com.bintangg.finalproject.screens.BadgeBgGrey
import com.bintangg.finalproject.screens.BadgeTextGrey
import com.bintangg.finalproject.screens.CardBgGrey
import com.bintangg.finalproject.screens.CardBorderGrey
import com.bintangg.finalproject.screens.StatusBlue
import com.bintangg.finalproject.screens.StatusGrey
import com.bintangg.finalproject.screens.StatusLightGrey
import com.bintangg.finalproject.screens.TextPrimary
import com.bintangg.finalproject.screens.TextSecondary

// --- Komponen Timeline ---
@Composable
fun TimelineItem(
    time: String,
    title: String,
    location: String,
    status: ScheduleStatus,
    isLast: Boolean = false
) {
    val isOngoing = status == ScheduleStatus.ONGOING

    // Konfigurasi warna berdasarkan status
    val circleColor = when (status) {
        ScheduleStatus.DONE -> StatusGrey
        ScheduleStatus.ONGOING -> StatusBlue
        ScheduleStatus.UPCOMING -> StatusLightGrey
    }
    val cardBg = if (isOngoing) BackgroundWhite else CardBgGrey
    val cardBorder = if (isOngoing) StatusBlue else CardBorderGrey
    val badgeBg = if (isOngoing) StatusBlue else BadgeBgGrey
    val badgeText = if (isOngoing) BackgroundWhite else BadgeTextGrey
    val badgeString = when (status) {
        ScheduleStatus.DONE -> "Selesai"
        ScheduleStatus.ONGOING -> "Sedang Berlangsung"
        ScheduleStatus.UPCOMING -> "Akan Datang"
    }

    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        // Kolom Kiri: Garis dan Lingkaran Timeline
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(20.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            // Lingkaran Timeline
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(circleColor),
                contentAlignment = Alignment.Center
            ) {
                if (status == ScheduleStatus.DONE) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = BackgroundWhite,
                        modifier = Modifier.size(8.dp)
                    )
                }
            }
            // Garis vertikal
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(StatusLightGrey)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Kolom Kanan: Konten Waktu dan Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = time,
                color = TextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                border = BorderStroke(1.dp, cardBorder),
                shape = RoundedCornerShape(4.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    // Garis aksen biru di sebelah kiri khusus untuk jadwal Ongoing
                    if (isOngoing) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .fillMaxHeight()
                                .background(StatusBlue)
                        )
                    }
                    // Konten dalam Card Jadwal
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = title,
                                color = TextPrimary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            // Badge Status
                            Surface(
                                color = badgeBg,
                                shape = RoundedCornerShape(4.dp),
                                border = if (!isOngoing) BorderStroke(1.dp, CardBorderGrey) else null
                            ) {
                                Text(
                                    text = badgeString,
                                    color = badgeText,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = location,
                            color = TextSecondary,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}