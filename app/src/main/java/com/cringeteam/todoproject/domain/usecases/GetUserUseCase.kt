package com.cringeteam.todoproject.domain.usecases

import com.cringeteam.todoproject.data.repository.RestRepositoryFakeImpl
import com.cringeteam.todoproject.domain.model.User
import com.cringeteam.todoproject.domain.repository.RestRepository
import io.reactivex.rxjava3.core.Single

class GetUserUseCase {

    // TODO: replace with real repository when backend will be ready
    private val repository: RestRepository = RestRepositoryFakeImpl()

    fun execute() : Single<User> {
        return repository.getUser()
    }
}