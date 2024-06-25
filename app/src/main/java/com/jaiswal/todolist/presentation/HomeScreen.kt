package com.jaiswal.todolist.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



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
