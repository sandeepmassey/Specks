package com.sandeepmassey.specks.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.sandeepmassey.specks.core.util.Constants.CACHE_CLEAN_INTERVAL
import com.sandeepmassey.specks.core.util.Constants.CLEAN_CACHE
import com.sandeepmassey.specks.recipes.worker.LocalCacheCleaner
import java.util.concurrent.TimeUnit

/**
 * Created by Sandeep Massey on 06-04-2022
 */
@Composable
fun StartCacheCleaner() {

    val workManager = WorkManager.getInstance(LocalContext.current)

    val workConstraints = Constraints.Builder()
        .setRequiresDeviceIdle(true)
        .build()

    val cleanCacheRequest =
        PeriodicWorkRequestBuilder<LocalCacheCleaner>(CACHE_CLEAN_INTERVAL, TimeUnit.MINUTES)
            .setConstraints(workConstraints)
            .build()

    workManager.enqueueUniquePeriodicWork(
        CLEAN_CACHE,
        ExistingPeriodicWorkPolicy.KEEP,
        cleanCacheRequest
    )
}