package com.sandeepmassey.specks.auth.dom.repo

import com.sandeepmassey.specks.auth.dom.model.AuthApiRequest
import com.sandeepmassey.specks.auth.dom.model.AuthApiResponse
import com.sandeepmassey.specks.auth.dom.model.UserUpdate
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Massey on 18-03-2022
 */
interface AuthRepository {

    suspend fun saveSignedInState(signedIn: Boolean)
    fun readSignedInState(): Flow<Boolean>
    suspend fun verifyTokenOnBackend(request: AuthApiRequest): AuthApiResponse
    suspend fun getUserInfo(): AuthApiResponse
    suspend fun updateUser(userUpdate: UserUpdate): AuthApiResponse
    suspend fun deleteUser(): AuthApiResponse
    suspend fun clearSession(): AuthApiResponse
}