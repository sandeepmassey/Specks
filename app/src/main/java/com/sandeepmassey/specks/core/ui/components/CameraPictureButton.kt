package com.sandeepmassey.specks.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Sandeep Massey on 26-04-2022
 */
@Composable
fun CameraPictureButton(
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (isPressed) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
    val contentPadding = PaddingValues(if (isPressed) 8.dp else 12.dp)
    OutlinedButton(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary),
        contentPadding = contentPadding,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.primary),
        enabled = false,
        onClick = { /*GNDN*/ }
    ) {
        Button(
            modifier = modifier,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            interactionSource = interactionSource,
            onClick = onClick
        ) {}
    }
}
