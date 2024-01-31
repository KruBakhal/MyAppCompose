package com.example.myappcompose.Practice.States

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GetStartWithStates(wellnessViewModel: WellnessVieModel = viewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        WaterCounterStateFull()
        WellnessScreen(wellnessViewModel.listTask, onClose = { task ->
            wellnessViewModel.remove(task)
        })
    }
}


private @Composable
fun WellnessScreen(
    list: List<WellnessTaskData>, onClose: (WellnessTaskData) -> Unit,
    modifier: Modifier = Modifier,

    ) {

    LazyColumn(modifier = modifier) {
        items(list, key = { task -> task.index }) { task ->
            WellnessTaskItem(task, onClose = onClose)
        }
    }

}

private @Composable
fun WellnessTaskItem(task: WellnessTaskData, onClose: (WellnessTaskData) -> Unit) {
    var checkedState by rememberSaveable { mutableStateOf(false) }
    WellnessTaskItem(
        task.text,
        checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = {
            onClose.invoke(task)
        },
        Modifier
    )
}

@Composable
fun WellnessTaskItem(
    task: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        Text(modifier = Modifier.weight(1f), text = task)
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(
            onClick = onClose
        ) {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Close"
            )
        }
    }
}

@Composable
fun WaterCounterStateFull() {
    var count by rememberSaveable { mutableStateOf(0) }
    var showTask by rememberSaveable() {
        mutableStateOf(true)
    }
    WaterCounterBodyStateLess(count, showTask, onIncrement = {
        count++
    }, onClose = {
        showTask = false
    })
}

@Composable
private fun WaterCounterBodyStateLess(
    count: Int, showTask: Boolean,
    onIncrement: () -> Unit, onClose: () -> Unit
) {
    Column(modifier = Modifier) {
        if (count > 0) {
            if (showTask) {
                WaterCounterItem() {
                    onClose.invoke()
                }
            }
        }
        Text(text = "Added water count ${count}")
        Button(onClick = { onIncrement.invoke() }) {
            Text(text = "Add")
        }
        Button(onClick = { onIncrement.invoke() }) {
            Text(text = "Clean")
        }
    }
}

private @Composable
fun WaterCounterItem(onClose: () -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Take 15 min to drink water",
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        IconButton(
            onClick = {
                onClose.invoke()
            }) {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Close"
            )
        }
    }
}