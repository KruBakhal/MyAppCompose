package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class Shift(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("deviceId") var deviceId: Int? = null,
    @SerializedName("startedOn") var startedOn: String? = null,
    @SerializedName("endedOn") var endedOn: String? = null,
    @SerializedName("isAutoClosed") var isAutoClosed: Boolean? = null,
    @SerializedName("shiftGroup") var shiftGroup: String? = null,
    @SerializedName("shiftDate") var shiftDate: String? = null,
    @SerializedName("device") var device: Device? = null,
    @SerializedName("tickets") var tickets: ArrayList<String> = arrayListOf()

)