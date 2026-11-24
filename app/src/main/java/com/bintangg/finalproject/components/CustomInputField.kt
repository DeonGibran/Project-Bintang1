package com.bintangg.finalproject.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.screens.Blue
import com.bintangg.finalproject.screens.Border
import com.bintangg.finalproject.screens.MainText
import com.bintangg.finalproject.screens.Muted
import com.bintangg.finalproject.screens.Panel2

@Composable
fun CustomInputField(
    label: String,
    placeholder: String,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    trailingIcon: ImageVector? = null
) {
    Column {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = Muted
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Muted,
                    fontSize = 13.sp
                )
            },
            trailingIcon = if (trailingIcon != null) {
                {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = Muted,
                        modifier = Modifier.size(18.dp)
                    )
                }
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            textStyle = TextStyle(fontSize = 13.sp, color = MainText),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Border,
                focusedBorderColor = Blue,
                unfocusedContainerColor = Panel2,
                focusedContainerColor = Panel2
            )
        )
    }
}
