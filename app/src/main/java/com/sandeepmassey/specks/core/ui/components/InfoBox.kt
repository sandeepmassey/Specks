package com.sandeepmassey.specks.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

/**
 * Created by Sandeep Massey on 25-03-2022
 */
@Composable
fun InfoBox(
    bigText: String,
    smallText: String,
    textColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.overline.fontSize
            )
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.body1.fontSize,
                fontWeight = FontWeight.Black
            )
        }
    }
}