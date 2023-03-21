package com.cringeteam.todoproject.presentation.features.loginScreen

import com.cringeteam.todoproject.domain.usecases.SendLoginRequestUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.LoginRequestModel
import com.cringeteam.todoproject.presentation.model.formatters.LoginRequestFormatter
import io.reactivex.rxjava3.disposables.Disposable

class LoginViewModel : BaseViewModel() {

    private var loginRequestDisposable: Disposable? = null

    private val sendLoginRequestUseCase = SendLoginRequestUseCase()
    private val loginRequestFormatter = LoginRequestFormatter()

    fun onLoginClick(login: String, password: String) {
        sendLoginRequestUseCase.execute(
            loginRequestFormatter.format(
                request = LoginRequestModel(login, password)
            )
        )
    }

    override fun onCleared() {
        loginRequestDisposable?.dispose()
        loginRequestDisposable = null
        super.onCleared()
    }
}