package com.cringeteam.todoproject.domain.usecases

import com.cringeteam.todoproject.data.repository.RestRepositoryFakeImpl
import com.cringeteam.todoproject.domain.model.Task
import io.reactivex.rxjava3.core.Single

class GetSubtasksUseCase {

    private val repository = RestRepositoryFakeImpl()

    fun execute(parentId: Long): Single<List<Task>> {
        return repository.getSubtasks(parentId)
    }
}