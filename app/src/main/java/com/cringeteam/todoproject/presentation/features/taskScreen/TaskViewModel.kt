package com.cringeteam.todoproject.presentation.features.taskScreen

import com.cringeteam.todoproject.common.state.ScreenState
import com.cringeteam.todoproject.domain.usecases.GetSubtasksUseCase
import com.cringeteam.todoproject.domain.usecases.GetTaskInfoUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.task.TaskFormatter
import com.cringeteam.todoproject.presentation.model.task.TaskVO
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class TaskViewModel : BaseViewModel() {

    private val getTaskInfoUseCase = GetTaskInfoUseCase()
    private val getSubtasksUseCase = GetSubtasksUseCase()

    private val taskFormatter = TaskFormatter()

    private val _screenState: BehaviorSubject<ScreenState> =
        BehaviorSubject.createDefault(ScreenState.Waiting)
    val screenState: BehaviorSubject<ScreenState> get() = _screenState

    fun getTaskInfo(id: Long): Single<TaskVO> {
        return getTaskInfoUseCase.execute(id).map { task ->
            taskFormatter.format(task)
        }
    }

    fun getSubtasks(parentId: Long): Single<List<TaskVO>> {
        return getSubtasksUseCase.execute(parentId).map { subtasks ->
            subtasks.map { task ->
                taskFormatter.format(task)
            }
        }
    }
}
