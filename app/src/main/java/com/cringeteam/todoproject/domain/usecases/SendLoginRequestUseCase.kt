package com.cringeteam.todoproject.domain.usecases

import com.cringeteam.todoproject.data.repository.RestRepositoryFakeImpl
import com.cringeteam.todoproject.domain.model.LoginRequest
import com.cringeteam.todoproject.domain.model.LoginResponse
import com.cringeteam.todoproject.domain.repository.RestRepository
import io.reactivex.rxjava3.core.Single

class SendLoginRequestUseCase {

    // TODO: replace with real repository when backend will be ready
    private val repository: RestRepository = RestRepositoryFakeImpl()

    fun execute(request: LoginRequest): Single<LoginResponse> {
        return try {
            repository.getLoginAccess(request)
        } catch (error: Throwable) {
            Single.error(error)
        }
    }
}