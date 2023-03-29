package com.cringeteam.todoproject.data.rest.model.loginRequest

import com.cringeteam.todoproject.domain.model.LoginRequest

class LoginRequestMapper {

    fun map(request: LoginRequest): LoginRequestDto {
        return LoginRequestDto(
            login = request.login,
            password = request.password,
        )
    }
}