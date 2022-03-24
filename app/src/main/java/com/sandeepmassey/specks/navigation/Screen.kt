package com.sandeepmassey.specks.navigation

/**
 * Created by Sandeep Massey on 18-03-2022
 */
sealed class Screen(
    val route: String
) {
    object Login : Screen(route = "login_screen")
    object Profile : Screen(route = "profile_screen")
    object Recipes : Screen(route = "recipes_screen")
    object Recipe : Screen("recipe_screen/{recipeId}") {
        fun passRecipeId(recipeId: String): String {
            return "recipe_screen/$recipeId"
        }
    }
    object Search : Screen(route = "search_screen")
}