package com.cursokotlin.misnotas.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.misnotas.databinding.ItemTaskBinding
import com.cursokotlin.misnotas.modelo.TaskEntity

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemTaskBinding.bind(view)

    val tvTask = binding.tvTask
    val cbIsDone = binding.cbIsDone

    fun bind(
        task: TaskEntity,
        checkTask: (TaskEntity)-> Unit,
        deleteTaskEntity: (TaskEntity) -> Unit
    ){
        tvTask.text = task.name
        cbIsDone.isChecked = task.isDone

        cbIsDone.setOnClickListener { checkTask(task) }
        itemView.setOnClickListener { deleteTaskEntity(task) }
    }
}