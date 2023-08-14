package com.cringeteam.todoproject.presentation.model.login

import com.cringeteam.todoproject.domain.model.LoginResponse

class LoginResponseFormatter {
    fun format(response: LoginResponse): LoginResponseVo {
        return LoginResponseVo(
            token = response.token,
            userId = response.userId,
            login = response.login,
        )
    }
}