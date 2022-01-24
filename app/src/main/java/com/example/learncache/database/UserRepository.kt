package com.example.learncache.database
import com.example.learncache.network.SampleApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository (private val userDAO: UserDAO){
    val user = userDAO.loadUsers()

    suspend fun refreshUsers(){
        withContext(Dispatchers.IO){
            val users = SampleApi.retrofitService.showList()
            userDAO.insertAll(users)
        }
    }
}