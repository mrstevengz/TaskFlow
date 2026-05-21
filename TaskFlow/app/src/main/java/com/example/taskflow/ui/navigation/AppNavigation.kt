package com.example.taskflow.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation() {


    val navController = rememberNavController()                         // Controlador que se encarga de manejar la navegación

    NavHost(

        navController = navController,
        startDestination = "splash"

    ) {


        composable("splash") {

            // Pantalla SplashScreen
            SplashScreen(
                onGoToTaskList = {
                    navController.navigate("taskList")

                }
            )
        }


        composable("taskList") {

            TaskListScreen(
                onGoToAddTask = {
                    navController.navigate("addTask")
                },

                onGoToEditTask = {
                    navController.navigate("editTask")

                }
            )
        }

        composable("addTask") {

            AddTaskScreen(

                // Regresar a la pantalla anterior
                onBack = {
                    navController.popBackStack()

                }
            )
        }

        composable("editTask") {

            EditTaskScreen(
                onBack = {

                    navController.popBackStack()

                }
            )
        }
    }
}