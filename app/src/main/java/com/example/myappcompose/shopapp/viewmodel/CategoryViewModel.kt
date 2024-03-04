package com.example.myappcompose.shopapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.CategoryList
import com.example.myappcompose.shopapp.di.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val repository: NetworkRepository

) : ViewModel() {

    val categoryList: StateFlow<List<CategoryList>> get() = repository.categoryList

    init {
        getCategory()
    }

    fun getCategory() {
        viewModelScope.launch {
            repository.getData()
        }
    }


}