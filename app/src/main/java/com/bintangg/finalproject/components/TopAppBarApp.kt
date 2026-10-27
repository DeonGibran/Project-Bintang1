package com.bintangg.finalproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarApp(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit = {}
) {
    // Surface digunakan untuk memberikan background putih dan radius pada sudut atas
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = Color.White
    ) {
        Column {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            // Ganti R.drawable.profile_placeholder dengan resource gambar asli
                            painter = painterResource(id = R.drawable.icon),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(44.dp)
                                // Clip menggunakan RoundedCornerShape untuk menyamai bentuk "rounded square" pada gambar
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Welcome Back",
                            color = Color(0xFF004FBA), // Warna biru yang disesuaikan dengan referensi gambar
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Settings",
                            tint = Color(0xFF333333)
                        )
                    }
                },
                // Warna transparan agar mengikuti warna Surface parent
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            // Divider untuk garis bawah yang tipis
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFE0E0E0)
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF222222, // Latar belakang gelap agar lengkungan sudut atas (putih) terlihat jelas
    name = "Welcome Top App Bar"
)
@Composable
fun WelcomeTopAppBarPreview() {
    MaterialTheme {
        TopAppBarApp(
            modifier = Modifier.fillMaxWidth(),
            onSettingsClick = {
                // Aksi saat tombol pengaturan diklik pada preview (kosongkan saja)
            }
        )
    }
}