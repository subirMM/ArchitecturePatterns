package com.example.architecturepatterns.cleanmvvm.view.ui

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.architecturepatterns.cleanmvvm.domain.model.NoteModel
import com.example.architecturepatterns.cleanmvvm.view.adapter.NotesAdapter
import com.example.architecturepatterns.cleanmvvm.view.viewmodel.NotesViewModel
import com.example.architecturepatterns.databinding.ActivityNotesBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesActivity : ComponentActivity() {

    private val viewModel: NotesViewModel by viewModels()
    private val notesAdapter: NotesAdapter by lazy { NotesAdapter() }
    private lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObservers()
        initListeners()
    }

    private fun initView() {
        binding.rvNotes.adapter = notesAdapter
    }

    private fun initObservers() {
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is NotesViewModel.ViewState.Init -> initData()
                is NotesViewModel.ViewState.ShowError -> showError(state.stateHolder.errorMessage)
                is NotesViewModel.ViewState.NotesFetched -> updateNotesUi(state.stateHolder.notes)
                is NotesViewModel.ViewState.NoteSaved -> updateNotesUi(state.stateHolder.notes)
            }
        }
    }

    private fun initListeners() {
        binding.buttonSaveNote.setOnClickListener {
            saveNote()
        }
    }

    private fun initData() {
        viewModel.fetchNotes()
    }

    private fun updateNotesUi(notes: List<NoteModel>) {
        notesAdapter.setNotes(notes)
        with(binding) {
            tvEmptyNotes.isVisible = notes.isEmpty()
            clearEditText(edtTitle)
            clearEditText(edtDescription)
        }
    }

    private fun clearEditText(editText: EditText) {
        editText.apply {
            text?.clear()
            clearFocus()
        }
    }

    private fun saveNote() {
        with(binding) {
            viewModel.saveNote(edtTitle.text.toString(), edtDescription.text.toString())
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}