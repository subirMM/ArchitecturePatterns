package com.example.architecturepatterns.cleanmvvm.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("meals")
    val recipeList: List<Recipe>
)