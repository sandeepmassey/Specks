package com.sandeepmassey.specks.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sandeepmassey.specks.auth.data.remote.AuthApi
import com.sandeepmassey.specks.auth.data.util.Constants.BASE_URL
import com.sandeepmassey.specks.recipes.data.local.RecipesDatabase
import com.sandeepmassey.specks.recipes.data.remote.RecipesApi
import com.sandeepmassey.specks.recipes.data.repo.RecipesRemoteDataSourceImpl
import com.sandeepmassey.specks.recipes.dom.repo.RecipesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.JavaNetCookieJar
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCookieManager(): CookieManager = CookieManager()

    @Provides
    @Singleton
    fun provideHttpClient(cookieManager: CookieManager): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(cookieManager))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideRecipesApi(retrofit: Retrofit): RecipesApi = retrofit.create(RecipesApi::class.java)

    @Provides
    @Singleton
    fun provideRecipesRemoteDataSource(
        recipesApi: RecipesApi,
        recipesDatabase: RecipesDatabase
    ): RecipesRemoteDataSource {
        return RecipesRemoteDataSourceImpl(
            recipesApi = recipesApi,
            recipesDatabase = recipesDatabase
        )
    }

}