package com.cringeteam.todoproject.data.repository

import com.cringeteam.todoproject.data.rest.api.RetrofitApi
import com.cringeteam.todoproject.domain.model.LoginRequest
import com.cringeteam.todoproject.domain.model.LoginResponse
import com.cringeteam.todoproject.data.dto.loginRequest.LoginRequestMapper
import com.cringeteam.todoproject.data.dto.loginResponse.LoginResponseMapper
import com.cringeteam.todoproject.domain.repository.RestRepository
import io.reactivex.rxjava3.core.Single

class RestRepositoryImpl : RestRepository {

    private val requestMapper = LoginRequestMapper()
    private val responseMapper = LoginResponseMapper()

    override fun getLoginAccess(request: LoginRequest): Single<LoginResponse> {

        val requestDto = requestMapper.format(request)

        return RetrofitApi.retrofit.getLoginAccess(
            login = requestDto.login,
            password = requestDto.password,
        ).map { dto ->
            responseMapper.format(dto)
        }
    }
}