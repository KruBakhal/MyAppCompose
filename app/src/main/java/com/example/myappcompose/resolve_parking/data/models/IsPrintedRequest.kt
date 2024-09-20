package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class IsPrintedRequest(
    @SerializedName("ticketSerialNo") var ticketSerialNo: String? = null,
    @SerializedName("isPrinted") var isPrinted: Boolean? = true

)