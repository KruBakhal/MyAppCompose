package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class Device(

    @SerializedName("addedOn") var addedOn: String? = null,
    @SerializedName("updatedOn") var updatedOn: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("locationId") var locationId: Int? = null,
    @SerializedName("deviceSerialNo") var deviceSerialNo: String? = null,
    @SerializedName("ticketSerialPrefix") var ticketSerialPrefix: String? = null,
    @SerializedName("notes") var notes: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("shifts") var shifts: ArrayList<String> = arrayListOf(),
    @SerializedName("notifications") var notifications: ArrayList<String> = arrayListOf(),
    @SerializedName("allDay") var allDay: String? = null,
    @SerializedName("overNight") var overNight: String? = null,
)
