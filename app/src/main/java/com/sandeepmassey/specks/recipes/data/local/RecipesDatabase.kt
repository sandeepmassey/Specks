package com.sandeepmassey.specks.recipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sandeepmassey.specks.recipes.data.local.dao.RecipeDao
import com.sandeepmassey.specks.recipes.data.local.dao.RecipeRemoteKeysDao
import com.sandeepmassey.specks.recipes.dom.model.FavoriteRecipe
import com.sandeepmassey.specks.recipes.dom.model.Recipe
import com.sandeepmassey.specks.recipes.dom.model.RecipeRemoteKeys

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Database(
    entities = [
        Recipe::class,
        RecipeRemoteKeys::class,
        FavoriteRecipe::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun recipeRemoteKeysDao(): RecipeRemoteKeysDao
}