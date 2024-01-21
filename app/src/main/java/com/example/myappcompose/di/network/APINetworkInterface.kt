package com.example.myappcompose.di.network

import com.example.example.AppBrandData
import com.example.example.BrandCategory
import com.example.example.CategoryList
import com.example.myappcompose.utils.Constants
import retrofit2.http.GET

interface APINetworkInterface {

    @GET("main/patanjali_products.json")
    suspend fun getData(): List<CategoryList>
}