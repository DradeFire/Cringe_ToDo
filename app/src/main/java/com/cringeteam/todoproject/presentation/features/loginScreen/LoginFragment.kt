package com.cringeteam.todoproject.presentation.features.loginScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.cringeteam.todoproject.R
import com.cringeteam.todoproject.common.logger.Logger
import com.cringeteam.todoproject.common.state.ScreenState
import com.cringeteam.todoproject.databinding.FragmentLoginBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding =
        FragmentLoginBinding::inflate

    override val viewModelClass: Class<LoginViewModel> = LoginViewModel::class.java

    override val screenName: String = SCREEN_NAME

    override fun initButtons() {
        super.initButtons()

        binding?.let { bindingNotNull ->
            with(bindingNotNull) {
                loginButton.setOnClickListener {
                    val login: String = loginEditText.text.toString()
                    val password: String = passwordEditText.text.toString()
                    viewModel?.onLoginClick(login, password)
                }

                registrationButton.setOnClickListener {
                    findNavController().navigate(R.id.action_navigate_loginScreen_to_registrationScreen)
                }
            }
        }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel?.let { viewModel ->
            compositeDisposable?.add(
                viewModel.screenState
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { state ->
                            when (state) {
                                ScreenState.Waiting -> {
                                    Logger.log("State is waiting")
                                    binding?.loginButton?.isEnabled = true
                                    binding?.progressBar?.isVisible = false
                                }
                                ScreenState.Loading -> {
                                    Logger.log("State is loading")
                                    binding?.loginButton?.isEnabled = false
                                    binding?.progressBar?.isVisible = true
                                }
                                ScreenState.Success -> {
                                    Logger.log("State is success")
                                    findNavController().navigate(R.id.action_navigate_loginScreen_to_NotesScreen)
                                }
                                ScreenState.Error -> TODO("Add Error state and show error toast")
                            }
                        },
                        { error ->
                            Logger.log("LoginFragment::initObservers(), screenState error: ${error.localizedMessage}")
                        }
                    )
            )
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
        private const val SCREEN_NAME = "login screen"
    }
}