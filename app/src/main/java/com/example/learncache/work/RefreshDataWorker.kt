package com.example.learncache.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.learncache.database.SampleDatabase
import com.example.learncache.database.UserRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params :WorkerParameters)
    : CoroutineWorker(appContext,params){
    override suspend fun doWork(): Result {
        val userDAO = SampleDatabase.getInstance(applicationContext).userDAO()
        val repository = UserRepository(userDAO)

        try {
            repository.refreshUsers()
            return Result.success()
        }catch (e: HttpException){
            return Result.retry()
        }
    }
}