package com.sandeepmassey.specks.recipes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sandeepmassey.specks.recipes.dom.model.RecipeRemoteKeys

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Dao
interface RecipeRemoteKeysDao {

    @Query("SELECT * FROM recipes_remote_keys_table WHERE id LIKE :id")
    suspend fun getRemoteKeys(id: String): RecipeRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(recipeRemoteKeys: List<RecipeRemoteKeys>)

    @Query("DELETE FROM recipes_remote_keys_table")
    suspend fun removeAllRemoteKeys()
}