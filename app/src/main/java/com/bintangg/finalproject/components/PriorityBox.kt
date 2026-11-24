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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.screens.Blue
import com.bintangg.finalproject.screens.Border
import com.bintangg.finalproject.screens.Muted
import com.bintangg.finalproject.screens.Panel2
import com.bintangg.finalproject.screens.PubBlue

@Composable
fun PriorityBox(text: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                border = BorderStroke(1.dp, if (isSelected) Blue else Border),
                shape = RoundedCornerShape(8.dp)
            )
            .background(if (isSelected) PubBlue else Panel2)
            .clickable { /* Aksi ubah prioritas */ },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Blue else Muted
        )
    }
}
