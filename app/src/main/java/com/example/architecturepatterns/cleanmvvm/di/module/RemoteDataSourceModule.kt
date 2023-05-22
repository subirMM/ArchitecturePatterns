package com.example.architecturepatterns.cleanmvvm.di.module

import com.example.architecturepatterns.cleanmvvm.data.datasource.remotedatasource.RemoteRecipeDataSource
import com.example.architecturepatterns.cleanmvvm.domain.blueprint.RecipeDataSourceBlueprint
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindsRecipeDataSource(remoteRecipeDataSource: RemoteRecipeDataSource): RecipeDataSourceBlueprint
}