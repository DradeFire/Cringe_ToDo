package com.cringeteam.todoproject.presentation.model.formatters

import com.cringeteam.todoproject.domain.model.LoginResponse
import com.cringeteam.todoproject.presentation.model.LoginResponseVo

class LoginResponseFormatter {
    fun format(response: LoginResponse): LoginResponseVo {
        return LoginResponseVo(
            token = response.token,
            userId = response.userId,
            login = response.login,
        )
    }
}