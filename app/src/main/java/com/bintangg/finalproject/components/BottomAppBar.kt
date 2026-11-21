package com.bintangg.finalproject.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ShowChart
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.navigation.AppRoute

private val BackgroundWhite = Color(0xFFFFFFFF)
private val PrimaryBlue = Color(0xFF0052CC)
private val TextSelectedWhite = Color(0xFFFFFFFF)
private val TextUnselectedDark = Color(0xFF64748B)

private data class BottomNavigationItem(
    val destination: AppRoute,
    val label: String,
    val icon: ImageVector
)

private val bottomNavigationItems = listOf(
    BottomNavigationItem(AppRoute.DASHBOARD, "Home", Icons.Outlined.Home),
    BottomNavigationItem(AppRoute.TASK_LIST, "Tasks", Icons.Outlined.CheckCircle),
    BottomNavigationItem(AppRoute.STUDY_PROGRESS, "Stats", Icons.AutoMirrored.Outlined.ShowChart)
)

@Composable
fun CustomBottomNavigationBar(
    currentRoute: AppRoute?,
    onNavigate: (AppRoute) -> Unit
) {
    NavigationBar(
        containerColor = BackgroundWhite,
        tonalElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        bottomNavigationItems.forEach { item ->
            val isSelected = currentRoute == item.destination
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 12.sp
                    )
                },
                selected = isSelected,
                onClick = { onNavigate(item.destination) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = PrimaryBlue,
                    selectedIconColor = TextSelectedWhite,
                    selectedTextColor = PrimaryBlue,
                    unselectedIconColor = TextUnselectedDark,
                    unselectedTextColor = TextUnselectedDark
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1E1E1E)
@Composable
fun CustomBottomNavigationBarPreview() {
    Row(modifier = Modifier.padding(top = 50.dp)) {
        CustomBottomNavigationBar(currentRoute = AppRoute.DASHBOARD, onNavigate = {})
    }
}