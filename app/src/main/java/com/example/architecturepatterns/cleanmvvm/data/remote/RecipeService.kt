package com.example.architecturepatterns.cleanmvvm.data.remote

import com.example.architecturepatterns.cleanmvvm.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("api/json/v1/1/search.php")
    suspend fun searchRecipeFor(@Query("s") searchTerm: String) : Response<SearchResponse>

    @GET("api/json/v1/1/lookup.php")
    suspend fun getRecipeBy(@Query("i") recipeId: String) : Response<SearchResponse>
}