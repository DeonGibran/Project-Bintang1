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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.data.ScheduleStatus
import com.bintangg.finalproject.screens.Blue
import com.bintangg.finalproject.screens.Border
import com.bintangg.finalproject.screens.Green
import com.bintangg.finalproject.screens.MainText
import com.bintangg.finalproject.screens.Muted
import com.bintangg.finalproject.screens.Panel
import com.bintangg.finalproject.screens.Panel2
import com.bintangg.finalproject.screens.PubBlue

@Composable
fun TimelineItem(
    time: String,
    title: String,
    location: String,
    status: ScheduleStatus,
    isLast: Boolean = false
) {
    val isOngoing = status == ScheduleStatus.ONGOING

    val circleColor = when (status) {
        ScheduleStatus.DONE -> Green
        ScheduleStatus.ONGOING -> Blue
        ScheduleStatus.UPCOMING -> Muted
    }
    val cardBg = Panel
    val cardBorder = if (isOngoing) Blue else Border
    val badgeBg = if (isOngoing) PubBlue else Panel2
    val badgeText = if (isOngoing) Blue else Muted
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
                        tint = Color.White,
                        modifier = Modifier.size(8.dp)
                    )
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(Border)
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
                color = Muted,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                border = BorderStroke(1.dp, cardBorder),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (isOngoing) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .fillMaxHeight()
                                .background(Blue)
                        )
                    }
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
                                color = MainText,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                color = badgeBg,
                                shape = RoundedCornerShape(6.dp),
                                border = if (!isOngoing) BorderStroke(1.dp, Border) else null
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
                            color = Muted,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
