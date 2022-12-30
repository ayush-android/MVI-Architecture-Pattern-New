package com.mvi.demo.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvi.demo.Intent.MainIntent
import com.mvi.demo.model.data.User
import com.mvi.demo.model.state.MainState
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {

    private val mutableStateLiveData = MutableLiveData<MainState>(MainState.Idle)
    val stateLiveData: LiveData<MainState> = mutableStateLiveData

    fun processIntent(mainIntent: MainIntent){
        when(mainIntent){
            is MainIntent.AddUser -> addUser(mainIntent.user)
            is MainIntent.FetchUser -> fetchUser()
            MainIntent.GenerateError -> generateError()
        }

    }

    private fun addUser(user: User){
        mutableStateLiveData.value = MainState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            withContext(Dispatchers.Main){
            mutableStateLiveData.value = MainState.Done("User Added")
            }
        }
    }

    private fun fetchUser(){
        mutableStateLiveData.value = MainState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            withContext(Dispatchers.Main){
                mutableStateLiveData.value = MainState.UserList(listOf(User("Aman",20)))
            }
        }
    }

    private fun generateError(){
        mutableStateLiveData.value = MainState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            withContext(Dispatchers.Main){
                mutableStateLiveData.value = MainState.Error("Error while fetching user")
            }
        }
    }

}