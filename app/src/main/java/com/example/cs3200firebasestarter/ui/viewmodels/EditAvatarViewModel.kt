package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.models.Avatar
import com.example.cs3200firebasestarter.ui.repositories.AvatarsRepository

class EditAvatarScreenState {
    var id by mutableStateOf("")
    var userId by mutableStateOf("")
    var name by mutableStateOf("")
    var age by mutableStateOf("")
    var race by mutableStateOf("")
    var description by mutableStateOf("")
    var height by mutableStateOf("")
    var occupation by mutableStateOf("")
    var gender by mutableStateOf("")

    var nameError by mutableStateOf(false)
    var heightError by mutableStateOf(false)
    var dropDownExpanded by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
    var saveSuccess by mutableStateOf(false)
}

class EditAvatarViewModel(application: Application) : AndroidViewModel(application) {
    val uiState = EditAvatarScreenState()
    private var id: String? = null


    // if an avatar with that id is in repository, updates the uiState,
    // otherwise, just returns
    suspend fun setUpInitialState(id: String?) {
        if (id == null || id == "new") return
        this.id = id
        val avatar = AvatarsRepository.getAvatars().find { it.id == id} ?: return
        uiState.name = avatar.name ?: ""
        uiState.age = avatar.age ?: ""
        uiState.race = avatar.race ?: ""
        uiState.occupation = avatar.occupation ?: ""
        uiState.height = avatar.height ?: ""
        uiState.description = avatar.description ?: ""
        uiState.gender = avatar.gender ?: ""
    }

    // validates input can be converted to int, converts input to Int and
    // updates the uiState with that
    fun updateHeight(input: String) {
        uiState.heightError = false
        uiState.errorMessage = ""

        try {
            input.filter { !it.isWhitespace() }.toInt()
        } catch(e: Exception){
            uiState.heightError = true
            uiState.errorMessage = "Height must be a valid number"
        } finally {
            uiState.height = input.filter { !it.isWhitespace() }
        }
    }

    // saves the avatar whether we're creating new or editing existing based on
    // the id. Calls "CreateAvatar" or "UpdateAvatar" which both update firestore
    suspend fun saveAvatar() {
        // if there are any errors, don't do anything
        if (uiState.heightError) return
        uiState.errorMessage = ""
        uiState.nameError = false

        if (uiState.name.isEmpty()){
            uiState.nameError = true
            uiState.errorMessage = "Title cannot be Blank"
            return
        }

        // create new avatar if id isn't found
        if (id == null) {
            AvatarsRepository.createAvatar(
                uiState.name,
                uiState.age,
                uiState.race,
                uiState.occupation,
                uiState.height,
                uiState.description,
                uiState.gender
            )


            // TODO: THIS IS WRONG FIX THIS
        } else {
            // update existing avatar with new information
            val avatarToUpdate = AvatarsRepository.getAvatars().find { it.id == id } ?: return
            AvatarsRepository.updateAvatar(
                avatarToUpdate.copy(
                id = avatarToUpdate?.id,
                userId = avatarToUpdate?.userId,
                name = uiState.name,
                age = uiState.age,
                race = uiState.race,
                occupation = uiState.occupation,
                height = uiState.height,
                description = uiState.description,
                gender = uiState.gender
                )
            )
        }

        uiState.saveSuccess = true
    }
}
