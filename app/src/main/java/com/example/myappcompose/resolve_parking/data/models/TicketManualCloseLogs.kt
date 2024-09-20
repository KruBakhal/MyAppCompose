package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class TicketManualCloseLogs(

    @SerializedName("addedOn") var addedOn: String? = null,
    @SerializedName("updatedOn") var updatedOn: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("ticketId") var ticketId: Int? = null,
    @SerializedName("note") var note: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("ticket") var ticket: String? = null

)