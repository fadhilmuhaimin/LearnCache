package com.example.learncache.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learncache.database.SampleDatabase
import com.example.learncache.database.UserDAO
import com.example.learncache.database.UserRepository
import com.example.learncache.database.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : UserRepository
    private val userDAO : UserDAO

    private  val _items : LiveData<List<User>>

    val items : LiveData<List<User>>
        get() = _items

    private val _response = MutableLiveData<String>()

    val response : LiveData<String>
        get() = _response


    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
        userDAO = SampleDatabase.getInstance(application).userDAO()
        repository = UserRepository(userDAO)
        _response.value = "Loading ....."
        crScope.launch{
            try{
               repository.refreshUsers()
            }catch (t: Throwable){
                _response.value = "Failed, Internet OFF"
            }
        }
        _items = repository.user
        _response.value = "OK"
    }



    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

}