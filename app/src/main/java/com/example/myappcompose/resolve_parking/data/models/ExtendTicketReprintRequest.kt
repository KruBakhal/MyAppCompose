package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class ExtendTicketReprintRequest(

    @SerializedName("ticketSerialNo") var ticketSerialNo: String? = null,
    @SerializedName("printGuid") var printGuid: String? = null,
    @SerializedName("printedOn") var printedOn: String? = null

)