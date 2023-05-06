package com.cringeteam.todoproject.presentation.features.notesScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import com.cringeteam.todoproject.R
import com.cringeteam.todoproject.databinding.FragmentNotesBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment

class NotesFragment : BaseFragment<FragmentNotesBinding, NotesViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNotesBinding =
        FragmentNotesBinding::inflate

    override val viewModelClass: Class<NotesViewModel> = NotesViewModel::class.java

    override val screenName: String = SCREEN_NAME

    override fun initUI() {
        super.initUI()
        val drawerLayout = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    companion object {
        private const val SCREEN_NAME = "notes fragment"
    }
}