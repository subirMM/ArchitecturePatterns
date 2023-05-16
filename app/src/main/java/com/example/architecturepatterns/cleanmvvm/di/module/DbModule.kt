package com.example.architecturepatterns.cleanmvvm.di.module

import android.content.Context
import androidx.room.Room
import com.example.architecturepatterns.cleanmvvm.data.db.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room
            .databaseBuilder(context, NotesDatabase::class.java, "notes-db")
            .build()
    }
}