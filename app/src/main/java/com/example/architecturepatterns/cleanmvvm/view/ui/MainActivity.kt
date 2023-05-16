package com.example.architecturepatterns.cleanmvvm.view.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.architecturepatterns.cleanmvvm.data.model.NoteModel
import com.example.architecturepatterns.cleanmvvm.view.adapter.NotesAdapter
import com.example.architecturepatterns.cleanmvvm.view.viewmodel.MainViewModel
import com.example.architecturepatterns.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val notesAdapter: NotesAdapter by lazy { NotesAdapter() }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObservers()
        initListeners()
    }

    private fun initView() {
        binding.rvNotes.adapter = notesAdapter
    }

    private fun initObservers() {
        mainViewModel.viewState.observe(this) { state ->
            when (state) {
                is MainViewModel.ViewState.Init -> initData()
                is MainViewModel.ViewState.ShowError -> showError(state.stateHolder.errorMessage)
                is MainViewModel.ViewState.NotesFetched -> updateNotesUi(state.stateHolder.notes)
                is MainViewModel.ViewState.NoteSaved -> updateNotesUi(state.stateHolder.notes)
            }
        }
    }

    private fun initListeners() {
        binding.buttonSaveNote.setOnClickListener {
            saveNote()
        }
    }

    private fun initData() {
        mainViewModel.fetchNotes()
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
            mainViewModel.saveNote(edtTitle.text.toString(), edtDescription.text.toString())
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}