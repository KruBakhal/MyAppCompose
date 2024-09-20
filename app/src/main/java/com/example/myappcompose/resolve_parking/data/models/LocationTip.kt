package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class LocationTip(
    @SerializedName("addedOn") val addedOn: Date,
    @SerializedName("updatedOn") val updatedOn: Date,
    @SerializedName("id") val id: Int,
    @SerializedName("locationId") val locationId: Int,
    @SerializedName("amountPerc") val amountPerc: Int,
    @SerializedName("location") val location: String
)
