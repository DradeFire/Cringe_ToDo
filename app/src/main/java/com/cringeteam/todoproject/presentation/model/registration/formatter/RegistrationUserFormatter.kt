package com.cringeteam.todoproject.presentation.model.registration.formatter

import com.cringeteam.todoproject.domain.model.RegistrationUser
import com.cringeteam.todoproject.presentation.model.registration.RegistrationUserVo

class RegistrationUserFormatter {

    fun format(request: RegistrationUserVo): RegistrationUser {
        return RegistrationUser(
            login = request.login,
            password = request.password,
            passwordConfirm = request.passwordConfirm
        )
    }
}