package com.cringeteam.todoproject.presentation.features.taskScreen

import com.cringeteam.todoproject.domain.usecases.GetTaskInfoUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.task.TaskFormatter
import com.cringeteam.todoproject.presentation.model.task.TaskVO
import io.reactivex.rxjava3.core.Single

class TaskViewModel : BaseViewModel() {

    private val getTaskInfoUseCase = GetTaskInfoUseCase()
    private val taskFormatter = TaskFormatter()

    fun getTaskInfo(id: Long): Single<TaskVO> {
        return getTaskInfoUseCase.execute(id).map { task ->
            taskFormatter.format(task)
        }
    }
}