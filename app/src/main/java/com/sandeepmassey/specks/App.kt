package com.sandeepmassey.specks

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.sandeepmassey.specks.core.util.Constants.CLEAN_CACHE_CHANNEL
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by Sandeep Massey on 18-03-2022
 */
@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        val notificationChannel = NotificationChannel(
            CLEAN_CACHE_CHANNEL,
            "Clean cache",
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(NotificationManager::class.java).apply {
            createNotificationChannel(notificationChannel)
        }
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.ERROR)
            .setWorkerFactory(workerFactory)
            .build()
}