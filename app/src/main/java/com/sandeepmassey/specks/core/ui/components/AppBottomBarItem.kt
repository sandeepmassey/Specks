package com.sandeepmassey.specks.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
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
    badgeCount: Int = 0,
    onClick: () -> Unit = {}
) {
    BottomNavigationItem(
        selected = selected,
        enabled = enabled,
        onClick = onClick,
        label = {
            Text(
                text = label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        selectedContentColor = selectedColor,
        unselectedContentColor = unSelectedColor,
        alwaysShowLabel = true,
        icon = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BadgedBox(badge = {
                    if (badgeCount > 0) {
                        Text(
                            text = badgeCount.toString(),
                            modifier = Modifier.background(color = MaterialTheme.colors.error),
                            color = MaterialTheme.colors.onSurface,
                            fontSize = MaterialTheme.typography.overline.fontSize
                        )
                    }
                }) {
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
        }
    )

}