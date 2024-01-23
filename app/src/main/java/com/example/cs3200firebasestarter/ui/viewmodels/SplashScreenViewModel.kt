package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.repositories.UserRepository

class SplashScreenViewModel(application: Application): AndroidViewModel(application) {
    fun isUserLoggedIn() = UserRepository.isUserLoggedIn()
}