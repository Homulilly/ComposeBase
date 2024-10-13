package com.example.simpleroom.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simpleroom.data.User
import kotlinx.coroutines.launch

@Composable
fun AddUserScreen(
    viewModel : UserViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()

    Column{
        UserEntry(
            viewModel.uiState.user,
            viewModel::updateUserUiState
        )

        Button( onClick = { coroutineScope.launch {
                    viewModel.addUser()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            enabled = viewModel.uiState.isButtonEnable
        ) { Text("Add") }

    }
}

@Composable
fun UserEntry(
    user: User,
    onValueChange : (User) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = user.name,
            onValueChange = { onValueChange(user.copy(name = it)) },
            label = { Text("Name:") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = user.bio,
            onValueChange = { onValueChange(user.copy(bio = it)) },
            label = { Text("Bio:") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
    }
}
