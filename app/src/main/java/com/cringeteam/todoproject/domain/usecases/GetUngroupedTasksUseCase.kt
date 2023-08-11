package com.cringeteam.todoproject.domain.usecases

import com.cringeteam.todoproject.data.repository.RestRepositoryFakeImpl
import com.cringeteam.todoproject.domain.model.Task
import com.cringeteam.todoproject.domain.repository.RestRepository
import io.reactivex.rxjava3.core.Single

class GetUngroupedTasksUseCase {
    // TODO: replace with real repository when backend will be ready
    private val repository: RestRepository = RestRepositoryFakeImpl()

    fun execute(): Single<List<Task>> {
        return repository.getUngroupedTasks()
    }
}