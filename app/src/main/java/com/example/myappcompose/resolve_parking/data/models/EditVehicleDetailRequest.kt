package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName


data class EditVehicleDetailRequest(

    @SerializedName("note") var note: String? = null
)