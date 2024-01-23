package com.example.cs3200firebasestarter.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.AvatarListItem
import com.example.cs3200firebasestarter.ui.viewmodels.AvatarsViewModel
import androidx.compose.foundation.lazy.items
import kotlinx.coroutines.launch

@Composable
fun AvatarsHomeScreen(navHostController: NavHostController) {
    val viewModel: AvatarsViewModel = viewModel()
    val state = viewModel.uiState
    val scope = rememberCoroutineScope()
    
    LaunchedEffect(true) {
        viewModel.getAvatars()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Avatars Home Page!",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
    }

    LazyColumn {
//
        items(state.avatarList, key = {it.id!!}) {avatar ->
            AvatarListItem(
                avatar = avatar,
                onEditPressed = { navHostController.navigate("editavatar?id=${avatar.id}") }
            )
        }
        }
}