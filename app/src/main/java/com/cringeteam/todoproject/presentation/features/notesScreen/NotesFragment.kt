package com.cringeteam.todoproject.presentation.features.notesScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
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

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        toolbar.isVisible = true

        val settingsButton = toolbar.findViewById<ImageButton>(R.id.open_settings)
        settingsButton.isVisible = false
    }

    companion object {
        private const val SCREEN_NAME = "notes fragment"
    }
}