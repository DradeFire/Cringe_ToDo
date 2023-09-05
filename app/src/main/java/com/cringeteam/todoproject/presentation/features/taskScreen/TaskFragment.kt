package com.cringeteam.todoproject.presentation.features.taskScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cringeteam.todoproject.databinding.FragmentTaskBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment

class TaskFragment : BaseFragment<FragmentTaskBinding, TaskViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTaskBinding =
        FragmentTaskBinding::inflate
    override val viewModelClass: Class<TaskViewModel> = TaskViewModel::class.java
    override val screenName: String = SCREEN_NAME

    companion object {
        private const val SCREEN_NAME = "task screen"
    }
}