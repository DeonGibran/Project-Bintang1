package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.ui.theme.FinalProjectTheme

enum class LoginRole {
    SISWA, GURU
}

@Composable
fun LoginScreen(
    onRegisterRedirectClicked: () -> Unit,
    onLoginClicked: (String, String, LoginRole) -> Unit = { _, _, _ -> },
    onGoogleLoginClicked: () -> Unit = {},
    onAppleLoginClicked: () -> Unit = {},
    onForgotPasswordClicked: () -> Unit = {}
) {
    var selectedRole by remember { mutableStateOf(LoginRole.SISWA) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Tab Menu Atas (Masuk | Daftar)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Panel, RoundedCornerShape(24.dp))
                    .border(BorderStroke(1.dp, Border), RoundedCornerShape(24.dp))
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tombol "Masuk" (Aktif)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Panel2, RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Masuk",
                        fontWeight = FontWeight.Bold,
                        color = MainText,
                        fontSize = 14.sp
                    )
                }
                // Tombol "Daftar"
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { onRegisterRedirectClicked() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Daftar",
                        fontWeight = FontWeight.Medium,
                        color = Muted,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Badge Sambutan
            Surface(
                color = PubBlue,
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "📣", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Selamat Datang Kembali",
                        color = Blue,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Judul Utama
            Text(
                text = "Masuk ke Akun",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MainText
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Sub-teks
            Text(
                text = "Masukkan email dan kata sandi yang telah terdaftar untuk melanjutkan belajarmu.",
                fontSize = 13.sp,
                color = Muted,
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Pilihan Peran (Siswa / Guru)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Panel, RoundedCornerShape(16.dp))
                    .border(BorderStroke(1.dp, Border), RoundedCornerShape(16.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val isSiswa = selectedRole == LoginRole.SISWA
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSiswa) PubBlue else Color.Transparent)
                        .clickable { selectedRole = LoginRole.SISWA }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🎓", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Siswa / Pelajar",
                            fontWeight = if (isSiswa) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSiswa) Blue else Muted,
                            fontSize = 13.sp
                        )
                    }
                }

                val isGuru = selectedRole == LoginRole.GURU
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isGuru) PubBlue else Color.Transparent)
                        .clickable { selectedRole = LoginRole.GURU }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "👨‍🏫", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Guru / Pendidik",
                            fontWeight = if (isGuru) FontWeight.Bold else FontWeight.Medium,
                            color = if (isGuru) Blue else Muted,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Card Form Input Utama
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = Panel,
                border = BorderStroke(1.dp, Border),
                shadowElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    // Field Alamat Email
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Alamat Email", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MainText)
                        Text(
                            text = if (selectedRole == LoginRole.SISWA) "Akun Pelajar" else "Akun Pengajar",
                            fontSize = 11.sp,
                            color = Muted
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("nama@email.com", color = Muted, fontSize = 14.sp) },
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Muted) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Border,
                            focusedBorderColor = Blue,
                            unfocusedContainerColor = Panel2,
                            focusedContainerColor = Panel2,
                            unfocusedTextColor = MainText,
                            focusedTextColor = MainText
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Field Kata Sandi
                    Text(text = "Kata Sandi", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MainText)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Masukkan kata sandi", color = Muted, fontSize = 14.sp) },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Muted) },
                        trailingIcon = {
                            val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = null, tint = Muted)
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Border,
                            focusedBorderColor = Blue,
                            unfocusedContainerColor = Panel2,
                            focusedContainerColor = Panel2,
                            unfocusedTextColor = MainText,
                            focusedTextColor = MainText
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Opsi "Ingat Saya" & "Lupa Kata Sandi?"
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = rememberMe,
                                onCheckedChange = { rememberMe = it },
                                colors = CheckboxDefaults.colors(checkedColor = Purple, uncheckedColor = Muted)
                            )
                            Text(text = "Ingat Saya", fontSize = 13.sp, color = Muted)
                        }
                        Text(
                            text = "Lupa Kata Sandi?",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Blue,
                            modifier = Modifier.clickable { onForgotPasswordClicked() }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tombol Masuk Sekarang & Tombol Sidik Jari
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { onLoginClicked(email, password, selectedRole) },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Purple)
                        ) {
                            Text(text = "Masuk Sekarang →", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        // Tombol Biometrik / Sidik Jari
                        Surface(
                            modifier = Modifier
                                .size(50.dp)
                                .border(1.dp, Border, RoundedCornerShape(14.dp))
                                .clickable { },
                            shape = RoundedCornerShape(14.dp),
                            color = Panel2
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = "👆", fontSize = 20.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Divider "Atau Masuk Dengan"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Border)
                Text(
                    text = " ATAU MASUK DENGAN ",
                    fontSize = 11.sp,
                    color = Muted,
                    fontWeight = FontWeight.Medium
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = Border)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Tombol Social Login (Google & Apple)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { onGoogleLoginClicked() },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Border),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Panel2)
                ) {
                    Text(text = "🌐 Google", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MainText)
                }

                OutlinedButton(
                    onClick = { onAppleLoginClicked() },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Border),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Panel2)
                ) {
                    Text(text = "🍎 Apple", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MainText)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Banner Info Pelajar Aktif
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Panel,
                border = BorderStroke(1.dp, Border)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = PubBlue,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "🎓", fontSize = 16.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Lebih dari 120.000 Pelajar Aktif",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MainText
                        )
                        Text(
                            text = "Gabung ruang diskusi & materi interaktif terb...",
                            fontSize = 11.sp,
                            color = Muted
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Footer (Belum punya akun? Daftar sekarang)
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Belum punya akun?", fontSize = 13.sp, color = Muted)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Daftar sekarang →",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue,
                    modifier = Modifier.clickable { onRegisterRedirectClicked() }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF070B17, widthDp = 392, heightDp = 800)
@Composable
fun LoginScreenPreview() {
    FinalProjectTheme {
        LoginScreen(onRegisterRedirectClicked = {})
    }
}
