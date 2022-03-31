package com.sandeepmassey.specks.core.ui

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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sandeepmassey.specks.core.ui.components.AppBottomBarItem
import com.sandeepmassey.specks.core.ui.theme.BOTTOM_BAR_ELEVATION
import com.sandeepmassey.specks.navigation.Screen

/**
 * Created by Sandeep Massey on 28-03-2022
 */
@Composable
fun AppBottomBar(
    navController: NavController,
    bottomNavItems: List<Screen> = listOf(
        Screen.Recipes,
        Screen.Profile
    )
) {
    CompositionLocalProvider(LocalElevationOverlay provides null) {
        BottomAppBar(
            modifier = Modifier.fillMaxWidth(),
            cutoutShape = CircleShape,
            elevation = BOTTOM_BAR_ELEVATION
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            BottomNavigation(
                modifier = Modifier
                    .fillMaxSize(),
                backgroundColor = MaterialTheme.colors.surface
            ) {
                bottomNavItems.forEach { item ->
                    AppBottomBarItem(
                        screen = item,
                        label = item.title.toString(),
                        contentDescription = item.title,
                        selected = currentDestination?.route == item.route,
                        enabled = item.icon_outlined != null,
                        onClick = {
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                popUpTo(Screen.Profile.route) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    }

}