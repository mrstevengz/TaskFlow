package com.example.taskflow.vmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskflow.model.Task

class TaskViewModel: ViewModel() {
    private val repository = TaskRepository()

    var tasks by mutableStateOf(listOf<Task>())
        private set

    var id by mutableStateOf("")
        private set

    var taskTitle by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var completed by mutableStateOf(false)
        private set

    init {
        loadTask()
    }

    private fun loadTask() {
        tasks = repository.getTasks()
    }

    fun loadTask(taskID: Int) {
        if (taskID == -1) {
            id = ""
            taskTitle = ""
            description = ""
            completed = false
            return
        } else {
            val task = repository.getTask(taskID)
            task?.let {
                id = it.id.toString()
                taskTitle = it.taskTitle
                description = it.description
                completed = it.completed
            }
        }
    }

    fun addTask() {
        repository.addTask(Task(
            id.toInt(),
            taskTitle,
            description,
            completed
        ))
        loadTask()
    }

    fun deleteTask(task: Task) {
        repository.deleteTask(task)
        loadTask()
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
        loadTask()
    }

    fun onIdChange(value: String) {
        id = value
    }

    fun onTitleChange(value: String) {
        taskTitle = value
    }

    fun onDescriptionChange(value: String) {
        description = value
    }

    fun onIsCompletedChange(value: Boolean) {
        completed= value
    }
}
