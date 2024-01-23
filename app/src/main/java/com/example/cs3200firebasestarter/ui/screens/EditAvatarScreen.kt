package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.FormField
import com.example.cs3200firebasestarter.ui.navigation.Routes
import com.example.cs3200firebasestarter.ui.viewmodels.EditAvatarViewModel
import kotlinx.coroutines.launch

@Composable
fun EditAvatarScreen(navHostController: NavHostController, id: String?) {
    val viewModel: EditAvatarViewModel = viewModel()
    val state = viewModel.uiState
    val scope = rememberCoroutineScope()

    LaunchedEffect(true){
        viewModel.setUpInitialState(id)
    }

    LaunchedEffect(state.saveSuccess) {
        if (state.saveSuccess) {
            navHostController.popBackStack()
        }
    }
    Column(modifier = Modifier.padding(16.dp)) {
        FormField(
            value = state.name,
            onValueChange = { state.name = it },
            placeholder = { Text(text = "Name") }
        )
        Spacer(modifier = Modifier.height(4.dp))
        FormField(
            value = state.description,
            onValueChange = { state.description = it },
            placeholder = { Text(text = "Description") }
        )
        Spacer(modifier = Modifier.height(4.dp))
        FormField(
            value = state.age,
            onValueChange = { state.age = it },
            placeholder = { Text(text = "Age (years)") }
        )
        Spacer(modifier = Modifier.height(4.dp))
        FormField(
            value = state.height.toString(),
            onValueChange = {
                state.height = it
                viewModel.updateHeight(it)
            },
            placeholder = { Text(text = "Height (feet)") },
            error = state.heightError
        )
        Spacer(modifier = Modifier.height(4.dp))
        FormField(
            value = state.race,
            onValueChange = { state.race = it },
            placeholder = { Text(text = "Race") }
        )
        Spacer(modifier = Modifier.height(4.dp))
        FormField(
            value = state.occupation,
            onValueChange = { state.occupation = it },
            placeholder = { Text(text = "Class") }
        )
        Spacer(modifier = Modifier.height(4.dp))
        FormField(
            value = state.gender,
            onValueChange = { state.gender = it },
            placeholder = { Text(text = "Gender") }
        )


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = {
                navHostController.navigate(Routes.appNavigation.route)
            }) {
                Text(text = "Cancel")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                scope.launch {
                    viewModel.saveAvatar()
                }
            }) {
                Text(text = "Save")
            }
        }
        Text(
            text = state.errorMessage,
            style = androidx.compose.ui.text.TextStyle(color = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Right
        )
    }
}