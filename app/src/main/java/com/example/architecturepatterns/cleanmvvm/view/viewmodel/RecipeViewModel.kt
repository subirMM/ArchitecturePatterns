package com.example.architecturepatterns.cleanmvvm.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturepatterns.cleanmvvm.data.Failure
import com.example.architecturepatterns.cleanmvvm.data.Success
import com.example.architecturepatterns.cleanmvvm.data.model.Recipe
import com.example.architecturepatterns.cleanmvvm.data.model.SearchResponse
import com.example.architecturepatterns.cleanmvvm.domain.usecase.FetchRecipeUseCase
import com.example.architecturepatterns.cleanmvvm.domain.usecase.SearchRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase,
    private val fetchRecipeUseCase: FetchRecipeUseCase
) : ViewModel() {

    private var _viewState: MutableLiveData<ViewState> =
        MutableLiveData(ViewState.Init(ViewState.StateHolder()))
    val viewState: LiveData<ViewState> = _viewState

    private val _currentStateHolder: ViewState.StateHolder =
        _viewState.value?.stateHolder ?: ViewState.StateHolder()

    fun searchRecipeBy(queryTerm: String) {
        viewModelScope.launch {
            _viewState.value = ViewState.ShowLoader(_currentStateHolder.copy(showLoadingState = true))
            when (val result = searchRecipeUseCase.invoke(queryTerm)) {
                is Success -> recipeFetchSuccess(result.data)
                is Failure -> {
                    _viewState.value =
                        ViewState.ShowError(_currentStateHolder.copy(errorMessage = result.message))
                }
            }
            _viewState.value = ViewState.ShowLoader(_currentStateHolder.copy(showLoadingState = false))
        }
    }

    fun fetchRecipeBy(recipeId: String) {
        viewModelScope.launch {
            _viewState.value = ViewState.ShowLoader(_currentStateHolder.copy(showLoadingState = true))
            when (val result = fetchRecipeUseCase.invoke(recipeId)) {
                is Success -> recipeFetchSuccess(result.data)
                is Failure -> {
                    _viewState.value =
                        ViewState.ShowError(_currentStateHolder.copy(errorMessage = result.message))
                }
            }
            _viewState.value = ViewState.ShowLoader(_currentStateHolder.copy(showLoadingState = false))
        }
    }

    private fun recipeFetchSuccess(searchResponse: SearchResponse) {
        val recipeList = _currentStateHolder.recipes
        recipeList.clear()
        recipeList.addAll(searchResponse.recipeList)
        _viewState.value =
            ViewState.RecipesFetched(_currentStateHolder.copy(recipes = recipeList))
    }

    sealed class ViewState {
        abstract val stateHolder: StateHolder

        class Init(override val stateHolder: StateHolder) : ViewState()
        class RecipesFetched(override val stateHolder: StateHolder) : ViewState()
        class ShowError(override val stateHolder: StateHolder) : ViewState()
        class ShowLoader(override val stateHolder: StateHolder) : ViewState()

        data class StateHolder(
            val recipes: ArrayList<Recipe> = arrayListOf(),
            val errorMessage: String = "Something went wrong",
            val showLoadingState: Boolean = false
        )
    }
}