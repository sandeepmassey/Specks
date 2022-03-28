package com.sandeepmassey.specks.recipes.dom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sandeepmassey.specks.recipes.dom.util.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Serializable
@Entity(tableName = Constants.RECIPES_TABLE)
class Recipe(
    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
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
)
