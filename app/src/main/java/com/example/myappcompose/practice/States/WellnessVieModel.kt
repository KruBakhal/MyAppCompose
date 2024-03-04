package com.example.myappcompose.practice.States

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessVieModel : ViewModel() {

    val _tasks = getWellnessTasks().toMutableStateList()
    val listTask get() = _tasks

    fun getWellnessTasks() = List(30) { i -> WellnessTaskData(i, "Task # $i") }
    fun remove(item: WellnessTaskData) {
        _tasks.remove(item)
    }
}