package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class LocationRateCustomization(
    @SerializedName("addedOn") val addedOn: Date,
    @SerializedName("updatedOn") val updatedOn: Date,
    @SerializedName("id") val id: Int,
    @SerializedName("locationRateId") val locationRateId: Int,
    @SerializedName("image") val image: String,
    @SerializedName("duration") val duration: String?=null,
    @SerializedName("note") val note: String,
    @SerializedName("paidOnExit") val paidOnExit: Boolean,
    @SerializedName("locationRate") val locationRate: String
)