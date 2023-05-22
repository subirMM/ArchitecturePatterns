package com.example.architecturepatterns.cleanmvvm.domain.usecase

import com.example.architecturepatterns.cleanmvvm.data.Result
import com.example.architecturepatterns.cleanmvvm.data.model.SearchResponse
import com.example.architecturepatterns.cleanmvvm.domain.repository.RecipeRepository
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(queryTerm: String): Result<SearchResponse> {
        return recipeRepository.searchRecipeFor(queryTerm)
    }
}