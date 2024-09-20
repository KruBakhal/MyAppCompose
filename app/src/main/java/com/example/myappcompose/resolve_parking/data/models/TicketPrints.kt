package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class TicketPrints(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("ticketId") var ticketId: Int? = null,
    @SerializedName("ticketSerialNo") var ticketSerialNo: String? = null,
    @SerializedName("printGuid") var printGuid: String? = null,
    @SerializedName("printInstanceStatus") var printInstanceStatus: Int? = null,
    @SerializedName("printedOn") var printedOn: String? = null,
    @SerializedName("ticket") var ticket: String? = null,
    @SerializedName("isPrinted") var isPrinted: Boolean? = false

)