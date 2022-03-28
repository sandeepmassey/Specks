package com.sandeepmassey.specks.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.sandeepmassey.specks.recipes.dom.model.Mixing

/**
 * Created by Sandeep Massey on 25-03-2022
 */
@Composable
fun UnOrderedList(
    title: String,
    items: List<Mixing>,
    textColor: Color
) {
    Column {
        Text(
            modifier = Modifier.alpha(ContentAlpha.medium),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.overline.fontSize
        )
        items.forEach { item ->
            Text(
                text = "${item.material} ${item.count} ${item.length} ${item.ratio}%",
                color = textColor,
                fontSize = MaterialTheme.typography.caption.fontSize,
                fontWeight = FontWeight.Black
            )
        }
    }
}