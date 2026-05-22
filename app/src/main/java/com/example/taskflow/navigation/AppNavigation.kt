package com.example.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskflow.screens.SplashScreen
import com.example.taskflow.screens.TaskDetailScreen
import com.example.taskflow.screens.TaskListScreen
import com.example.taskflow.vmodel.TaskViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel()

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(
                onGoToTaskList = {
                    navController.navigate("taskList") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("taskList") {
            TaskListScreen(
                viewModel = viewModel,
                onGoToAddTask = {
                    navController.navigate("taskDetail/-1")
                },
                onGoToEditTask = { taskId ->
                    navController.navigate("taskDetail/$taskId")
                }
            )
        }

        composable(
            route = "taskDetail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            TaskDetailScreen(
                viewModel = viewModel,
                taskId = taskId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}