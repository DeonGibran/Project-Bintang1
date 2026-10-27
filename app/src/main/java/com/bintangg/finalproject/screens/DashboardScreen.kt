package com.bintangg.finalproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

private val AppBg = Color(0xFF070B17)
private val Panel = Color(0xFF10192A)
private val Panel2 = Color(0xFF1A2638)
private val Border = Color(0xFF273449)
private val MainText = Color(0xFFF5F7FF)
private val Muted = Color(0xFF9AAAC3)
private val Purple = Color(0xFF5544E8)
private val Blue = Color(0xFF818BFF)
private val Green = Color(0xFF20D6A0)
private val Amber = Color(0xFFFFA51F)
private val Red = Color(0xFFFF4969)

val BackgroundWhite = Color(0xFFFFFFFF)
val TextPrimary = Color(0xFF111827)
val TextSecondary = Color(0xFF475569) // Disesuaikan agar sedikit lebih gelap untuk jadwal
val LinkBlue = Color(0xFF2563EB)

val CardBgRed = Color(0xFFFDECEE)
val CardBorderRed = Color(0xFFB42318)
val BadgeBgRed = Color(0xFFFFE4E6)
val BadgeTextRed = Color(0xFFE11D48)

val BadgeBgGrey = Color(0xFFF1F5F9)
val BadgeTextGrey = Color(0xFF475569)
val CardBorderGrey = Color(0xFFE5E7EB)
val CardBgGrey = Color(0xFFF8FAFC)

val StatusBlue = Color(0xFF2563EB)
val StatusGrey = Color(0xFFCBD5E1)
val StatusLightGrey = Color(0xFFE2E8F0)

@Composable
fun DashboardScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = AppBg) {
        Box(Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(bottom = 90.dp)) {
                Row(
                    Modifier.fillMaxWidth().background(Panel).padding(horizontal = 22.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("WELCOME BACK", color = Blue, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        Spacer(Modifier.height(5.dp))
                        Text("Dashboard", color = MainText, fontSize = 21.sp, fontWeight = FontWeight.Bold)
                    }
                    Box(Modifier.size(40.dp).clip(CircleShape).background(Panel2), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Layers, contentDescription = "Dashboard overview", tint = Color(0xFFD7DEED))
                    }
                }
                HorizontalDivider(color = Border.copy(alpha = .65f))
                Row(Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 18.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SummaryCard(Modifier.weight(1f), "12", "Pending Tasks", Icons.Default.Checklist, Blue, Color(0xFF1B2048), Color(0xFF34366F)) { navController.navigate("TaskList") }
                    SummaryCard(Modifier.weight(1f), "4", "Today's Schedules", Icons.Default.CalendarMonth, Green, Color(0xFF0D2B31), Color(0xFF15504C)) { navController.navigate("EditSchedule") }
                }
                SectionHeader("QUICK ACTIONS & SHORTCUTS", Modifier.padding(horizontal = 20.dp))
                Column(Modifier.padding(horizontal = 18.dp).padding(top = 10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        ActionTile(Modifier.weight(1f), "Add Category", Icons.Default.Folder, Blue, Color(0xFF242D50)) { navController.navigate("AddCategoryScreen") }
                        ActionTile(Modifier.weight(1f), "Add Role", Icons.Default.Groups, Amber, Color(0xFF34352F)) { navController.navigate("AddRoleScreen") }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        ActionTile(Modifier.weight(1f), "Create Account", Icons.Default.PersonAdd, Color(0xFFC18AFF), Color(0xFF302747)) { navController.navigate("CreateAccount") }
                        ActionTile(Modifier.weight(1f), "Add Schedule", Icons.Default.CalendarMonth, Green, Color(0xFF153A3B)) { navController.navigate("AddSchedule") }
                    }
                }
                Row(Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    SectionHeader("UPCOMING TASKS")
                    Text("+ New Task", color = Blue, fontSize = 12.sp, modifier = Modifier.clip(RoundedCornerShape(6.dp)).clickable { navController.navigate("AddTask") }.padding(4.dp))
                }
                Column(Modifier.padding(horizontal = 18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    TaskPreview("Final Project Submission", "Mobile App Development • High", "Today", Red) { navController.navigate("EditTask") }
                    TaskPreview("Database Schema Review", "Backend Engineering • Medium", "Tomorrow", Amber) { navController.navigate("TaskList") }
                }
                Spacer(Modifier.height(22.dp))
            }
            FloatingActionButton(
                onClick = { navController.navigate("AddTask") },
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 12.dp).size(56.dp),
                containerColor = Purple, contentColor = Color.White, shape = CircleShape
            ) { Icon(Icons.Default.Add, contentDescription = "Add new task", modifier = Modifier.size(28.dp)) }
        }
    }
}

@Composable
private fun SummaryCard(modifier: Modifier, value: String, label: String, icon: ImageVector, accent: Color, background: Color, border: Color, onClick: () -> Unit) {
    Card(modifier.height(112.dp).clickable(onClick = onClick), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = background), border = BorderStroke(1.dp, border)) {
        Column(Modifier.fillMaxSize().padding(13.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Icon(icon, contentDescription = null, tint = accent, modifier = Modifier.size(21.dp))
            Column {
                Text(value, color = MainText, fontSize = 23.sp, fontWeight = FontWeight.Bold)
                Text(label, color = Muted, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String, modifier: Modifier = Modifier) {
    Text(text, modifier = modifier, color = Muted, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = .7.sp)
}

@Composable
private fun ActionTile(modifier: Modifier, label: String, icon: ImageVector, accent: Color, iconBg: Color, onClick: () -> Unit) {
    Card(modifier.height(58.dp).clickable(onClick = onClick), shape = RoundedCornerShape(13.dp), colors = CardDefaults.cardColors(containerColor = Panel2), border = BorderStroke(1.dp, Border)) {
        Row(Modifier.fillMaxSize().padding(horizontal = 11.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(34.dp).clip(RoundedCornerShape(9.dp)).background(iconBg), contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = accent, modifier = Modifier.size(19.dp))
            }
            Spacer(Modifier.width(10.dp))
            Text(label, color = MainText, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
private fun TaskPreview(title: String, subtitle: String, due: String, accent: Color, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(13.dp), colors = CardDefaults.cardColors(containerColor = Panel), border = BorderStroke(1.dp, Color(0xFF1E2A3D))) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 13.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.padding(end = 11.dp).size(9.dp).clip(CircleShape).background(accent))
            Column(Modifier.weight(1f)) {
                Text(title, color = MainText, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(3.dp))
                Text(subtitle, color = Muted, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Spacer(Modifier.width(8.dp))
            Surface(color = Color(0xFF344258), shape = RoundedCornerShape(50)) {
                Text(due, color = Color(0xFFDDE6F5), fontSize = 11.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashBoardScreenPreview() {
    DashboardScreen(rememberNavController() )
}