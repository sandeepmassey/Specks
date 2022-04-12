package com.sandeepmassey.specks.auth.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.auth.dom.model.AuthApiResponse
import com.sandeepmassey.specks.auth.dom.model.MessageBarState
import com.sandeepmassey.specks.core.ui.components.GoogleButton
import com.sandeepmassey.specks.core.ui.components.MessageBar
import com.sandeepmassey.specks.core.util.RequestState

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@ExperimentalCoilApi
@Composable
fun ProfileContent(
    apiResponse: RequestState<AuthApiResponse>,
    messageBarState: MessageBarState,
    firstName: String,
    lastName: String,
    emailAddress: String?,
    profilePhoto: String?,
    onSignOutClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.weight(1f)) {
            if (apiResponse is RequestState.Loading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                MessageBar(messageBarState = messageBarState)
            }
        }
        Column(
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth(0.7f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val painter = rememberImagePainter(data = profilePhoto) {
                crossfade(500)
                placeholder(R.drawable.ic_placeholder)
            }
            Image(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .size(150.dp)
                    .clip(CircleShape),
                painter = painter,
                contentDescription = stringResource(id = R.string.profile_photo_text)
            )
            Text(
                text = stringResource(id = R.string.welcome_text),
                fontSize = MaterialTheme.typography.h5.fontSize
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "$firstName $lastName",
                fontSize = MaterialTheme.typography.h6.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = emailAddress.toString(),
                fontSize = MaterialTheme.typography.body2.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))
            GoogleButton(
                modifier = Modifier
                    .padding(top = 24.dp),
                primaryText = stringResource(id = R.string.sign_out_text),
                secondaryText = stringResource(id = R.string.sign_out_text),
                onClick = onSignOutClicked
            )
        }
    }
}
