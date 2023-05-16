package com.example.architecturepatterns.cleanmvvm.di.module

import com.example.architecturepatterns.cleanmvvm.domain.blueprint.NotesDataSourceBlueprint
import com.example.architecturepatterns.cleanmvvm.domain.datasource.local.LocalNotesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsNotesDataSource(localNotesDataSource: LocalNotesDataSource): NotesDataSourceBlueprint
}