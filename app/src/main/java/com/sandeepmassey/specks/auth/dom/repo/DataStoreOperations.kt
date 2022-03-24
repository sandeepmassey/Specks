package com.sandeepmassey.specks.auth.dom.repo

import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Massey on 18-03-2022
 */
interface DataStoreOperations {

    fun readSignedInState(): Flow<Boolean>
    suspend fun saveSignedInState(signedIn: Boolean)
}