package com.cringeteam.todoproject.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.cringeteam.todoproject.R
import com.cringeteam.todoproject.common.logger.Logger
import com.cringeteam.todoproject.databinding.ActivityMainBinding
import com.cringeteam.todoproject.presentation.activity.driwerRecyclerView.DrawerAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val drawerAdapter = DrawerAdapter()

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
        initButtons()
        initRecycler()
    }


    private fun initObservers() {
        compositeDisposable.add(
            viewModel.getProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { groups ->
                        drawerAdapter.setData(groups)
                    },
                    { error ->
                        Logger.log("MainActivity::initObservers(), getProjects() error: ${error.localizedMessage}")
                    }
                )
        )
    }

    private fun initButtons() {
        with(binding.drawer) {
            logo.setOnClickListener {
                // TODO: do navigate to profile
            }
            closeDrawer.setOnClickListener {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            addGroup.setOnClickListener {
                // TODO: create dialog to create a group
            }
            logout.setOnClickListener {
                // TODO: logout + navigate to loginScreen
            }
            deleteAccount.setOnClickListener {
                // TODO: create dialog "Are you sure"
            }
        }
    }

    private fun initRecycler() {
        val drawerGroupList = binding.drawer.groupsList
        drawerGroupList.adapter = drawerAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
