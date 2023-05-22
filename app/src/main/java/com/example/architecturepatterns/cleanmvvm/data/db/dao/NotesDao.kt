package com.example.architecturepatterns.cleanmvvm.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.architecturepatterns.cleanmvvm.data.db.entity.NotesEntity

@Dao
interface NotesDao {
    @Query("SELECT * FROM notesentity")
    fun fetchAllNotes(): List<NotesEntity>

    @Insert
    fun insertNote(note: NotesEntity) : Long
}