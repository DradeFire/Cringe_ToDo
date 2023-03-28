package com.cringeteam.todoproject.data.repository

import com.cringeteam.todoproject.data.rest.model.loginRequest.LoginRequestMapper
import com.cringeteam.todoproject.data.rest.model.loginResponse.LoginResponseDto
import com.cringeteam.todoproject.data.rest.model.loginResponse.LoginResponseMapper
import com.cringeteam.todoproject.domain.model.LoginRequest
import com.cringeteam.todoproject.domain.model.LoginResponse
import com.cringeteam.todoproject.domain.repository.RestRepository
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

// TODO: Delete it when backend will be ready
class RestRepositoryFakeImpl : RestRepository {

    private val requestMapper = LoginRequestMapper()
    private val responseMapper = LoginResponseMapper()

    override fun getLoginAccess(request: LoginRequest): Single<LoginResponse> {

        val requestDto = requestMapper.map(request)

        val isAuthorizationCorrect = requestDto.login == "test" && requestDto.password == " "

        val fakeRequestDelay = 5L

        return if (isAuthorizationCorrect) {
            val fakeResponse = LoginResponseDto(
                token = "testToken",
                userId = -1L,
                login = "testLogin",
            )

            Single.just(responseMapper.map(fakeResponse))
                .delay(fakeRequestDelay, TimeUnit.SECONDS)

        } else {
            Single.error<LoginResponse>(Exception("Wrong login or Password"))
                .delay(fakeRequestDelay, TimeUnit.SECONDS)
        }
    }
}