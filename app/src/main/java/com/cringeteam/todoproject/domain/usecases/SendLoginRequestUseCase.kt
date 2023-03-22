package com.cringeteam.todoproject.domain.usecases

import com.cringeteam.todoproject.data.repository.RestRepositoryImpl
import com.cringeteam.todoproject.domain.model.LoginRequest
import com.cringeteam.todoproject.domain.model.LoginResponse
import com.cringeteam.todoproject.domain.repository.RestRepository
import io.reactivex.rxjava3.core.Single

class SendLoginRequestUseCase {

    // Зависимость от data слоя? лул? Или просто нет Dagger и вот так вот?
    private val repository: RestRepository = RestRepositoryImpl()

    fun execute(request: LoginRequest): Single<LoginResponse> = Single.create { response ->
        try {
            repository.getLoginAccess(request)
        } catch (e: Throwable) {
            response.onError(e)
        }
    }
}