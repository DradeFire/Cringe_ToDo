package com.cringeteam.todoproject.presentation.model.registration

import com.cringeteam.todoproject.domain.model.RegistrationUser

class RegistrationUserFormatter {

    fun format(request: RegistrationUserVo): RegistrationUser {
        return RegistrationUser(
            login = request.login,
            password = request.password,
            passwordConfirm = request.passwordConfirm
        )
    }
}