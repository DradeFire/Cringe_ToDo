package com.cringeteam.todoproject.presentation.features.loginScreen

import com.cringeteam.todoproject.domain.usecases.SendLoginRequestUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.LoginRequestVo
import com.cringeteam.todoproject.presentation.model.LoginResponseVo
import com.cringeteam.todoproject.presentation.model.formatters.LoginRequestFormatter
import com.cringeteam.todoproject.presentation.model.formatters.LoginResponseFormatter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

class LoginViewModel : BaseViewModel() {

    private var loginRequestDisposable: Disposable? = null

    private val sendLoginRequestUseCase = SendLoginRequestUseCase()
    private val loginRequestFormatter = LoginRequestFormatter()
    private val loginResponseFormatter = LoginResponseFormatter()

    fun onLoginClick(login: String, password: String): Single<LoginResponseVo> {

        val loginRequest = loginRequestFormatter.format(
            LoginRequestVo(
                login = login,
                password = password,
            )
        )

        return sendLoginRequestUseCase.execute(loginRequest)
            .map { loginResponse ->
                loginResponseFormatter.format(loginResponse)
            }
    }

    override fun onCleared() {
        loginRequestDisposable?.dispose()
        loginRequestDisposable = null
        super.onCleared()
    }
}