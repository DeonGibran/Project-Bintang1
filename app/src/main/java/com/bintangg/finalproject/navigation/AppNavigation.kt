package com.bintangg.finalproject.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bintangg.finalproject.components.CustomBottomNavigationBar
import com.bintangg.finalproject.screens.AddCategoryScreen
import com.bintangg.finalproject.screens.AddRoleScreen
import com.bintangg.finalproject.screens.AddScheduleScreen
import com.bintangg.finalproject.screens.AddTaskBottomSheetScreen
import com.bintangg.finalproject.screens.CategoryListScreen
import com.bintangg.finalproject.screens.CreateAccountScreen
import com.bintangg.finalproject.screens.DashboardScreen
import com.bintangg.finalproject.screens.EditCategoryScreen
import com.bintangg.finalproject.screens.EditRoleScreen
import com.bintangg.finalproject.screens.EditScheduleScreen
import com.bintangg.finalproject.screens.EditTaskScreen
import com.bintangg.finalproject.screens.LoginScreen
import com.bintangg.finalproject.screens.StudyProgressScreen
import com.bintangg.finalproject.screens.TaskListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = AppRoute.entries.firstOrNull {
        it.route == backStackEntry?.destination?.route
    }
    val onBack: () -> Unit = { navController.popBackStack() }
    val onNavigate: (AppRoute) -> Unit = { destination ->
        navController.navigate(destination.route) {
            launchSingleTop = true
            if (destination.showBottomBar) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (currentRoute?.showBottomBar == true) {
                CustomBottomNavigationBar(
                    currentRoute = currentRoute,
                    onNavigate = onNavigate
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.DASHBOARD.route,
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            composable(AppRoute.DASHBOARD.route) {
                DashboardScreen(
                    onOpenTasks = { onNavigate(AppRoute.TASK_LIST) },
                    onEditSchedule = { onNavigate(AppRoute.EDIT_SCHEDULE) },
                    onAddCategory = { onNavigate(AppRoute.ADD_CATEGORY) },
                    onAddRole = { onNavigate(AppRoute.ADD_ROLE) },
                    onCreateAccount = { onNavigate(AppRoute.CREATE_ACCOUNT) },
                    onAddSchedule = { onNavigate(AppRoute.ADD_SCHEDULE) },
                    onAddTask = { onNavigate(AppRoute.ADD_TASK) },
                    onEditTask = { onNavigate(AppRoute.EDIT_TASK) },
                    onOpenCategories = { onNavigate(AppRoute.CATEGORY_LIST) }
                )
            }
            composable(AppRoute.TASK_LIST.route) {
                TaskListScreen(onAddTask = { onNavigate(AppRoute.ADD_TASK) })
            }
            composable(AppRoute.STUDY_PROGRESS.route) {
                StudyProgressScreen()
            }
            composable(AppRoute.ADD_TASK.route) {
                AddTaskBottomSheetScreen(onBack = onBack)
            }
            composable(AppRoute.EDIT_TASK.route) {
                EditTaskScreen(onBack = onBack)
            }
            composable(AppRoute.CATEGORY_LIST.route) {
                CategoryListScreen(
                    onAddCategory = { onNavigate(AppRoute.ADD_CATEGORY) },
                    onEditCategory = { onNavigate(AppRoute.EDIT_CATEGORY) }
                )
            }
            composable(AppRoute.ADD_CATEGORY.route) {
                AddCategoryScreen(onBack = onBack)
            }
            composable(AppRoute.EDIT_CATEGORY.route) {
                EditCategoryScreen(onBack = onBack)
            }
            composable(AppRoute.ADD_ROLE.route) {
                AddRoleScreen(onBack = onBack)
            }
            composable(AppRoute.EDIT_ROLE.route) {
                EditRoleScreen(onBack = onBack)
            }
            composable(AppRoute.ADD_SCHEDULE.route) {
                AddScheduleScreen(onBack = onBack)
            }
            composable(AppRoute.EDIT_SCHEDULE.route) {
                EditScheduleScreen(onBack = onBack)
            }
            composable(AppRoute.CREATE_ACCOUNT.route) {
                CreateAccountScreen(
                    onLoginClicked = {
                        navController.navigate(AppRoute.LOGIN.route) {
                            popUpTo(AppRoute.CREATE_ACCOUNT.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(AppRoute.LOGIN.route) {
                LoginScreen(
                    onRegisterRedirectClicked = {
                        navController.navigate(AppRoute.CREATE_ACCOUNT.route) {
                            popUpTo(AppRoute.LOGIN.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
