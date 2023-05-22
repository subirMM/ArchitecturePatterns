package com.example.architecturepatterns.cleanmvvm.domain.blueprint

import com.example.architecturepatterns.cleanmvvm.data.Result
import com.example.architecturepatterns.cleanmvvm.domain.model.NoteModel

interface NotesDataSourceBlueprint {
    suspend fun fetchNotes(): Result<List<NoteModel>>
    suspend fun saveNote(title: String, description: String): Result<Int>
}