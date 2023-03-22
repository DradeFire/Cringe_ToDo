package com.cringeteam.todoproject.data.dto.loginRequest

import com.cringeteam.todoproject.domain.model.LoginRequest

class LoginRequestMapper {

    fun format(request: LoginRequest): LoginRequestDto {
        return LoginRequestDto(
            login = request.login,
            password = request.password,
        )
    }
}