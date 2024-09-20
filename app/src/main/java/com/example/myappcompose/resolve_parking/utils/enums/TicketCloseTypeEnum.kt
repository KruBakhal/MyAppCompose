package com.example.myappcompose.resolve_parking.utils.enums

import com.google.gson.annotations.SerializedName

enum class TicketCloseTypeEnum(val id: Int, val text: String) {
    @SerializedName("1")
    CLOSED(1, "Closed"),
    @SerializedName("2")
    MANUAL(2, "Manual"),
    @SerializedName("3")
    SCAN(3, "Scan"),
    @SerializedName("4")
    VOID(4, "Void"),
    @SerializedName("5")
    AUTO_CLOSE(5, "Auto Close")
}