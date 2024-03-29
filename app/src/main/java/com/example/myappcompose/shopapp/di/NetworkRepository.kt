package com.example.myappcompose.shopapp.di

import androidx.lifecycle.SavedStateHandle
import com.example.example.CategoryList
import com.example.myappcompose.shopapp.data.ErrorResponse
import com.example.myappcompose.shopapp.data.Response
import com.example.myappcompose.shopapp.data.SuccessResponse
import com.example.myappcompose.shopapp.di.network.APINetworkInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class NetworkRepository @Inject constructor(
    val apiNetworkInterface: APINetworkInterface,
    private val savedStateHandle: SavedStateHandle
) {
    val _categoryList = MutableStateFlow<List<CategoryList>>(emptyList())
    val categoryList: StateFlow<List<CategoryList>> get() = _categoryList


    suspend fun getData(): Response<List<CategoryList>> {
        return try {
            val response = apiNetworkInterface.getData()
            _categoryList.value = response
            SuccessResponse(response)
        } catch (ee: Exception) {
            ErrorResponse("", ee)
        }
    }


}