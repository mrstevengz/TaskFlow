package com.example.taskflow.screens

import androidx.compose.runtime.Composable

@Composable
fun TaskListScreen(
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
        ,floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(TaskDetail)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar tarea"
                )
            }
        }
    )
    { padding ->
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Lista de tareas",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (viewModel.tasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text("No hay tareas registradas")
            }
        }
        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            )
            {
                items(viewModel.tasks.size)
                {
                    Card(
                        modifier = Modifier.padding(vertical = 8.dp)
                            .fillMaxWidth()
                    )
                    {
                        Row(
                            modifier = Modifier.padding(2.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                            ,verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(viewModel.tasks[it].id.toString())
                            Text(viewModel.tasks[it].title)
                            Checkbox(
                                checked = viewModel.tasks[it].completed,
                                onCheckedChange = {}
                            )
                        }
                    }
                }
            }
        }
    }

}