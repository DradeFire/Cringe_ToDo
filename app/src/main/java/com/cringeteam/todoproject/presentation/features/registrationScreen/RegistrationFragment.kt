package com.cringeteam.todoproject.presentation.features.registrationScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
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

    override fun initUI() {
        super.initUI()
        val drawerLayout = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

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
                            ScreenState.Waiting -> {
                                binding?.progressBar?.isVisible = false
                                binding?.registrationButton?.isEnabled = true
                                Logger.log("State is waiting")
                            }
                            ScreenState.Loading -> {
                                binding?.progressBar?.isVisible = true
                                binding?.registrationButton?.isEnabled = false
                                Toast.makeText(context, "Идет загрузка...", Toast.LENGTH_SHORT).show()
                                Logger.log("State is loading")
                            }
                            ScreenState.Success -> {
                                Toast.makeText(context, "Аккаунт зарегистрирован", Toast.LENGTH_SHORT).show()
                                Logger.log("State is success")
                                findNavController().navigate(R.id.action_navigate_registrationScreen_to_loginScreen)
                            }
                            ScreenState.Error -> {
                                Toast.makeText(context, "Ошибка...", Toast.LENGTH_SHORT).show()
                                Logger.log("State is error")
                            }
                        }
                    }
            )
        }
    }

    companion object {
        private const val SCREEN_NAME = "Registration screen"
    }
}