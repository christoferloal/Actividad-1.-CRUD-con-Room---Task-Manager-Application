package com.example.taskmanagerapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.taskmanagerapp.databinding.ActivityEditTaskBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class EditTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditTaskBinding
    private lateinit var database: TaskDatabase
    private var taskId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = TaskDatabase.getDatabase(this)
        taskId = intent.getIntExtra("taskId", 0).takeIf { it != 0 }

        // Si es edición, cargar datos
        taskId?.let { id ->
            lifecycleScope.launch {
                database.taskDao().getAllTasks().collect { tasks ->
                    val task = tasks.find { it.id == id }
                    task?.let {
                        binding.editTaskTitle.setText(it.task_title)
                        binding.editTaskDescription.setText(it.task_description)
                    }
                }
            }
        }

        binding.buttonSaveTask.setOnClickListener {
            val title = binding.editTaskTitle.text.toString()
            val description = binding.editTaskDescription.text.toString()
            val createdAt = System.currentTimeMillis().toString()

            if (title.isBlank()) {
                Toast.makeText(this, "Task title cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val task = Task(
                id = taskId ?: 0,
                task_title = title,
                task_description = description,
                created_at = createdAt
            )

            lifecycleScope.launch {
                if (taskId != null) database.taskDao().updateTask(task)
                else database.taskDao().insertTask(task)
                finish()
            }
        }
    }
}
