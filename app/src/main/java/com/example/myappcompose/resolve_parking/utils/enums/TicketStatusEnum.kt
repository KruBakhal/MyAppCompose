package com.example.myappcompose.resolve_parking.utils.enums

import com.google.gson.annotations.SerializedName

enum class TicketStatusEnum(val id: Int, val text: String) {
    @SerializedName("1")
    ACTIVE(1, "Active"),
    @SerializedName("2")
    INACTIVE(2, "Inactive"),
    @SerializedName("3")
    VOID(3, "Void"),
    @SerializedName("4")
    REFUND(4, "Refund")
}