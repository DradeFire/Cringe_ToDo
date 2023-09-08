package com.cringeteam.todoproject.presentation.features.tasksScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.cringeteam.todoproject.R
import com.cringeteam.todoproject.common.logger.Logger
import com.cringeteam.todoproject.common.state.ScreenState
import com.cringeteam.todoproject.databinding.FragmentTasksBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment
import com.cringeteam.todoproject.presentation.features.tasksScreen.recyclerView.TasksAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class TasksFragment : BaseFragment<FragmentTasksBinding, TasksViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTasksBinding =
        FragmentTasksBinding::inflate
    override val viewModelClass: Class<TasksViewModel> = TasksViewModel::class.java
    override val screenName: String = SCREEN_NAME

    private val tasksAdapter = TasksAdapter { id -> navigateToTaskScreen(id) }

    override fun onStart() {
        super.onStart()

        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)

        val settingsButton = toolbar.findViewById<ImageButton>(R.id.open_settings)
        settingsButton.isVisible = false
    }

    override fun initUI() {
        super.initUI()

        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible = true

        val drawerLayout = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun initRecycler() {
        super.initRecycler()
        binding?.tasks?.adapter = tasksAdapter
    }

    override fun initObservers() {
        super.initObservers()
        viewModel?.let { viewModel ->
            compositeDisposable?.addAll(
                viewModel.screenState
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { state ->
                            when (state) {
                                ScreenState.Waiting -> {
                                    Logger.log("State is waiting")
                                    binding?.progressBar?.isVisible = false
                                }

                                ScreenState.Loading -> {
                                    Logger.log("State is loading")
                                    binding?.progressBar?.isVisible = true
                                }

                                ScreenState.Success -> {
                                    Logger.log("State is success")
                                    binding?.progressBar?.isVisible = false
                                }

                                ScreenState.Error -> TODO("Add Error state and show error toast")

                                null -> TODO("Add if we get null")
                            }
                        },
                        { error ->
                            Logger.log("LoginFragment::initObservers(), screenState error: ${error.localizedMessage}")
                        }
                    ),
                viewModel.getUngroupedTasks()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        viewModel.screenState.onNext(ScreenState.Loading)
                    }
                    .subscribe(
                        { tasks ->
                            tasksAdapter.setData(tasks)
                            viewModel.screenState.onNext(ScreenState.Success)
                        },
                        { error ->
                            Logger.log("MainActivity::initObservers(), getProjects() error: ${error.localizedMessage}")
                        }
                    )
            )
        }
    }

    private fun navigateToTaskScreen(id: Long) {
        val action = TasksFragmentDirections.navigateTasksScreenToTaskScreen(id)
        findNavController().navigate(action)
    }

    companion object {
        private const val SCREEN_NAME = "notes fragment"
    }
}