package com.cringeteam.todoproject.presentation.features.registrationScreen

import com.cringeteam.todoproject.common.logger.Logger
import com.cringeteam.todoproject.common.state.ScreenState
import com.cringeteam.todoproject.domain.usecases.RegistrationUseCase
import com.cringeteam.todoproject.presentation.base.BaseViewModel
import com.cringeteam.todoproject.presentation.model.formatters.StatusMessageFormatter
import com.cringeteam.todoproject.presentation.model.registration.RegistrationUserVo
import com.cringeteam.todoproject.presentation.model.registration.formatter.RegistrationUserFormatter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class RegistrationViewModel : BaseViewModel() {

    private var registrationDisposable: Disposable? = null

    private val registrationUseCase = RegistrationUseCase()
    private val registrationRequestFormatter = RegistrationUserFormatter()

    private val statusMessageFormatter = StatusMessageFormatter()

    private val _screenState: BehaviorSubject<ScreenState> =
        BehaviorSubject.createDefault(ScreenState.Waiting)
    val screenState: BehaviorSubject<ScreenState> get() = _screenState

    fun onRegistrationClick(
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

        registrationDisposable = registrationUseCase.execute(request)
            .map { statusMessage ->
                statusMessageFormatter.formatter(statusMessage)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _screenState.onNext(ScreenState.Loading)
            }
            .subscribe(
                { statusMessageVo ->
                    if (statusMessageVo.codeStatus == CODE_STATUS_CREATED) {
                        _screenState.onNext(ScreenState.Success)
                    }
                    _screenState.onNext(ScreenState.Waiting)
                    Logger.log("Registration screen::onRegistrationClick() - ${statusMessageVo.message}")
                },
                { throwable ->
                    _screenState.onNext(ScreenState.Waiting)
                    Logger.log("Registration screen::onRegistrationClick() - Error: ${throwable.localizedMessage}")
                }
            )
    }

    override fun onCleared() {
        registrationDisposable?.dispose()
        registrationDisposable = null
        super.onCleared()
    }

    companion object {
        private const val CODE_STATUS_CREATED = 201
    }
}