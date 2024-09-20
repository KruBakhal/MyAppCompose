package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName


data class ForceEndShiftRequest(

    @SerializedName("userName") var userName: String? = null,
    @SerializedName("reason") var reason: String? = null

)