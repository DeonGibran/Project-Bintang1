package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

val PrimaryIndigo = Color(0xFF4F46E5)
val LightIndigoBg = Color(0xFFEEF2FF)
val SlateBackground = Color(0xFFF8FAFC)
val SlateBorder = Color(0xFFE2E8F0)
val TextMuted = Color(0xFF64748B)

enum class UserRole {
    TEACHER, STUDENT
}

@Composable
fun CreateAccountScreen(navController: NavController,
    onRegisterClicked: (String, String, String, UserRole) -> Unit = { _, _, _, _ -> },
    onLoginClicked: () -> Unit = {}
) {
    var selectedRole by remember { mutableStateOf(UserRole.TEACHER) }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Badge Kecil
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(LightIndigoBg)
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "✨ Mulai langkahmu hari ini",
                    color = PrimaryIndigo,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Judul Utama
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Buat Akun Baru",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Text(
                    text = "Lengkapi data di bawah ini untuk memulai pengalaman terbaikmu.",
                    fontSize = 13.sp,
                    color = TextMuted,
                    lineHeight = 18.sp
                )
            }

            // Pilih Peran Anda
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "PILIH PERAN ANDA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextMuted,
                        letterSpacing = 0.5.sp
                    )
                    Surface(
                        color = Color(0xFFFFF1F2),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "Wajib",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            color = Color(0xFFF43F5E),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Opsi Guru / Pendidik
                RoleCard(
                    title = "Guru / Pendidik",
                    subtitle = "Kelola kelas, materi & evaluasi",
                    badgeText = "Utama",
                    badgeColor = Color(0xFFF43F5E),
                    isSelected = selectedRole == UserRole.TEACHER,
                    icon = Icons.Default.Person,
                    onClick = { selectedRole = UserRole.TEACHER }
                )

                // Opsi Siswa / Pelajar
                RoleCard(
                    title = "Siswa / Pelajar",
                    subtitle = "Akses materi pelajaran & tugas",
                    badgeText = "Akses Belajar",
                    badgeColor = Color(0xFFE2E8F0),
                    badgeTextColor = Color(0xFF475569),
                    isSelected = selectedRole == UserRole.STUDENT,
                    icon = Icons.Default.Person,
                    onClick = { selectedRole = UserRole.STUDENT }
                )
            }

            // Form Inputs
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Nama Lengkap
                CustomTextField(
                    label = "NAMA LENGKAP",
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = "Contoh: Budi Santoso",
                    leadingIcon = Icons.Default.Person
                )

                // Alamat Email
                CustomTextField(
                    label = "ALAMAT EMAIL",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "nama@email.com",
                    leadingIcon = Icons.Outlined.Email,
                    keyboardType = KeyboardType.Email
                )

                // Kata Sandi
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "KATA SANDI",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextMuted,
                        letterSpacing = 0.5.sp
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Minimal 8 karakter", color = Color(0xFF94A3B8), fontSize = 13.sp) },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(18.dp)) },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                                    contentDescription = null,
                                    tint = Color(0xFF94A3B8),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryIndigo,
                            unfocusedBorderColor = SlateBorder,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = SlateBackground
                        ),
                        singleLine = true
                    )

                    // Indikator Kekuatan Sandi
                    PasswordStrengthIndicator(password = password)
                }
            }

            // Checkbox Syarat & Ketentuan
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = { agreedToTerms = it },
                    colors = CheckboxDefaults.colors(checkedColor = PrimaryIndigo)
                )
                Text(
                    text = buildAnnotatedString {
                        append("Saya menyetujui ")
                        withStyle(SpanStyle(color = PrimaryIndigo, fontWeight = FontWeight.Bold)) {
                            append("Syarat & Ketentuan")
                        }
                        append(" serta ")
                        withStyle(SpanStyle(color = PrimaryIndigo, fontWeight = FontWeight.Bold)) {
                            append("Kebijakan Privasi")
                        }
                        append(" yang berlaku.")
                    },
                    fontSize = 12.sp,
                    color = TextMuted,
                    lineHeight = 16.sp,
                    modifier = Modifier.clickable { agreedToTerms = !agreedToTerms }
                )
            }

            // Tombol Daftar Sekarang
            Button(
                onClick = { onRegisterClicked(fullName, email, password, selectedRole) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Daftar Sekarang",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Pemisah "atau daftar dengan"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(modifier = Modifier.weight(1f), color = SlateBorder)
                Text(
                    text = "atau daftar dengan",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontSize = 11.sp,
                    color = TextMuted
                )
                Divider(modifier = Modifier.weight(1f), color = SlateBorder)
            }

            // Tombol Sosial Google & Apple
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { /* Handle Google Login */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, SlateBorder),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                ) {
                    Text(text = "Google", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = TextDark)
                }

                OutlinedButton(
                    onClick = { /* Handle Apple Login */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, SlateBorder),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                ) {
                    Text(text = "Apple", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = TextDark)
                }
            }

            // Tautan Masuk di Sini
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sudah punya akun? ",
                    fontSize = 12.sp,
                    color = TextMuted
                )
                TextButton(onClick = onLoginClicked, contentPadding = PaddingValues(0.dp)) {
                    Text(
                        text = "Masuk di sini >",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryIndigo
                    )
                }
            }
        }
    }
}

@Composable
fun RoleCard(
    title: String,
    subtitle: String,
    badgeText: String,
    badgeColor: Color,
    badgeTextColor: Color = Color.White,
    isSelected: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) PrimaryIndigo else SlateBorder
    val backgroundColor = if (isSelected) LightIndigoBg.copy(alpha = 0.5f) else Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Icon Kotak Peran
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) PrimaryIndigo else SlateBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) Color.White else PrimaryIndigo,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Keterangan Teks
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) TextDark else TextMuted
                    )
                    Surface(
                        color = badgeColor,
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = badgeText,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            color = badgeTextColor,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }

            // Radio / Check Icon Indikator
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) PrimaryIndigo else Color.Transparent)
                    .border(2.dp, if (isSelected) PrimaryIndigo else SlateBorder, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: androidx.compose.ui.graphics.vector.ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = TextMuted,
            letterSpacing = 0.5.sp
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color(0xFF94A3B8), fontSize = 13.sp) },
            leadingIcon = { Icon(leadingIcon, contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(18.dp)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryIndigo,
                unfocusedBorderColor = SlateBorder,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = SlateBackground
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true
        )
    }
}

@Composable
fun PasswordStrengthIndicator(password: String) {
    val strengthText = when {
        password.isEmpty() -> "Belum diisi"
        password.length < 6 -> "Lemah"
        password.length < 8 -> "Cukup"
        else -> "Kuat"
    }

    val strengthColor = when (strengthText) {
        "Lemah" -> Color(0xFFF43F5E)
        "Cukup" -> Color(0xFFF59E0B)
        "Kuat" -> Color(0xFF10B981)
        else -> Color(0xFF94A3B8)
    }

    val activeBars = when (strengthText) {
        "Lemah" -> 1
        "Cukup" -> 2
        "Kuat" -> 4
        else -> 0
    }

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Kekuatan Kata Sandi", fontSize = 11.sp, color = TextMuted)
            Text(text = strengthText, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = strengthColor)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (i in 1..4) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(50))
                        .background(if (i <= activeBars) strengthColor else SlateBorder)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 392, heightDp = 850)
@Composable
fun CreateAccountScreenPreview() {
    CreateAccountScreen(rememberNavController())
}