package com.example.architecturepatterns.cleanmvvm.domain.repository

import com.example.architecturepatterns.cleanmvvm.data.Result
import com.example.architecturepatterns.cleanmvvm.domain.model.NoteModel
import com.example.architecturepatterns.cleanmvvm.domain.blueprint.NotesDataSourceBlueprint
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDataSource: NotesDataSourceBlueprint) {

    suspend fun fetchNotes(): Result<List<NoteModel>> {
        return notesDataSource.fetchNotes()
    }

    suspend fun saveNote(title: String, description: String): Result<Int> {
        return notesDataSource.saveNote(title, description)
    }
}