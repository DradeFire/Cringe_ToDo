package com.cringeteam.todoproject.presentation.features.tasksScreen

import com.cringeteam.todoproject.common.state.ScreenState
import com.cringeteam.todoproject.domain.usecases.GetUngroupedTasksUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.task.TaskFormatter
import com.cringeteam.todoproject.presentation.model.task.TaskVO
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class TasksViewModel : BaseViewModel() {

    private val getUngroupedTasksUseCase = GetUngroupedTasksUseCase()

    private val taskFormatter = TaskFormatter()

    private val _screenState: BehaviorSubject<ScreenState> =
        BehaviorSubject.createDefault(ScreenState.Waiting)
    val screenState: BehaviorSubject<ScreenState> get() = _screenState

    fun getUngroupedTasks(): Single<List<TaskVO>> {
        return getUngroupedTasksUseCase.execute()
            .map { tasks ->
                tasks.map { task ->
                    taskFormatter.format(task)
                }
            }
    }
}