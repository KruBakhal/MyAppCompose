package com.example.myappcompose.shopapp.di.network

import com.example.example.CategoryList
import retrofit2.http.GET

interface APINetworkInterface {

    @GET("main/patanjali_products.json")
    suspend fun getData(): List<CategoryList>
}