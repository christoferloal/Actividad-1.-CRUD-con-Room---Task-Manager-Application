package com.example.taskmanagerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.databinding.ItemTaskBinding

class TaskAdapter(
    private var tasks: List<Task>,
    private val onItemClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit,
    private val onCheckChange: (Task, Boolean) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.binding.textViewTitle.text = task.task_title
        holder.binding.textViewDescription.text = task.task_description
        holder.binding.checkboxCompleted.isChecked = task.is_completed

        // Editar tarea
        holder.itemView.setOnClickListener {
            onItemClick(task)
        }

        // Checkbox completada
        holder.binding.checkboxCompleted.setOnCheckedChangeListener { _, isChecked ->
            onCheckChange(task, isChecked)
        }

        // Botón eliminar
        holder.binding.buttonDeleteTask.setOnClickListener {
            onDeleteClick(task)
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
