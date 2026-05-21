package com.example.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Aquí definimos las rutas temporales de la app
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object TaskList : Screen("task_list")
    object AddTask : Screen("add_task")
    object EditTask : Screen("edit_task")
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            // Después cambias PlaceholderSplashScreen por tu pantalla real
            PlaceholderSplashScreen(
                onFinish = {
                    navController.navigate(Screen.TaskList.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.TaskList.route) {
            // Después cambias PlaceholderTaskListScreen por tu pantalla real
            PlaceholderTaskListScreen(
                onAddClick = {
                    navController.navigate(Screen.AddTask.route)
                },
                onEditClick = {
                    navController.navigate(Screen.EditTask.route)
                }
            )
        }

        composable(Screen.AddTask.route) {
            // Después cambias PlaceholderAddTaskScreen por tu pantalla real
            PlaceholderAddTaskScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.EditTask.route) {
            // Después cambias PlaceholderEditTaskScreen por tu pantalla real
            PlaceholderEditTaskScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}