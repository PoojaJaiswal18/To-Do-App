package com.jaiswal.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var itemsList by remember { mutableStateOf(listOf<String>()) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = {
            Column(modifier = Modifier.fillMaxSize().padding(it)) {
                Text(text = "To-Do List",
                    modifier = Modifier
                        .padding(16.dp),
                    fontSize = 33.sp,




                )

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(itemsList) { item ->
                        var checked by remember { mutableStateOf(false) }

                        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Checkbox(checked = checked, onCheckedChange = { checked = it })
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = item,
                                fontSize=30.sp
                            )
                        }
                    }
                }
            }
        }
    )

    if (showDialog) {
        PopUp(onDismiss = { showDialog = false }, onAdd = { newItem ->
            itemsList = itemsList + newItem
            showDialog = false
        })
    }
}

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




