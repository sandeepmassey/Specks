package com.sandeepmassey.specks.recipes.worker

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.sandeepmassey.specks.R
import com.sandeepmassey.specks.core.util.Constants.CLEAN_CACHE_CHANNEL
import com.sandeepmassey.specks.recipes.dom.use_cases.UseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber
import kotlin.random.Random

/**
 * Created by Sandeep Massey on 06-04-2022
 */
@HiltWorker
class LocalCacheCleaner @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters,
    private val useCases: UseCases
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        return try {
            showNotification()
            useCases.removeAllRecipesUseCase()
            Result.success()
        } catch (e: Exception) {
            Timber.d(e.message.toString())
            Result.failure()
        }
    }

    private suspend fun showNotification() {
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationCompat.Builder(context, CLEAN_CACHE_CHANNEL)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText("Cleaning...")
                    .setContentTitle(R.string.app_name.toString())
                    .build()
            )
        )
    }

}