package com.sandeepmassey.specks.recipes.dom.model

import kotlinx.serialization.Serializable

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Serializable
data class RecipesApiResponse(
    var success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val recipes: List<Recipe> = emptyList(),
    val lastUpdated: Long? = null
)
