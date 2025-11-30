package com.example.taskmanagerapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,                  // Se genera automáticamente

    val task_title: String,           // Obligatorio
    val task_description: String?,    // Opcional
    val created_at: String,           // Obligatorio
    val is_completed: Boolean = false // Por defecto false
)
