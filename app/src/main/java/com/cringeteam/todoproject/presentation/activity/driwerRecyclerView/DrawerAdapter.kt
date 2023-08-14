package com.cringeteam.todoproject.presentation.activity.driwerRecyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cringeteam.todoproject.databinding.ItemProjectDrawerBinding
import com.cringeteam.todoproject.presentation.model.group.GroupVo

class DrawerAdapter : RecyclerView.Adapter<DrawerViewHolder>() {

    private var data: List<GroupVo> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<GroupVo>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
        val binding = ItemProjectDrawerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrawerViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {
        with(holder.binding) {
            project.text = data[position].title
        }

        // TODO: add navigate to group
    }
}

class DrawerViewHolder(val binding: ItemProjectDrawerBinding) : RecyclerView.ViewHolder(binding.root)