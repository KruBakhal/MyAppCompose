package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class PayOnExitResponseModel(
    @SerializedName("message") var message: String? = null
)