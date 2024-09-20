package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class ShiftSummary(
    @SerializedName("startedOn") var startedOn: String? = null,
    @SerializedName("activeDevices") var activeDevices: Int? = null,
    @SerializedName("totalTickets") var totalTickets: Int? = null,
    @SerializedName("openTickets") var openTickets: Int? = null,
    @SerializedName("voidTickets") var voidTickets: Int? = null,
    @SerializedName("closeTickets") var closeTickets: Int? = null,
    @SerializedName("closeManuallyTickets") var closeManuallyTickets: Int? = null,

)
