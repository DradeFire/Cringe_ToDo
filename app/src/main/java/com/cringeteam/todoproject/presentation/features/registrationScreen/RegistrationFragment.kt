package com.cringeteam.todoproject.presentation.features.registrationScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cringeteam.todoproject.R
import com.cringeteam.todoproject.common.logger.Logger
import com.cringeteam.todoproject.common.state.ScreenState
import com.cringeteam.todoproject.databinding.FragmentRegistrationBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding, RegistrationViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegistrationBinding =
        FragmentRegistrationBinding::inflate

    override val viewModelClass: Class<RegistrationViewModel> = RegistrationViewModel::class.java

    override val screenName: String = SCREEN_NAME

    override fun initButtons() {
        super.initButtons()

        binding?.let { binding ->
            with(binding) {
                registrationButton.setOnClickListener {
                    val login = loginEditText.text.toString()
                    val password = passwordEditText.text.toString()
                    val passwordConfirm = passwordConfirmEditText.text.toString()

                    viewModel?.onRegistrationClick(login, password, passwordConfirm)
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
                    .subscribe { screenState ->
                        when (screenState) {
                            // TODO: add progressBar animation in registration-ui branch
                            ScreenState.Waiting -> {
                                Logger.log("State is waiting")
                            }
                            ScreenState.Loading -> {
                                Logger.log("State is loading")
                            }
                            ScreenState.Success -> {
                                Logger.log("State is success")
                                findNavController().navigate(R.id.action_navigate_registrationScreen_to_loginScreen)
                            }
                        }
                    }
            )
        }
    }

    companion object {
        fun newInstance() = RegistrationFragment()
        private const val SCREEN_NAME = "Registration screen"
    }
}