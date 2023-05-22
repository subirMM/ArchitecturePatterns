package com.example.architecturepatterns.cleanmvvm.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturepatterns.cleanmvvm.data.Failure
import com.example.architecturepatterns.cleanmvvm.data.Success
import com.example.architecturepatterns.cleanmvvm.domain.model.NoteModel
import com.example.architecturepatterns.cleanmvvm.domain.usecase.FetchNotesUseCase
import com.example.architecturepatterns.cleanmvvm.domain.usecase.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val saveNoteUseCase: SaveNoteUseCase
) : ViewModel() {

    private var _viewState: MutableLiveData<ViewState> =
        MutableLiveData(ViewState.Init(ViewState.StateHolder()))
    val viewState: LiveData<ViewState> = _viewState

    private val _currentStateHolder: ViewState.StateHolder =
        _viewState.value?.stateHolder ?: ViewState.StateHolder()

    fun fetchNotes() {
        viewModelScope.launch {
            when (val result = fetchNotesUseCase.invoke()) {
                is Success -> notesFetchSuccess(result.data)
                is Failure -> {
                    _viewState.value =
                        ViewState.ShowError(_currentStateHolder.copy(errorMessage = result.message))
                }
            }
        }
    }

    fun saveNote(title: String, description: String) {
        viewModelScope.launch {
            val saveNoteParam = SaveNoteUseCase.SaveNoteParams(title, description)
            when (val result = saveNoteUseCase.invoke(saveNoteParam)) {
                is Success -> noteSaveSuccess(title, description)
                is Failure -> {
                    _viewState.value =
                        ViewState.ShowError(_currentStateHolder.copy(errorMessage = result.message))
                }
            }
        }
    }

    private fun notesFetchSuccess(notesFetched: List<NoteModel>) {
        val savedNotes = _currentStateHolder.notes
        savedNotes.clear()
        savedNotes.addAll(notesFetched)
        _viewState.value =
            ViewState.NotesFetched(_currentStateHolder.copy(notes = savedNotes))
    }

    private fun noteSaveSuccess(title: String, description: String) {
        val savedNotes = _currentStateHolder.notes
        savedNotes.add(
            NoteModel(title, description)
        )
        _viewState.value =
            ViewState.NoteSaved(_currentStateHolder.copy(notes = savedNotes))
    }

    sealed class ViewState {
        abstract val stateHolder: StateHolder

        class Init(override val stateHolder: StateHolder) : ViewState()
        class NotesFetched(override val stateHolder: StateHolder) : ViewState()
        class NoteSaved(override val stateHolder: StateHolder) : ViewState()
        class ShowError(override val stateHolder: StateHolder) : ViewState()

        data class StateHolder(
            val notes: ArrayList<NoteModel> = arrayListOf(),
            val errorMessage: String = "Something went wrong"
        )
    }
}