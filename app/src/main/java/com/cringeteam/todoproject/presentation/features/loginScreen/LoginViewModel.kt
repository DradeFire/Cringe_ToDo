package com.cringeteam.todoproject.presentation.features.loginScreen

import com.cringeteam.todoproject.common.logger.Logger
import com.cringeteam.todoproject.common.state.LoginScreenState
import com.cringeteam.todoproject.domain.usecases.SendLoginRequestUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.LoginRequestVo
import com.cringeteam.todoproject.presentation.model.formatters.LoginRequestFormatter
import com.cringeteam.todoproject.presentation.model.formatters.LoginResponseFormatter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class LoginViewModel : BaseViewModel() {

    private var loginRequestDisposable: Disposable? = null

    private val sendLoginRequestUseCase = SendLoginRequestUseCase()
    private val loginRequestFormatter = LoginRequestFormatter()
    private val loginResponseFormatter = LoginResponseFormatter()

    private val _screenState: BehaviorSubject<LoginScreenState> =
        BehaviorSubject.createDefault(LoginScreenState.Waiting)
    val screenState: BehaviorSubject<LoginScreenState> get() = _screenState

    fun onLoginClick(login: String, password: String) {

        val loginRequest = loginRequestFormatter.format(
            LoginRequestVo(
                login = login,
                password = password,
            )
        )

        loginRequestDisposable = sendLoginRequestUseCase.execute(loginRequest)
            .map { loginResponse ->
                loginResponseFormatter.format(loginResponse)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _screenState.onNext(LoginScreenState.Success)
                },
                { error ->
                    Logger.log("LoginViewModel::onLoginClick() error: $error")
                    _screenState.onNext(LoginScreenState.Waiting)
                }
            )
    }

    override fun onCleared() {
        loginRequestDisposable?.dispose()
        loginRequestDisposable = null
        super.onCleared()
    }
}