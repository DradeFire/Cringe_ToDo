package com.cringeteam.todoproject.presentation.model.login

import com.cringeteam.todoproject.domain.model.LoginUser

class LoginRequestFormatter {

    fun format(request: LoginUserVo): LoginUser {
        return LoginUser(
            login = request.login,
            password = request.password,
        )
    }
}