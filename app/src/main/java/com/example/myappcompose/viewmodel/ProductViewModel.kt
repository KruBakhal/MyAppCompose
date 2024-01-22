package com.example.myappcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.example.CategoryList
import com.google.gson.Gson

class ProductViewModel(private val onSavedStateHandle: SavedStateHandle) : ViewModel() {

    val _categoryProductList = mutableStateOf(CategoryList())
    val categoryProductList
        get() = _categoryProductList

    init {
        if (!onSavedStateHandle.get<String>("category").isNullOrEmpty()) {
            val json = onSavedStateHandle.get<String>("category")
            _categoryProductList.value = if (!json.isNullOrEmpty()) {
                Gson().fromJson(json, CategoryList::class.java)
            } else {
                CategoryList()
            }
        } else {
            CategoryList()
        }
    }


}