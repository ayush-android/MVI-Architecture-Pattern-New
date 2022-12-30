package com.mvi.demo.Intent

import com.mvi.demo.model.data.User
import com.mvi.demo.model.state.MainState

/*
* Here we need to define our actions which we will going to perform from our UI
* */
sealed class MainIntent {
    data class AddUser(val user: User) : MainIntent()
    object FetchUser : MainIntent()
    object GenerateError: MainIntent()
}