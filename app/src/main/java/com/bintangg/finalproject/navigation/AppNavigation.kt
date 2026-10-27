package com.bintangg.finalproject.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bintangg.finalproject.components.CustomBottomNavigationBar
import com.bintangg.finalproject.screens.AddCategoryScreen
import com.bintangg.finalproject.screens.AddRoleScreen
import com.bintangg.finalproject.screens.AddTaskBottomSheetScreen
import com.bintangg.finalproject.screens.DashboardScreen
import com.bintangg.finalproject.screens.StudyProgressScreen
import com.bintangg.finalproject.screens.TaskListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Dashboard",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("AddTask") { AddTaskBottomSheetScreen(navController) }
            composable("Dashboard") { DashboardScreen(navController ) }
            composable("StudyProgress") { StudyProgressScreen(navController ) }
            composable("TaskList") { TaskListScreen(navController) }
            composable("AddRoleScreen") { AddRoleScreen(navController) }
            composable( "AddCategoryScreen") { AddCategoryScreen(navController) }
        }
    }
}
