package com.sandeepmassey.specks.recipes.dom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sandeepmassey.specks.recipes.dom.util.RecipesConstants.RECIPES_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Serializable
@Entity(tableName = RECIPES_TABLE)
class Recipe(
    @PrimaryKey(autoGenerate = false)
    @SerialName("_id")
    val id: String,
    @SerialName("Process")
    val process: String,
    @SerialName("Section")
    val section: String,
    @SerialName("Machine")
    val machine: String,
    @SerialName("Yarn count")
    val yarnCount: String,
    @SerialName("Mixing")
    val mixing: List<Mixing>,
    @SerialName("Parameters")
    val parameters: List<Map<String, String>>
) {
    fun toFavoriteRecipe(): FavoriteRecipe =
        FavoriteRecipe(
            id,
            process,
            section,
            machine,
            yarnCount,
            mixing,
            parameters
        )

}
