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
import com.bintangg.finalproject.ui.theme.FinalProjectTheme

val PrimaryIndigo = Purple
val LightIndigoBg = PubBlue
val SlateBackground = Panel2
val SlateBorder = Border
val TextMuted = Muted

enum class UserRole {
    TEACHER, STUDENT
}

@Composable
fun CreateAccountScreen(
    onLoginClicked: () -> Unit,
    onRegisterClicked: (String, String, String, UserRole) -> Unit = { _, _, _, _ -> }
) {
    var selectedRole by remember { mutableStateOf(UserRole.TEACHER) }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppBg
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
                    .background(PubBlue)
                    .border(BorderStroke(1.dp, Border), RoundedCornerShape(50))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "✨ Mulai langkahmu hari ini",
                    color = Blue,
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
                    color = MainText
                )
                Text(
                    text = "Lengkapi data di bawah ini untuk memulai pengalaman terbaikmu.",
                    fontSize = 13.sp,
                    color = Muted,
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
                        color = Muted,
                        letterSpacing = 0.5.sp
                    )
                    Surface(
                        color = Panel2,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "Wajib",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            color = Red,
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
                    badgeColor = PubBlue,
                    badgeTextColor = Blue,
                    isSelected = selectedRole == UserRole.TEACHER,
                    icon = Icons.Default.Person,
                    onClick = { selectedRole = UserRole.TEACHER }
                )

                // Opsi Siswa / Pelajar
                RoleCard(
                    title = "Siswa / Pelajar",
                    subtitle = "Akses materi pelajaran & tugas",
                    badgeText = "Akses Belajar",
                    badgeColor = Panel2,
                    badgeTextColor = Muted,
                    isSelected = selectedRole == UserRole.STUDENT,
                    icon = Icons.Default.Person,
                    onClick = { selectedRole = UserRole.STUDENT }
                )
            }

            // Form Inputs
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                CustomTextField(
                    label = "NAMA LENGKAP",
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = "Contoh: Budi Santoso",
                    leadingIcon = Icons.Default.Person
                )

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
                        color = Muted,
                        letterSpacing = 0.5.sp
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Minimal 8 karakter", color = Muted, fontSize = 13.sp) },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Muted, modifier = Modifier.size(18.dp)) },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                                    contentDescription = null,
                                    tint = Muted,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Border,
                            focusedContainerColor = Panel2,
                            unfocusedContainerColor = Panel2,
                            focusedTextColor = MainText,
                            unfocusedTextColor = MainText
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
                    colors = CheckboxDefaults.colors(checkedColor = Purple, uncheckedColor = Muted)
                )
                Text(
                    text = buildAnnotatedString {
                        append("Saya menyetujui ")
                        withStyle(SpanStyle(color = Blue, fontWeight = FontWeight.Bold)) {
                            append("Syarat & Ketentuan")
                        }
                        append(" serta ")
                        withStyle(SpanStyle(color = Blue, fontWeight = FontWeight.Bold)) {
                            append("Kebijakan Privasi")
                        }
                        append(" yang berlaku.")
                    },
                    fontSize = 12.sp,
                    color = Muted,
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
                colors = ButtonDefaults.buttonColors(containerColor = Purple),
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
                HorizontalDivider(modifier = Modifier.weight(1f), color = Border)
                Text(
                    text = "atau daftar dengan",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontSize = 11.sp,
                    color = Muted
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = Border)
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
                    border = BorderStroke(1.dp, Border),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Panel2)
                ) {
                    Text(text = "Google", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MainText)
                }

                OutlinedButton(
                    onClick = { /* Handle Apple Login */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, Border),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Panel2)
                ) {
                    Text(text = "Apple", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MainText)
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
                    color = Muted
                )
                TextButton(onClick = onLoginClicked, contentPadding = PaddingValues(0.dp)) {
                    Text(
                        text = "Masuk di sini >",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Blue
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
    val borderColor = if (isSelected) Blue else Border
    val backgroundColor = if (isSelected) PubBlue else Panel

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(if (isSelected) 2.dp else 1.dp, borderColor, RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) Purple else Panel2),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MainText
                    )
                    Surface(
                        color = badgeColor,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = badgeText,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            color = badgeTextColor,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Muted
                )
            }

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .border(
                        1.5.dp,
                        if (isSelected) Blue else Border,
                        CircleShape
                    )
                    .background(
                        if (isSelected) Blue else Color.Transparent,
                        CircleShape
                    ),
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
            color = Muted,
            letterSpacing = 0.5.sp
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Muted, fontSize = 13.sp) },
            leadingIcon = { Icon(leadingIcon, contentDescription = null, tint = Muted, modifier = Modifier.size(18.dp)) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Blue,
                unfocusedBorderColor = Border,
                focusedContainerColor = Panel2,
                unfocusedContainerColor = Panel2,
                focusedTextColor = MainText,
                unfocusedTextColor = MainText
            ),
            singleLine = true
        )
    }
}

@Composable
fun PasswordStrengthIndicator(password: String) {
    val strength = when {
        password.isEmpty() -> 0
        password.length < 6 -> 1
        password.length < 10 -> 2
        else -> 3
    }

    val (label, color) = when (strength) {
        1 -> "Lemah" to Red
        2 -> "Sedang" to Amber
        3 -> "Kuat" to Green
        else -> "" to Color.Transparent
    }

    if (password.isNotEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(if (index < strength) color else Panel2)
                    )
                }
            }
            Text(
                text = "Kekuatan Sandi: $label",
                fontSize = 11.sp,
                color = color,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF070B17, showSystemUi = true)
@Composable
fun CreateAccountScreenPreview() {
    FinalProjectTheme {
        CreateAccountScreen(onLoginClicked = {})
    }
}
