package com.sandeepmassey.specks.di

import android.content.Context
import androidx.room.Room
import com.sandeepmassey.specks.recipes.data.local.RecipesDatabase
import com.sandeepmassey.specks.recipes.data.repo.RecipesLocalDataSourceImpl
import com.sandeepmassey.specks.recipes.dom.repo.RecipesLocalDataSource
import com.sandeepmassey.specks.recipes.dom.util.Constants.RECIPES_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Sandeep Massey on 22-03-2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RecipesDatabase {
        return Room.databaseBuilder(
            context,
            RecipesDatabase::class.java,
            RECIPES_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: RecipesDatabase
    ): RecipesLocalDataSource {
        return RecipesLocalDataSourceImpl(
            recipesDatabase = database
        )
    }
}