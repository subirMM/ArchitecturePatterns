package com.example.architecturepatterns.cleanmvvm.data.datasource.remotedatasource

import com.example.architecturepatterns.cleanmvvm.data.Failure
import com.example.architecturepatterns.cleanmvvm.data.Result
import com.example.architecturepatterns.cleanmvvm.data.Success
import com.example.architecturepatterns.cleanmvvm.data.model.SearchResponse
import com.example.architecturepatterns.cleanmvvm.data.remote.RecipeService
import com.example.architecturepatterns.cleanmvvm.domain.blueprint.RecipeDataSourceBlueprint
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRecipeDataSource @Inject constructor(private val remoteService: RecipeService) :
    RecipeDataSourceBlueprint {

    override suspend fun searchRecipeFor(queryTerm: String): Result<SearchResponse> {
        return withContext(Dispatchers.IO) {
            val response = remoteService.searchRecipeFor(queryTerm)
            when {
                response.isSuccessful -> Success(response.body() ?: SearchResponse(emptyList()))
                else -> Failure(response.message())
            }
        }
    }

    override suspend fun fetchRecipeById(recipeId: String): Result<SearchResponse> {
        return withContext(Dispatchers.IO) {
            val response = remoteService.getRecipeBy(recipeId)
            when {
                response.isSuccessful -> Success(response.body() ?: SearchResponse(emptyList()))
                else -> Failure(response.message())
            }
        }
    }
}