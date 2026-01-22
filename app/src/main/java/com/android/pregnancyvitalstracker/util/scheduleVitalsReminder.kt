package com.android.pregnancyvitalstracker.util

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.android.pregnancyvitalstracker.VitalsReminderWorker
import java.util.concurrent.TimeUnit

fun scheduleVitalsReminder(context: Context) {

    val workRequest = PeriodicWorkRequestBuilder<VitalsReminderWorker>(
        1, TimeUnit.HOURS
    ).build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "VitalsReminderWork",
        ExistingPeriodicWorkPolicy.UPDATE,
        workRequest
    )
}
