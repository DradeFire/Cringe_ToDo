package com.cringeteam.todoproject.presentation.features.registrationScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cringeteam.todoproject.databinding.FragmentRegistrationBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding, RegistrationViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegistrationBinding =
        FragmentRegistrationBinding::inflate

    override val viewModelClass: Class<RegistrationViewModel> = RegistrationViewModel::class.java

    override val screenName: String = SCREEN_NAME

    override fun initButtons() {
        super.initButtons()

        binding?.let { binding ->
            with(binding) {
                val login = loginEditText.text.toString()
                val password = passwordEditText.text.toString()
                val passwordConfirm = passwordConfirmEditText.text.toString()

                viewModel?.onSignUpClick(login, password, passwordConfirm)
            }

        }
    }

    companion object {
        fun newInstance() = RegistrationFragment()
        private const val SCREEN_NAME = "Registration screen"
    }
}