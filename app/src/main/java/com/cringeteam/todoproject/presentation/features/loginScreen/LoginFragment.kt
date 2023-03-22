package com.cringeteam.todoproject.presentation.features.loginScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cringeteam.todoproject.databinding.FragmentLoginBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding =
        FragmentLoginBinding::inflate

    override val viewModelClass: Class<LoginViewModel> = LoginViewModel::class.java

    override val screenName: String = SCREEN_NAME

    override fun initButtons() {
        super.initButtons()

        binding?.loginButton?.setOnClickListener {
            val login: String = binding.loginEditText.text.toString()
            val password: String = binding.passwordEditText.text.toString()

            viewModel?.onLoginClick(login, password)?.subscribe()
        }

    }

    companion object {
        fun newInstance() = LoginFragment()
        private const val SCREEN_NAME = "login screen"
    }
}