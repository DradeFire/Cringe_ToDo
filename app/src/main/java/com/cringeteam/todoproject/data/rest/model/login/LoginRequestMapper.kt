package com.cringeteam.todoproject.data.rest.model.login

import com.cringeteam.todoproject.domain.model.LoginUser

class LoginRequestMapper {

    fun map(request: LoginUser): LoginRequestDto {
        return LoginRequestDto(
            login = request.login,
            password = request.password,
        )
    }
}