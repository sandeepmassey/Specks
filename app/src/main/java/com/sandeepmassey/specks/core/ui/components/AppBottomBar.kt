package com.sandeepmassey.specks.core.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sandeepmassey.specks.core.ui.theme.BOTTOM_BAR_ELEVATION
import com.sandeepmassey.specks.navigation.Screen

/**
 * Created by Sandeep Massey on 28-03-2022
 */
@Composable
fun AppBottomBar(
    navController: NavHostController,
    bottomNavItems: List<Screen> = listOf(
        Screen.Recipes,
        Screen.FavoriteRecipes,
        Screen.Profile
    ),
) {
    CompositionLocalProvider(LocalElevationOverlay provides null) {
        BottomAppBar(
            modifier = Modifier.fillMaxWidth(),
            cutoutShape = CircleShape,
            elevation = BOTTOM_BAR_ELEVATION
        ) {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxSize(),
                backgroundColor = MaterialTheme.colors.surface
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavItems.forEach { item ->
                    AppBottomBarItem(
                        screen = item,
                        label = item.title.toString(),
                        contentDescription = item.title,
                        selected = currentDestination?.hierarchy?.any {
                            it.route == item.route
                        } == true,
                        enabled = item.icon_outlined != null,
                        onClick = {
                            currentDestination?.route?.let {
                                navController.popBackStack(route = it, inclusive = true)
                            }
                            navController.navigate(route = item.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    }

}