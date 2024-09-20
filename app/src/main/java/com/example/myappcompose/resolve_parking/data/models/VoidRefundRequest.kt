package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class VoidRefundRequest(
//    @SerializedName("ticketAmount") var ticketAmount: Double? = null,
//    @SerializedName("ticketStatus") var ticketStatus: Int? = null
    @SerializedName("voidReason") var voidReason: String? = null,
    @SerializedName("voidPassword") var voidPassword: String? = null,
    @SerializedName("isValidateUser") var isValidateUser: Boolean? = false

)