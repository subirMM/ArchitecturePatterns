package com.example.architecturepatterns.cleanmvvm.data.db.mapper

import com.example.architecturepatterns.cleanmvvm.data.db.entity.NotesEntity
import com.example.architecturepatterns.cleanmvvm.domain.model.NoteModel

fun NotesEntity.toNoteModel(): NoteModel {
    return NoteModel(
        title = this.title,
        description = this.description
    )
}

fun NoteModel.toNotesEntity(): NotesEntity {
    return NotesEntity(
        title = this.title,
        description = this.description
    )
}