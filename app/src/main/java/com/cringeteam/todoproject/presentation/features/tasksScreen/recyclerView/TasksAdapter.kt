package com.cringeteam.todoproject.presentation.features.tasksScreen.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cringeteam.todoproject.R
import com.cringeteam.todoproject.databinding.ItemTaskBinding
import com.cringeteam.todoproject.presentation.model.task.TaskVO

class TasksAdapter : Adapter<TasksViewHolder>() {
    private var data: List<TaskVO> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<TaskVO>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val task = data[position]
        with(holder.binding) {
            title.text = task.title
            description.text = task.description
            notificationTime.text = task.notification

            when (task.priority) {
                0 -> priorityPicker.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.black
                    ),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                1 -> priorityPicker.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.yellow
                    ),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                2 -> priorityPicker.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.red
                    ),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                else -> priorityPicker.setColorFilter(R.color.black)
            }

            complete.setOnClickListener {
                // TODO: setup listener
            }
            notificationPicker.setOnClickListener {
                // TODO: setup listener
            }
            priorityPicker.setOnClickListener {
                // TODO: setup listener
            }
            alarmPicker.setOnClickListener {
                // TODO: setup listener
            }
        }
    }
}

class TasksViewHolder(val binding: ItemTaskBinding) : ViewHolder(binding.root)