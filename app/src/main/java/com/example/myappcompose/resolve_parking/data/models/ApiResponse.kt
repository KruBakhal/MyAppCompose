package com.example.myappcompose.resolve_parking.data.models

data class ApiResponse<T>(val isSuccess: Boolean, val data: T? = null, val message: String? = null,
                          val statusCode:Int?=null)
