package com.cringeteam.todoproject.domain.repository

import com.cringeteam.todoproject.domain.model.LoginRequest
import com.cringeteam.todoproject.domain.model.LoginResponse
import io.reactivex.rxjava3.core.Single

interface RestRepository {

    fun getLoginAccess(request: LoginRequest): Single<LoginResponse>
}