package com.example.architecturepatterns.cleanmvvm.domain.blueprint

import com.example.architecturepatterns.cleanmvvm.data.model.NoteModel
import com.example.architecturepatterns.cleanmvvm.data.Result

interface NotesDataSourceBlueprint {
    suspend fun fetchNotes(): Result<List<NoteModel>>
    suspend fun saveNote(title: String, description: String): Result<Int>
}