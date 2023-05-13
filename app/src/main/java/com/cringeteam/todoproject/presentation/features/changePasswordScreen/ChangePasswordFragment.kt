package com.cringeteam.todoproject.presentation.features.changePasswordScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cringeteam.todoproject.databinding.FragmentChangePasswordBinding
import com.cringeteam.todoproject.databinding.FragmentLoginBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment
import com.cringeteam.todoproject.presentation.features.loginScreen.LoginFragment
import com.cringeteam.todoproject.presentation.features.loginScreen.LoginViewModel

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding, ChangePasswordViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentChangePasswordBinding =
        FragmentChangePasswordBinding::inflate

    override val viewModelClass: Class<ChangePasswordViewModel> = ChangePasswordViewModel::class.java

    override val screenName: String = SCREEN_NAME

    // TODO: realize "change password"

    companion object {
        private const val SCREEN_NAME = "change password screen"
    }

}