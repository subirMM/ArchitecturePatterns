package com.example.architecturepatterns.cleanmvvm.domain.usecase

import com.example.architecturepatterns.cleanmvvm.data.Result
import com.example.architecturepatterns.cleanmvvm.domain.repository.NotesRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(params: SaveNoteParams): Result<Int> {
        return notesRepository.saveNote(params.title, params.description)
    }

    data class SaveNoteParams(
        val title: String,
        val description: String
    )
}