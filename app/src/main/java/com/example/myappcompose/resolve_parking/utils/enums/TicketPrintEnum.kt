package com.example.myappcompose.resolve_parking.utils.enums

import com.google.gson.annotations.SerializedName

enum class TicketPrintEnum(val id: Int, val text: String) {
    @SerializedName("1")
    VALID(1, "Valid"),
    @SerializedName("2")
    INVALID(2, "Invalidated_ Due To Reprint"),
    @SerializedName("3")
    CLOSED(3, "Closed"),
    @SerializedName("4")
    VOIDED(4, "Voided")
}