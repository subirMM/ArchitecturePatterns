package com.example.architecturepatterns.cleanmvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.architecturepatterns.cleanmvvm.common.loadImage
import com.example.architecturepatterns.cleanmvvm.data.model.Recipe
import com.example.architecturepatterns.databinding.ItemRecipeBinding

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    private val recipes: ArrayList<Recipe> = arrayListOf()
    private lateinit var context: Context

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        with(holder.binding) {
            tvTitle.text = recipes[position].recipeName
            recipes[position].recipeImage?.let { ivBg.loadImage(it) }
        }
    }

    override fun getItemCount(): Int = recipes.size

    fun setRecipes(notes: List<Recipe>) {
        this.recipes.clear()
        this.recipes.addAll(notes)
        notifyDataSetChanged()
    }

    class RecipeViewHolder(val binding: ItemRecipeBinding) : ViewHolder(binding.root)
}