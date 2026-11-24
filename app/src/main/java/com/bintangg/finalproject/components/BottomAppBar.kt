package com.bintangg.finalproject.components

import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bintangg.finalproject.navigation.AppRoute
import com.bintangg.finalproject.screens.Blue
import com.bintangg.finalproject.screens.Border
import com.bintangg.finalproject.screens.Muted
import com.bintangg.finalproject.screens.Panel
import com.bintangg.finalproject.screens.PubBlue
import com.bintangg.finalproject.ui.theme.FinalProjectTheme

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
        containerColor = Panel,
        tonalElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .border(1.dp, Border, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
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
                    indicatorColor = PubBlue,
                    selectedIconColor = Blue,
                    selectedTextColor = Blue,
                    unselectedIconColor = Muted,
                    unselectedTextColor = Muted
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF070B17)
@Composable
fun CustomBottomNavigationBarPreview() {
    FinalProjectTheme {
        Row(modifier = Modifier.padding(top = 50.dp)) {
            CustomBottomNavigationBar(currentRoute = AppRoute.DASHBOARD, onNavigate = {})
        }
    }
}
