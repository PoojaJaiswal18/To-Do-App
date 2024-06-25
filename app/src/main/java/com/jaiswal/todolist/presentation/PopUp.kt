package com.jaiswal.todolist.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUp(onDismiss: () -> Unit,
          onAdd: (String) -> Unit
) {
    var item by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Add a new task") },
        text = {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = item,
                onValueChange = { newItem -> item = newItem },
                label = { Text(text = "Your Task") }
            )
        },
        confirmButton = {
            Button(onClick = {
                if (item.isNotEmpty()) {
                    onAdd(item)
                }
            }) {
                Text(text = "Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }
    )
}




