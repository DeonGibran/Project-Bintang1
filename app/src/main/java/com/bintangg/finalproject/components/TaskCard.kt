package com.bintangg.finalproject.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.screens.BackgroundWhite
import com.bintangg.finalproject.screens.BorderCard
import com.bintangg.finalproject.screens.TagBgBlue
import com.bintangg.finalproject.screens.TagTextBlue
import com.bintangg.finalproject.screens.TextTitleDark
import com.bintangg.finalproject.screens.TimeTextGrey
import com.bintangg.finalproject.screens.UrgentTextRed

@Composable
fun TaskCard(
    title: String,
    tag: String,
    timeText: String,
    isUrgent: Boolean,
    timeIcon: ImageVector
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BackgroundWhite),
        border = BorderStroke(1.dp, BorderCard),
        shape = RoundedCornerShape(4.dp) // Sangat sedikit radius seperti di gambar
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Kotak Checkbox Custom (Mengikuti bentuk kotak tegas pada referensi)
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(2.dp))
                    .background(Color.Transparent)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    color = TextTitleDark,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Tag Biru Muda
                    Surface(
                        color = TagBgBlue,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = tag,
                            color = TagTextBlue,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Informasi Waktu (Merah jika mendesak, abu-abu jika normal)
                    Icon(
                        imageVector = timeIcon,
                        contentDescription = null,
                        tint = if (isUrgent) UrgentTextRed else TimeTextGrey,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = timeText,
                        color = if (isUrgent) UrgentTextRed else TimeTextGrey,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}