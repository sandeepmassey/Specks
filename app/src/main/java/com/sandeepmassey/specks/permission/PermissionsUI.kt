package com.sandeepmassey.specks.permission

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.ui.theme.SMALL_PADDING

/**
 * Created by Sandeep Massey on 18-04-2022
 */
@ExperimentalPermissionsApi
@Composable
fun PermissionsUI(
    permission: String,
    rationale: String,
    warning: String,
    content: @Composable () -> Unit = {}
) {

    val permissionState = rememberPermissionState(permission = permission)
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    permissionState.launchPermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )

    when (permissionState.status) {
        PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(SMALL_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val textToShow = if (permissionState.status.shouldShowRationale) {
                    rationale
                } else {
                    warning
                }
                Text(text = textToShow)
                Spacer(modifier = Modifier.height(SMALL_PADDING))
                Button(onClick = {
                    permissionState.launchPermissionRequest()
                }) {
                    Text(text = stringResource(R.string.request_permission_button))
                }
            }
        }
    }
}