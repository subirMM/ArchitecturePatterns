package com.example.architecturepatterns.cleanmvvm.di.module

import com.example.architecturepatterns.cleanmvvm.data.datasource.localdatasource.LocalNotesDataSource
import com.example.architecturepatterns.cleanmvvm.domain.blueprint.NotesDataSourceBlueprint
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