package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.repositories.UserRepository

class RootNavigationViewModel(application: Application): AndroidViewModel(application) {
    fun signUserOut() {
        UserRepository.signOutUser()
    }
}