package com.cringeteam.todoproject.data.repository

import com.cringeteam.todoproject.data.rest.retrofit.RetrofitApi
import com.cringeteam.todoproject.domain.model.LoginUser
import com.cringeteam.todoproject.domain.model.LoginResponse
import com.cringeteam.todoproject.data.rest.model.login.LoginRequestMapper
import com.cringeteam.todoproject.data.rest.model.login.LoginResponseMapper
import com.cringeteam.todoproject.data.rest.model.registration.RegistrationUserMapper
import com.cringeteam.todoproject.domain.model.Group
import com.cringeteam.todoproject.domain.model.RegistrationUser
import com.cringeteam.todoproject.domain.model.StatusMessage
import com.cringeteam.todoproject.domain.model.User
import com.cringeteam.todoproject.domain.repository.RestRepository
import io.reactivex.rxjava3.core.Single

class RestRepositoryImpl : RestRepository {

    private val loginRequestMapper = LoginRequestMapper()
    private val responseMapper = LoginResponseMapper()

    private val registrationRequestMapper = RegistrationUserMapper()

    override fun getLoginAccess(request: LoginUser): Single<LoginResponse> {

        val requestDto = loginRequestMapper.map(request)

        return RetrofitApi.retrofit.getLoginAccess(
            login = requestDto.login,
            password = requestDto.password,
        ).map { dto ->
            responseMapper.map(dto)
        }
    }

    override fun registrationUser(request: RegistrationUser): Single<StatusMessage> {
        val requestDto = registrationRequestMapper.map(request)

        return RetrofitApi.retrofit.registrationUser(requestDto)
    }

    override fun getGroups(): Single<List<Group>> {
        TODO("Not yet implemented")
    }

    override fun getUser(): Single<User> {
        TODO("Not yet implemented")
    }
}