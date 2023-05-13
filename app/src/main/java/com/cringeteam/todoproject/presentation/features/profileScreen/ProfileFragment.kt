package com.cringeteam.todoproject.presentation.features.profileScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.cringeteam.todoproject.R
import com.cringeteam.todoproject.common.logger.Logger
import com.cringeteam.todoproject.databinding.FragmentProfileBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment
import com.cringeteam.todoproject.presentation.features.profileScreen.recyclerView.ProfileAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding =
        FragmentProfileBinding::inflate
    override val viewModelClass: Class<ProfileViewModel> = ProfileViewModel::class.java
    override val screenName: String = SCREEN_NAME

    private val profileAdapter = ProfileAdapter()

    override fun initUI() {
        super.initUI()

        val openSettingsButton = requireActivity().findViewById<ImageButton>(R.id.open_settings)
        openSettingsButton.isVisible = true
    }

    override fun initRecycler() {
        super.initRecycler()

        binding?.let { binding ->
            binding.groupsList.adapter = profileAdapter
        }
    }

    override fun initButtons() {
        super.initButtons()

        val openSettings = requireActivity().findViewById<ImageButton>(R.id.open_settings)
        openSettings.setOnClickListener {
            showSettingPopupMenu(openSettings)
        }
    }

    private fun showSettingPopupMenu(anchorView: View) {
        val popupMenu = PopupMenu(requireContext(), anchorView)
        popupMenu.inflate(R.menu.profile_settings_menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.change_password -> {
                    findNavController().navigate(R.id.change_password)
                    true
                }

                R.id.change_photo -> {
                    // TODO: Add this later
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    override fun initObservers() {
        super.initObservers()

        viewModel?.let { viewModel ->
            compositeDisposable?.addAll(
                // TODO: add avatar downloading
                Single.zip(
                    viewModel.getUser(),
                    viewModel.getProjects()
                ) { value1, value2 ->
                    Pair(value1, value2)
                }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        binding?.progressBar?.isVisible = true
                    }
                    .doFinally {
                        binding?.progressBar?.isVisible = false
                    }
                    .subscribe(
                        { pair ->
                            val user = pair.first
                            val groups = pair.second

                            binding?.login?.text = user.login
                            profileAdapter.setData(groups)
                        },
                        { error ->
                            Logger.log("ProfileFragment::initObservers(), getProjects() error: ${error.localizedMessage}")
                        }
                    )
            )
        }
    }

    companion object {
        private const val SCREEN_NAME = "profile fragment"
    }
}