package com.sandeepmassey.specks.auth.ui.profile

import android.app.Activity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.google.android.gms.auth.api.identity.Identity
import com.sandeepmassey.specks.auth.data.util.StartActivityForResult
import com.sandeepmassey.specks.auth.data.util.signIn
import com.sandeepmassey.specks.auth.dom.model.AuthApiRequest
import com.sandeepmassey.specks.auth.dom.model.AuthApiResponse
import com.sandeepmassey.specks.core.util.RequestState
import com.sandeepmassey.specks.navigation.Screen
import retrofit2.HttpException

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@ExperimentalCoilApi
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val apiResponse by profileViewModel.apiResponse
    val clearSessionResponse by profileViewModel.clearSessionResponse
    val messageBarState by profileViewModel.messageBarState

    val user by profileViewModel.user
    val firstName by profileViewModel.firstName
    val lastName by profileViewModel.lastName

    Scaffold(
        topBar = {
            ProfileTopBar(
                onSave = {
                    //profileViewModel.updateUserInfo()
                    navigateToRecipesScreen(navController = navController)
                },
                onDeleteAllConfirmed = {
                    profileViewModel.deleteUser()
                }
            )
        },
        content = {
            ProfileContent(
                apiResponse = apiResponse,
                messageBarState = messageBarState,
                firstName = firstName,
                lastName = lastName,
                emailAddress = user?.emailAddress,
                profilePhoto = user?.profilePhoto
            ) {
                profileViewModel.clearSession()
            }
        }
    )

    val activity = LocalContext.current as Activity

    StartActivityForResult(
        key = apiResponse,
        onResultReceived = { token ->
            profileViewModel.verifyTokenOnBackend(
                request = AuthApiRequest(token = token)
            )
        },
        onDialogDismissed = {
            profileViewModel.saveSignedInState(signedIn = false)
            navigateToLoginScreen(navController = navController)
        }
    ) { activityLauncher ->
        if (apiResponse is RequestState.Success) {
            val response = (apiResponse as RequestState.Success<AuthApiResponse>).data
            if (response.error is HttpException && response.error.code() == 401) {
                signIn(
                    activity = activity,
                    accountNotFound = {
                        profileViewModel.saveSignedInState(signedIn = false)
                        navigateToLoginScreen(navController = navController)
                    },
                    launchActivityResult = {
                        activityLauncher.launch(it)
                    }
                )
            }
        } else if (apiResponse is RequestState.Error) {
            profileViewModel.saveSignedInState(signedIn = false)
            navigateToLoginScreen(navController = navController)
        }
    }

    LaunchedEffect(key1 = clearSessionResponse) {
        if (clearSessionResponse is RequestState.Success &&
            (clearSessionResponse as RequestState.Success<AuthApiResponse>).data.success
        ) {
            val oneTapClient = Identity.getSignInClient(activity)
            oneTapClient.signOut()
            profileViewModel.saveSignedInState(signedIn = false)
            navigateToLoginScreen(navController = navController)
        }
    }
}

private fun navigateToLoginScreen(
    navController: NavHostController
) {
    navController.navigate(route = Screen.Login.route) {
        popUpTo(route = Screen.Profile.route) {
            inclusive = true
        }
    }
}

private fun navigateToRecipesScreen(
    navController: NavHostController
) {
    navController.navigate(route = Screen.Recipes.route) {
        popUpTo(route = Screen.Profile.route) {
            //inclusive = true
        }
    }
}