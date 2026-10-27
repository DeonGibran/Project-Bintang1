package com.bintangg.finalproject.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.screens.BorderGrey
import com.bintangg.finalproject.screens.PrimaryBlue
import com.bintangg.finalproject.screens.TextLabelGrey

@Composable
fun PriorityBox(text: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                border = BorderStroke(1.dp, if (isSelected) PrimaryBlue else BorderGrey),
                shape = RoundedCornerShape(4.dp)
            )
            .background(if (isSelected) PrimaryBlue.copy(alpha = 0.05f) else Color.Transparent)
            .clickable { /* Aksi ubah prioritas */ },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) PrimaryBlue else TextLabelGrey
        )
    }
}