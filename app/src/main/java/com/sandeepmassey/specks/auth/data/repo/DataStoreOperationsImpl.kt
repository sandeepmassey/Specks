package com.sandeepmassey.specks.auth.data.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.sandeepmassey.specks.auth.data.util.Constants.PREFERENCES_SIGNED_IN_KEY
import com.sandeepmassey.specks.auth.dom.repo.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 18-03-2022
 */
class DataStoreOperationsImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): DataStoreOperations {

    private object PreferencesKey {
        val signedInKey = booleanPreferencesKey(name = PREFERENCES_SIGNED_IN_KEY)
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val signedInState = preferences[PreferencesKey.signedInKey] ?: false
                signedInState
            }
    }

    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.signedInKey] = signedIn
        }
    }
}