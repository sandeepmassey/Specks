package com.sandeepmassey.specks.auth.data.repo

import com.sandeepmassey.specks.auth.data.remote.AuthApi
import com.sandeepmassey.specks.auth.dom.model.AuthApiRequest
import com.sandeepmassey.specks.auth.dom.model.AuthApiResponse
import com.sandeepmassey.specks.auth.dom.model.UserUpdate
import com.sandeepmassey.specks.auth.dom.repo.AuthRepository
import com.sandeepmassey.specks.auth.dom.repo.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 18-03-2022
 */
class AuthRepositoryImpl @Inject constructor(
    private val dataStoreOperations: DataStoreOperations,
    private val authApi: AuthApi
): AuthRepository {

    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStoreOperations.saveSignedInState(signedIn = signedIn)
    }

    override fun readSignedInState(): Flow<Boolean> = dataStoreOperations.readSignedInState()

    override suspend fun verifyTokenOnBackend(request: AuthApiRequest): AuthApiResponse {
        return try {
            authApi.verifyTokenOnBackend(request = request)
        } catch (e: Exception) {
            AuthApiResponse(success = false, error = e)
        }
    }

    override suspend fun getUserInfo(): AuthApiResponse {
        return try {
            authApi.getUserInfo()
        } catch (e: Exception) {
            AuthApiResponse(success = false, error = e)
        }
    }

    override suspend fun updateUser(userUpdate: UserUpdate): AuthApiResponse {
        return try {
            authApi.updateUser(userUpdate = userUpdate)
        } catch (e: Exception) {
            AuthApiResponse(success = false, error = e)
        }
    }

    override suspend fun deleteUser(): AuthApiResponse {
        return try {
            authApi.deleteUser()
        } catch (e: Exception) {
            AuthApiResponse(success = false, error = e)
        }
    }

    override suspend fun clearSession(): AuthApiResponse {
        return try {
            authApi.clearSession()
        } catch (e: Exception) {
            AuthApiResponse(success = false, error = e)
        }
    }

}