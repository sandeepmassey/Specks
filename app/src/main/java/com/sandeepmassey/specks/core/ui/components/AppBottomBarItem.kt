package com.sandeepmassey.specks.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sandeepmassey.specks.core.ui.theme.Orangish
import com.sandeepmassey.specks.navigation.Screen

/**
 * Created by Sandeep Massey on 28-03-2022
 */
@Composable
fun RowScope.AppBottomBarItem(
    screen: Screen,
    label: String = "",
    contentDescription: String? = null,
    selected: Boolean = false,
    enabled: Boolean = true,
    selectedColor: Color = Orangish,
    unSelectedColor: Color = MaterialTheme.colors.onSurface,
    onClick: () -> Unit = {}
) {
    BottomNavigationItem(
        selected = selected,
        enabled = enabled,
        onClick = onClick,
        label = {
            Text(text = label)
        },
        selectedContentColor = selectedColor,
        unselectedContentColor = unSelectedColor,
        alwaysShowLabel = true,
        icon = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (screen.icon_outlined != null) {
                    Icon(
                        imageVector = (if (selected) screen.icon_filled else screen.icon_outlined)!!,
                        contentDescription = contentDescription,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    )

}