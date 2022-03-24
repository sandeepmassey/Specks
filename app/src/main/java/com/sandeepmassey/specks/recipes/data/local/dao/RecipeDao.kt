package com.sandeepmassey.specks.recipes.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sandeepmassey.specks.recipes.dom.model.Recipe

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun getAllRecipes(): PagingSource<Int, Recipe>

    @Query("SELECT * FROM recipes_table WHERE id LIKE :recipeId")
    fun getSelectedRecipe(recipeId: String): Recipe

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipes(recipes: List<Recipe>)

    @Query("DELETE FROM recipes_table")
    suspend fun removeAllRecipes()
}