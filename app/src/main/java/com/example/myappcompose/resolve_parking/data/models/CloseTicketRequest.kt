package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName
import retrofit2.http.Part


data class CloseTicketRequest(

    @SerializedName("ticketId") var ticketId: Int? = null,
    @SerializedName("note") var note: String? = null,
    @SerializedName("closedBy") var closedBy: String? = null,
    @Part("image") var image: String? = null

)