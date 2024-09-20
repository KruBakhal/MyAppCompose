package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class LocationRate(
    @SerializedName("addedOn") val addedOn: Date,
    @SerializedName("updatedOn") val updatedOn: Date,
    @SerializedName("id") val id: Int,
    @SerializedName("locationId") val locationId: Int,
    @SerializedName("amount") val amount: Double,
    @SerializedName("location") val location: String,
    @SerializedName("locationRateCustomizations") val locationRateCustomizations: List<LocationDuration>? = null
) {
    fun isCustomLocationRateDurationExist(): Boolean {
        return !locationRateCustomizations?.firstOrNull()?.duration.isNullOrEmpty()
    }
}