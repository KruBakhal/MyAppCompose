package com.example.myappcompose.resolve_parking.utils.enums

import com.google.gson.annotations.SerializedName

enum class TransactionModeEnum(val id: Int, val text: String) {
    @SerializedName("1")
    CASH(1, "Cash"),
    @SerializedName("2")
    CARD(2, "Card"),
    @SerializedName("3")
    APPLE_PAY(3, "Apple Pay"),
}