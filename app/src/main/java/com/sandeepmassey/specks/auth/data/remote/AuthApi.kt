package com.sandeepmassey.specks.auth.data.remote

import com.sandeepmassey.specks.auth.dom.model.AuthApiRequest
import com.sandeepmassey.specks.auth.dom.model.AuthApiResponse
import com.sandeepmassey.specks.auth.dom.model.UserUpdate
import retrofit2.http.*

/**
 * Created by Sandeep Massey on 18-03-2022
 */
interface AuthApi {

    @POST("/token_verification")
    suspend fun verifyTokenOnBackend(
        @Body request: AuthApiRequest
    ): AuthApiResponse

    @GET("/get_user")
    suspend fun getUserInfo(): AuthApiResponse

    @PUT("/update_user")
    suspend fun updateUser(
        @Body userUpdate: UserUpdate
    ): AuthApiResponse

    @DELETE("/delete_user")
    suspend fun deleteUser(): AuthApiResponse

    @GET("/sign_out")
    suspend fun clearSession(): AuthApiResponse
}