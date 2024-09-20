package com.example.myappcompose.resolve_parking.utils.enums

import com.google.gson.annotations.SerializedName

enum class TransactionReasonEnum(val id: Int, val text: String) {
    @SerializedName("1")
    NORMAL(1, "Normal Ticket Payment"),
    @SerializedName("2")
    OVERTIME(2, "Overtime"),
    @SerializedName("3")
    TOPUP(3, "Topup"),
    @SerializedName("4")
    EXTEND(4, "Extend"),
}