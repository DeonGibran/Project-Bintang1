package com.bintangg.finalproject.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.screens.Blue
import com.bintangg.finalproject.screens.Border
import com.bintangg.finalproject.screens.MainText
import com.bintangg.finalproject.screens.Muted
import com.bintangg.finalproject.screens.Panel
import com.bintangg.finalproject.screens.Panel2

@Composable
fun SubjectItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    isCompleted: Boolean
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Panel,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Border)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ikon Kotak Kiri
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Panel2, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Blue,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Teks Keterangan
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = MainText,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    color = Muted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            // Ikon Kanan (Chevron atau Centang)
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircleOutline,
                    contentDescription = "Selesai",
                    tint = Muted,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Lanjut",
                    tint = Muted,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
