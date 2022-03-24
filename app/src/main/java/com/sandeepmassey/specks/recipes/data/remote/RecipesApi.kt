package com.sandeepmassey.specks.recipes.data.remote

import com.sandeepmassey.specks.recipes.dom.model.RecipesApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sandeep Massey on 21-03-2022
 */
interface RecipesApi {

    @GET("/recipes")
    suspend fun getAllRecipes(
        @Query("page") page: Int = 1
    ): RecipesApiResponse

    @GET("/recipes/search")
    suspend fun searchRecipes(
        @Query("name") name: String
    ): RecipesApiResponse
}