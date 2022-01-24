package com.example.learncache.network

import com.example.learncache.database.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://my-json-server.typicode.com/sekolahkoding/")
    .build()


//API interface
interface SampleService{
    @GET("sample-api/users")
    suspend fun showList():
            List<User>
}

object SampleApi{
    val retrofitService = retrofit.create(SampleService::class.java)
}