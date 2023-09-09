package com.cringeteam.todoproject.presentation.features.taskScreen.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cringeteam.todoproject.databinding.ItemSubtaskBinding
import com.cringeteam.todoproject.presentation.model.task.TaskVO

class SubtasksAdapter(
    private val onClick: (Long) -> Unit
) : Adapter<SubtasksViewHolder>() {

    private var data: List<TaskVO> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<TaskVO>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtasksViewHolder {
        val binding = ItemSubtaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubtasksViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: SubtasksViewHolder, position: Int) {
        val task = data[position]
        with(holder.binding) {
            title.text = task.title
            description.text = task.description

            complete.setOnClickListener {
                // TODO: setup listener
            }

            root.setOnClickListener {
                onClick(task.id)
            }
        }
    }
}

class SubtasksViewHolder(val binding: ItemSubtaskBinding) : ViewHolder(binding.root)