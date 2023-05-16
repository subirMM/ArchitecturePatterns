package com.example.architecturepatterns.cleanmvvm.domain.datasource.local

import com.example.architecturepatterns.cleanmvvm.data.Failure
import com.example.architecturepatterns.cleanmvvm.data.Result
import com.example.architecturepatterns.cleanmvvm.data.Success
import com.example.architecturepatterns.cleanmvvm.data.db.helper.DbHelper
import com.example.architecturepatterns.cleanmvvm.data.model.NoteModel
import com.example.architecturepatterns.cleanmvvm.domain.blueprint.NotesDataSourceBlueprint
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalNotesDataSource @Inject constructor(private val dbHelper: DbHelper) :
    NotesDataSourceBlueprint {

    override suspend fun fetchNotes(): Result<List<NoteModel>> {
        return withContext(Dispatchers.IO) {
            try {
                Success(dbHelper.fetchNotes())
            } catch (error: Exception) {
                Failure("Failed to fetch notes")
            }
        }
    }

    override suspend fun saveNote(title: String, description: String): Result<Int> {
        return withContext(Dispatchers.IO) {
            try {
                Success(dbHelper.saveNote(title, description))
            } catch (error: Exception) {
                Failure("Failed to save note")
            }
        }
    }
}