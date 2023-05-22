package com.example.architecturepatterns.cleanmvvm.domain.blueprint

import com.example.architecturepatterns.cleanmvvm.data.Result
import com.example.architecturepatterns.cleanmvvm.data.model.SearchResponse

interface RecipeDataSourceBlueprint {
    suspend fun searchRecipeFor(queryTerm: String): Result<SearchResponse>
    suspend fun fetchRecipeById(recipeId: String): Result<SearchResponse>
}