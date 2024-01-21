package com.example.myappcompose.viewmodel

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.AppBrandData
import com.example.example.BrandCategory
import com.example.example.CategoryList
import com.example.myappcompose.di.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandViewModel @Inject constructor(val repository: NetworkRepository) : ViewModel() {

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