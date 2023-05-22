package com.example.architecturepatterns.cleanmvvm.domain.repository

import com.example.architecturepatterns.cleanmvvm.data.Result
import com.example.architecturepatterns.cleanmvvm.data.model.SearchResponse
import com.example.architecturepatterns.cleanmvvm.domain.blueprint.RecipeDataSourceBlueprint
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeDataSource: RecipeDataSourceBlueprint) {

    suspend fun searchRecipeFor(queryTerm: String): Result<SearchResponse> {
        return recipeDataSource.searchRecipeFor(queryTerm)
    }

    suspend fun fetchRecipeById(recipeId: String): Result<SearchResponse> {
        return recipeDataSource.fetchRecipeById(recipeId)
    }
}