package com.example.taskflow.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskflow.vmodel.TaskViewModel

@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onGoToAddTask: () -> Unit,
    onGoToEditTask: (Int) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onGoToAddTask) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Task")
            }
        }
    ) { padding ->
        if (viewModel.tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay tareas. Agrega una")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.tasks) { task ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onGoToEditTask(task.id) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = task.completed,
                                onCheckedChange = {
                                    viewModel.updateTask(task.copy(completed = it))
                                }
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = task.taskTitle,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                if (task.description.isNotBlank()) {
                                    Text(
                                        text = task.description,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            IconButton(onClick = { viewModel.deleteTask(task) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}