package com.example.taskmanagerapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagerapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: TaskDatabase
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar base de datos
        database = TaskDatabase.getDatabase(this)

        // Inicializar adapter con callbacks
        taskAdapter = TaskAdapter(
            emptyList(),
            onItemClick = { task ->
                // Editar tarea
                val intent = Intent(this, EditTaskActivity::class.java)
                intent.putExtra("taskId", task.id)
                startActivity(intent)
            },
            onDeleteClick = { task ->
                // Eliminar tarea
                lifecycleScope.launch {
                    database.taskDao().deleteTask(task)
                }
            },
            onCheckChange = { task, isChecked ->
                // Marcar completada
                lifecycleScope.launch {
                    val updatedTask = task.copy(is_completed = isChecked)
                    database.taskDao().updateTask(updatedTask)
                }
            }
        )

        // Configurar RecyclerView
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTasks.adapter = taskAdapter

        // Botón para agregar tarea
        binding.buttonAddTask.setOnClickListener {
            startActivity(Intent(this, EditTaskActivity::class.java))
        }

        // Observar cambios en Room
        lifecycleScope.launch {
            database.taskDao().getAllTasks().collect { tasks ->
                taskAdapter.updateTasks(tasks)
            }
        }
    }
}
