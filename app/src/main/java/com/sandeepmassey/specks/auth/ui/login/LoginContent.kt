package com.sandeepmassey.specks.auth.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.auth.dom.model.MessageBarState
import com.sandeepmassey.specks.core.ui.GoogleButton
import com.sandeepmassey.specks.core.ui.MessageBar

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@Composable
fun LoginContent(
    signedInState: Boolean,
    messageBarState: MessageBarState,
    onButtonClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.weight(1f)) {
            MessageBar(messageBarState = messageBarState)
        }
        Column(
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(200.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.app_logo)
            )
            Text(
                text = stringResource(id = R.string.sign_in_title),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.h5.fontSize
            )
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .padding(bottom = 40.dp, top = 4.dp),
                text = stringResource(id = R.string.sign_in_subtitle),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                textAlign = TextAlign.Center
            )
            GoogleButton(
                loadingState = signedInState,
                onClick = onButtonClicked
            )
        }
    }
}