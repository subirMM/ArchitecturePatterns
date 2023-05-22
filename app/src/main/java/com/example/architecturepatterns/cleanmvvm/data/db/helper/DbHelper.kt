package com.example.architecturepatterns.cleanmvvm.data.db.helper

import com.example.architecturepatterns.cleanmvvm.data.db.database.NotesDatabase
import com.example.architecturepatterns.cleanmvvm.data.db.mapper.toNoteModel
import com.example.architecturepatterns.cleanmvvm.data.db.mapper.toNotesEntity
import com.example.architecturepatterns.cleanmvvm.domain.model.NoteModel
import javax.inject.Inject

class DbHelper @Inject constructor(private val db: NotesDatabase) {

    fun fetchNotes(): List<NoteModel> {
        val notes = db.notesDao().fetchAllNotes()
        return notes.map { it.toNoteModel() }
    }

    fun saveNote(title: String, description: String): Int {
        val note = NoteModel(title, description)
        return db.notesDao().insertNote(note.toNotesEntity()).toInt()
    }
}