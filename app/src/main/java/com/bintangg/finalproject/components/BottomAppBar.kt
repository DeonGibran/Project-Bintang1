package com.bintangg.finalproject.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.automirrored.outlined.ShowChart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

val BackgroundWhite = Color(0xFFFFFFFF)
val PrimaryBlue = Color(0xFF0052CC)
val TextSelectedWhite = Color(0xFFFFFFFF)
val TextUnselectedDark = Color(0xFF64748B)

@Composable
fun CustomBottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf("Home", "Tasks", "Stats")
    val routes = listOf("Dashboard", "TaskList", "StudyProgress")
    val icons = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.CheckCircle,
        Icons.AutoMirrored.Outlined.ShowChart
    )

    NavigationBar(
        containerColor = BackgroundWhite,
        tonalElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items.forEachIndexed { index, item ->
                val route = routes[index]
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == route
                } == true

                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item
                        )
                    },
                    label = {
                        Text(
                            text = item,
                            fontSize = 12.sp
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(route) {
                            // Cek apakah graf sudah terpasang untuk menghindari crash IllegalStateException
                            navController.graph.findStartDestination().let { startDest ->
                                popUpTo(startDest.id) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
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
}

@Preview(showBackground = true, backgroundColor = 0xFF1E1E1E)
@Composable
fun CustomBottomNavigationBarPreview() {
    Row(modifier = Modifier.padding(top = 50.dp)) {
        CustomBottomNavigationBar(rememberNavController())
    }
}