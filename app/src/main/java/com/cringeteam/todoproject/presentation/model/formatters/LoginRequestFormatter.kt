package com.cringeteam.todoproject.presentation.model.formatters

import com.cringeteam.todoproject.domain.model.LoginUser
import com.cringeteam.todoproject.presentation.model.LoginUserVo

class LoginRequestFormatter {

    fun format(request: LoginUserVo): LoginUser {
        return LoginUser(
            login = request.login,
            password = request.password,
        )
    }
}