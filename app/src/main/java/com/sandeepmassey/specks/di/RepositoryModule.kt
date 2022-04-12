package com.sandeepmassey.specks.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.sandeepmassey.specks.auth.data.remote.AuthApi
import com.sandeepmassey.specks.auth.data.repo.AuthRepositoryImpl
import com.sandeepmassey.specks.auth.data.repo.DataStoreOperationsImpl
import com.sandeepmassey.specks.auth.dom.repo.AuthRepository
import com.sandeepmassey.specks.auth.dom.repo.DataStoreOperations
import com.sandeepmassey.specks.core.util.Constants.PREFERENCES_NAME
import com.sandeepmassey.specks.recipes.data.repo.RecipesRepository
import com.sandeepmassey.specks.recipes.dom.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(PREFERENCES_NAME) }
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        dataStore: DataStore<Preferences>
    ): DataStoreOperations {
        return DataStoreOperationsImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        dataStoreOperations: DataStoreOperations,
        authApi: AuthApi
    ): AuthRepository {
        return AuthRepositoryImpl(
            dataStoreOperations = dataStoreOperations,
            authApi = authApi
        )
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: RecipesRepository): UseCases {
        return UseCases(
            getAllRecipesUseCase = GetAllRecipesUseCase(repository),
            getSelectedRecipeUseCase = GetSelectedRecipeUseCase(repository),
            searchRecipesUseCase = SearchRecipesUseCase(repository),
            removeAllRecipesUseCase = RemoveAllRecipesUseCase(repository),
            getAllFavoriteRecipesUseCase = GetAllFavoriteRecipesUseCase(repository),
            addFavoriteRecipeUseCase = AddFavoriteRecipeUseCase(repository),
            removeFavoriteRecipeUseCase = RemoveFavoriteRecipeUseCase(repository)
        )
    }
}