package com.sandeepmassey.specks.recipes.dom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sandeepmassey.specks.recipes.dom.util.RecipesConstants.RECIPES_REMOTE_KEYS_TABLE

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Entity(tableName = RECIPES_REMOTE_KEYS_TABLE)
data class RecipeRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
