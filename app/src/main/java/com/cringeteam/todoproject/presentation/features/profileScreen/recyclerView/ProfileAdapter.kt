package com.cringeteam.todoproject.presentation.features.profileScreen.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.cringeteam.todoproject.databinding.ItemProjectProfileBinding
import com.cringeteam.todoproject.presentation.model.group.GroupVo

class ProfileAdapter : Adapter<ProfileViewHolder>() {

    private var data: List<GroupVo> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<GroupVo>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProjectProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        with(holder.binding) {
            project.text = data[position].title
        }
    }
}

class ProfileViewHolder(val binding: ItemProjectProfileBinding) : RecyclerView.ViewHolder(binding.root)