package com.cringeteam.todoproject.presentation.features.notesScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cringeteam.todoproject.databinding.FragmentNotesBinding
import com.cringeteam.todoproject.presentation.base.BaseFragment

class NotesFragment : BaseFragment<FragmentNotesBinding, NotesViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNotesBinding =
        FragmentNotesBinding::inflate

    override val viewModelClass: Class<NotesViewModel> = NotesViewModel::class.java

    override val screenName: String = SCREEN_NAME

    companion object {
        fun newInstance() = NotesFragment()
        private const val SCREEN_NAME = "notes fragment"
    }
}