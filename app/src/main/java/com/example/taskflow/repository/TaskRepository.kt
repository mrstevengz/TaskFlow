package com.example.taskflow.repository

import com.example.taskflow.model.Task

class TaskRepository {
    private val tasks = mutableListOf<Task>()

    fun getTasks(): List<Task> = tasks.toList()

    fun getTask(id: Int): Task? = tasks.find { it.id == id }

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun deleteTask(task: Task) {
        tasks.removeIf { it.id == task.id }
    }

    fun updateTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) tasks[index] = task
    }
}