package com.example.learncache

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.learncache.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit(this)
    }


    private fun lateinit(context: Context) {
        CoroutineScope(Dispatchers.Default).launch {
            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.METERED)
                .build()

            val request = PeriodicWorkRequestBuilder<RefreshDataWorker>(
                1,TimeUnit.DAYS
            ).setConstraints(constraints).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "refresh use data",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }

    }
}