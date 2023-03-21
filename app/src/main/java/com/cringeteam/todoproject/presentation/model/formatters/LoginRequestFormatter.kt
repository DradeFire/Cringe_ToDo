package com.cringeteam.todoproject.presentation.model.formatters

import com.cringeteam.todoproject.domain.model.LoginRequestVo
import com.cringeteam.todoproject.presentation.model.LoginRequestModel

class LoginRequestFormatter {

    fun format(request: LoginRequestModel): LoginRequestVo {
        return LoginRequestVo(
            login = request.login,
            password = request.password,
        )
    }
}