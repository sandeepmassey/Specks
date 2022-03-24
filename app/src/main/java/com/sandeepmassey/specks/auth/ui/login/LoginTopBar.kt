package com.sandeepmassey.specks.auth.ui.login

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@Composable
fun LoginTopBar() {

    TopAppBar(
        title = {
            Text(
                text = "Sign in",
                color = MaterialTheme.colors.primary
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}