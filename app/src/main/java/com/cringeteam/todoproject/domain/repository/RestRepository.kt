package com.cringeteam.todoproject.domain.repository

import com.cringeteam.todoproject.domain.model.Group
import com.cringeteam.todoproject.domain.model.LoginRequest
import com.cringeteam.todoproject.domain.model.LoginResponse
import com.cringeteam.todoproject.domain.model.RegistrationUser
import com.cringeteam.todoproject.domain.model.StatusMessage
import io.reactivex.rxjava3.core.Single

interface RestRepository {

    fun getLoginAccess(request: LoginRequest): Single<LoginResponse>

    fun registrationUser(request: RegistrationUser): Single<StatusMessage>

    fun getGroups(): Single<List<Group>>
}