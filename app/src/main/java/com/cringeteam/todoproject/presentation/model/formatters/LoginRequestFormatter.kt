package com.cringeteam.todoproject.presentation.model.formatters

import com.cringeteam.todoproject.domain.model.LoginRequest
import com.cringeteam.todoproject.presentation.model.LoginRequestVo

class LoginRequestFormatter {

    fun format(request: LoginRequestVo): LoginRequest {
        return LoginRequest(
            login = request.login,
            password = request.password,
        )
    }
}