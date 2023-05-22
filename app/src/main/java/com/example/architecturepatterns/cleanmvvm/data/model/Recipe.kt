package com.example.architecturepatterns.cleanmvvm.data.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("idMeal")
    val recipeId: String,
    @SerializedName("strMeal")
    val recipeName: String?,
    @SerializedName("strMealThumb")
    val recipeImage: String?,
)
