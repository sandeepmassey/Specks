package com.sandeepmassey.specks.auth.ui.login

import android.app.Activity
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sandeepmassey.specks.auth.data.util.StartActivityForResult
import com.sandeepmassey.specks.auth.data.util.signIn
import com.sandeepmassey.specks.auth.dom.model.AuthApiRequest
import com.sandeepmassey.specks.auth.dom.model.AuthApiResponse
import com.sandeepmassey.specks.navigation.Screen
import com.sandeepmassey.specks.core.util.RequestState

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val signedInState by loginViewModel.signedInState
    val messageBarState by loginViewModel.messageBarState
    val apiResponse by loginViewModel.apiResponse

    Log.d("LoginScreen", apiResponse.toString())

    Scaffold(
        topBar = {
            LoginTopBar()
        },
        content = {
            LoginContent(
                signedInState = signedInState,
                messageBarState = messageBarState,
                onButtonClicked = {
                    loginViewModel.saveSignedInState(signedIn = true)
                }
            )
        }
    )

    val activity = LocalContext.current as Activity

    StartActivityForResult(
        key = signedInState,
        onResultReceived = { token ->
            loginViewModel.verifyTokenOnBackend(
                request = AuthApiRequest(token = token)
            )
        },
        onDialogDismissed = {
            loginViewModel.saveSignedInState(signedIn = false)
        }
    ) { activityLauncher ->
        if (signedInState) {
            signIn(
                activity = activity,
                launchActivityResult = { intentSenderRequest ->
                    activityLauncher.launch(intentSenderRequest)
                },
                accountNotFound = {
                    loginViewModel.saveSignedInState(signedIn = false)
                    loginViewModel.updateMessageBarState()
                }
            )
        }
    }

    LaunchedEffect(key1 = apiResponse) {
        when (apiResponse) {
            is RequestState.Success -> {
                val response = (apiResponse as RequestState.Success<AuthApiResponse>).data.success
                if (response) {
                    navigateToProfileScreen(navController = navController)
                } else {
                    loginViewModel.saveSignedInState(signedIn = false)
                }
            }
            else -> {}
        }
    }
}

private fun navigateToProfileScreen(
    navController: NavHostController
) {
    navController.navigate(route = Screen.Profile.route) {
        popUpTo(route = Screen.Login.route) {
            inclusive = true
        }
    }
}