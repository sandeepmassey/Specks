package com.sandeepmassey.specks.recipes.ui.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.*
import androidx.paging.LoadState
import com.sandeepmassey.specks.R
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Created by Sandeep Massey on 21-03-2022
 */
@Composable
fun EmptyScreen(
    error: LoadState.Error? = null
) {
    var message by remember {
        mutableStateOf("Find your Favorite Recipe!")
    }
    var icon by remember {
        mutableStateOf(R.drawable.ic_search_document)
    }

    if (error != null) {
        message = parseErrorMessage(error = error)
        icon = R.drawable.ic_network_error
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message
    )
}

fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable."
        }
        is ConnectException -> {
            "Internet Unavailable."
        }
        else -> {
            "Unknown Error.${error.error.message}"
        }
    }
}