package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class DeviceConfigValueResponse(
    val device: Device,
    @SerializedName("lastPrintedSerialNo") val lastPrintedSerialNo: String?,
    @SerializedName("lastPrintedSerialNoParent") val lastPrintedSerialNoParent: String?,
    @SerializedName("allDayTicketDueAt") val allDayTicketDueAt: String,
    @SerializedName("overnightTicketDueAt") val overnightTicketDueAt: String,
    @SerializedName("qrContentTemplate") val qrContentTemplate: String,
    @SerializedName("transactionFee") val transactionFee: Int = 0,
    @SerializedName("voidGraceTime") val voidGraceTime: Int = 0,
    @SerializedName("dayOfTheYear") val dayOfTheYear: String? = null,
    @SerializedName("allDay") val allDay: String? = null,
    @SerializedName("overNight") val overNight: String? = null
//    @SerializedName("shiftCloseHours") val shiftCloseHours: String? = null
)
