package com.example.architecturepatterns.cleanmvvm.domain.usecase

import com.example.architecturepatterns.cleanmvvm.data.Result
import com.example.architecturepatterns.cleanmvvm.data.model.NoteModel
import com.example.architecturepatterns.cleanmvvm.domain.repository.NotesRepository
import javax.inject.Inject

class FetchNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(): Result<List<NoteModel>> {
        return notesRepository.fetchNotes()
    }
}