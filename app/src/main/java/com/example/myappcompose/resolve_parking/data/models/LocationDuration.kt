package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date

data class LocationDuration(
    @SerializedName("addedOn") val addedOn: Date,
    @SerializedName("updatedOn") val updatedOn: Date,
    @SerializedName("durationStr") val durationStr: String,
    @SerializedName("id") val id: Int,
    @SerializedName("locationId") val locationId: Int,
    @SerializedName("duration") val duration: String?=null,
    @SerializedName("durationType") val durationType: Int,
    @SerializedName("location") val location: String,
    @SerializedName("locationRateId") val locationRateId: Int,
    @SerializedName("image") val image: String?=null,
    @SerializedName("note") val note: String?=null,
    @SerializedName("paidOnExit") val paidOnExit: Boolean=false,
    @SerializedName("locationRate") val locationRate: String?=null
): Serializable {
    override fun toString(): String {
        return durationStr
    }
}