package com.cringeteam.todoproject.presentation.features.registrationScreen

import com.cringeteam.todoproject.domain.usecases.RegistrationUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.registration.RegistrationUserVo
import com.cringeteam.todoproject.presentation.model.registration.formatter.RegistrationUserFormatter

class RegistrationViewModel : BaseViewModel() {

    private val registrationUseCase = RegistrationUseCase()
    private val registrationRequestFormatter = RegistrationUserFormatter()

    fun onSignUpClick(
        login: String,
        password: String,
        passwordConfirm: String
    ) {

        val requestVo = RegistrationUserVo(
            login = login,
            password = password,
            passwordConfirm = passwordConfirm,
        )

        val request = registrationRequestFormatter.format(requestVo)

        registrationUseCase.execute(request)
    }
}