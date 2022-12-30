package com.mvi.demo.model.state

import com.mvi.demo.model.data.User


/*
* Here we need to define all the possible States during action(MainIntent) execution
* */
sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class Done(val successMsg: String): MainState()
    data class UserList(val list: List<User> = listOf(User("Amit", 20))) : MainState()
    data class Error(val message: String): MainState()
}