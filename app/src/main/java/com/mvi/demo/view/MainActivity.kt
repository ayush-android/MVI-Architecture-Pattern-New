package com.mvi.demo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.mvi.demo.Intent.MainIntent
import com.mvi.demo.R
import com.mvi.demo.databinding.ActivityMainBinding
import com.mvi.demo.model.data.User
import com.mvi.demo.model.state.MainState

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.stateLiveData.observe(this){
            when(it){
                is MainState.Idle -> {setTextStatus("Idle")}
                is MainState.Done -> {setTextStatus("Done "+it.successMsg)}
                is MainState.Error -> {setTextStatus(it.message)}
                is MainState.Loading -> {setTextStatus("Loading")}
                is MainState.UserList -> {setTextStatus("User name is "+it.list[0].userName+" and age is "+it.list[0].age)}
            }
        }

        handleOnClick()
    }

    private fun setTextStatus(status: String){
        binding.textStatus.text = status
    }

    private fun handleOnClick() {
        binding.btnAdd.setOnClickListener{
            val user = User("Ram", 10)
            val addUserIntent = MainIntent.AddUser(user)
            mainViewModel.processIntent(addUserIntent)
        }

        binding.btnFetch.setOnClickListener{
            mainViewModel.processIntent(MainIntent.FetchUser)
        }

        binding.btnError.setOnClickListener{
            mainViewModel.processIntent(MainIntent.GenerateError)
        }

    }
}