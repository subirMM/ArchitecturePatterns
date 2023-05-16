package com.example.architecturepatterns.cleanmvvm.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.architecturepatterns.cleanmvvm.data.db.dao.NotesDao
import com.example.architecturepatterns.cleanmvvm.data.db.entity.NotesEntity

@Database(entities = [NotesEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}