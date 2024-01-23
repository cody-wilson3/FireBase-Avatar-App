package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.models.Avatar
import com.example.cs3200firebasestarter.ui.repositories.AvatarsRepository

class AvatarsScreenState {
    val _avatarList = mutableStateListOf<Avatar>()
    val avatarList: List<Avatar> get() = _avatarList
}

class AvatarsViewModel(application: Application): AndroidViewModel(application) {
    val uiState = AvatarsScreenState()


    // just gets the avatars from the AvatarRepository and adds
    // them to this uiState
    suspend fun getAvatars(): List<Avatar> {
        val avatars = AvatarsRepository.getAvatars()
        uiState._avatarList.clear()
        uiState._avatarList.addAll(avatars)
        return avatars
    }
}