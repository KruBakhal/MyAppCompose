package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class VerifyPinRequest(
    @SerializedName("devicePIN") val devicePIN: String
    // Other necessary fields in the request body
)
