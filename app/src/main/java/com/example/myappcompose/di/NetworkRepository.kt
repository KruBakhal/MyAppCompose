package com.example.myappcompose.di

import com.example.example.AppBrandData
import com.example.example.CategoryList
import com.example.example.ProductList
import com.example.myappcompose.data.ErrorResponse
import com.example.myappcompose.data.Response
import com.example.myappcompose.data.SuccessResponse
import com.example.myappcompose.di.network.APINetworkInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class NetworkRepository @Inject constructor(val apiNetworkInterface: APINetworkInterface) {


    val _categoryList = MutableStateFlow<List<CategoryList>>(emptyList())
    val categoryList: StateFlow<List<CategoryList>> get() = _categoryList


    suspend fun getData(): Response<List<CategoryList>> {
        return try {
            val response = apiNetworkInterface.getData()

            SuccessResponse(response)
        } catch (ee: Exception) {
            ErrorResponse("", ee)
        }
    }


}