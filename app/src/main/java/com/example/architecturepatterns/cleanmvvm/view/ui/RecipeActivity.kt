package com.example.architecturepatterns.cleanmvvm.view.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.architecturepatterns.cleanmvvm.data.model.Recipe
import com.example.architecturepatterns.cleanmvvm.view.adapter.RecipeAdapter
import com.example.architecturepatterns.cleanmvvm.view.viewmodel.RecipeViewModel
import com.example.architecturepatterns.databinding.ActivityRecipeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeActivity : ComponentActivity() {

    private val viewModel: RecipeViewModel by viewModels()
    private val recipeAdapter: RecipeAdapter by lazy { RecipeAdapter() }
    private lateinit var binding: ActivityRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObservers()
        initListeners()
    }

    private fun initView() {
        binding.rvRecipe.adapter = recipeAdapter
    }

    private fun initObservers() {
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is RecipeViewModel.ViewState.Init -> initData()
                is RecipeViewModel.ViewState.ShowError -> showError(state.stateHolder.errorMessage)
                is RecipeViewModel.ViewState.ShowLoader -> showLoader(state.stateHolder.showLoadingState)
                is RecipeViewModel.ViewState.RecipesFetched -> updateUi(state.stateHolder.recipes)
            }
        }
    }

    private fun initListeners() {
    }

    private fun initData() {
        viewModel.searchRecipeBy("chicken")
    }

    private fun showLoader(showLoadingState: Boolean) {
        binding.progressCircular.isVisible = showLoadingState
    }

    private fun updateUi(notes: List<Recipe>) {
        recipeAdapter.setRecipes(notes)
        with(binding) {
            tvEmptyRecipe.isVisible = notes.isEmpty()
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}