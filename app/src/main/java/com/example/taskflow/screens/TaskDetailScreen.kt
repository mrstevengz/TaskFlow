package com.example.taskflow.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.taskflow.model.Task
import com.example.taskflow.vmodel.TaskViewModel

@Composable
fun TaskDetailScreen(
    viewModel: TaskViewModel,
    taskId: Int,
    onBack: () -> Unit
) {
    val isEditing = taskId != -1

    LaunchedEffect(taskId) {
        viewModel.loadTask(taskId)
    }

    Scaffold() { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = viewModel.id,
                onValueChange = viewModel::onIdChange,
                label = { Text("ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                enabled = !isEditing
            )
            OutlinedTextField(
                value = viewModel.taskTitle,
                onValueChange = viewModel::onTitleChange,
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.description,
                onValueChange = viewModel::onDescriptionChange,
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = viewModel.completed,
                    onCheckedChange = viewModel::onIsCompletedChange
                )
                Text("Completed")
            }
            Button(
                onClick = {
                    if (isEditing) {
                        viewModel.updateTask(
                            Task(taskId, viewModel.taskTitle, viewModel.description, viewModel.completed)
                        )
                    } else {
                        viewModel.addTask()
                    }
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isEditing) "Save Changes" else "Add Task")
            }
        }
    }
}