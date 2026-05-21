package com.example.taskflow.model

data class Task (
    val id: Int,
    val taskTitle: String,
    val description: String,
    val Completed: Boolean = false
)