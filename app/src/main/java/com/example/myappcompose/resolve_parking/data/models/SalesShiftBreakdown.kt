package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class SalesShiftBreakdown(

    @SerializedName("ticketAmount") var ticketAmount: Double? = null,
    @SerializedName("ticketCount") var ticketCount: Int? = null,
    @SerializedName("valetCount") var valetCount: Int? = null,
    @SerializedName("selfParkingCount") var selfParkingCount: Int? = null,
    @SerializedName("totalRevenue") var totalRevenue: Double? = null,
    @SerializedName("totalCashAmount") var totalCashAmount: Double? = null,
    @SerializedName("totalCardAmount") var totalCardAmount: Double? = null,
    @SerializedName("totalTipAmount") var totalTipAmount: Double? = null,
    @SerializedName("totalTransactionFee") var totalTransactionFee: Double? = null,
    @SerializedName("avgAmountPerTicket") var avgAmountPerTicket: Double? = null,
    @SerializedName("totalReprints") var totalReprints: Double? = null

) {
    fun getValueTotalRevenue(): String {
        return "$${totalRevenue}"
    }
    fun getValueTotalCashAmount(): String {
        return "$${totalCashAmount}"
    }
    fun getValueTotalCardAmount(): String {
        return "$${totalCardAmount}"
    }
    fun getValueTotalTipAmount(): String {
        return "$${totalTipAmount}"
    }
    fun getValueTotalTransactionFee(): String {
        return "$${totalTransactionFee}"
    }
    fun getValueAvgAmountPerTicket(): String {
        return "$${avgAmountPerTicket}"
    }
    fun getValueTicketAmount(): String {
        return "$${ticketAmount}"
    }
    fun getValueTotalReprint(): String {
        return "${totalReprints?.toInt()?:0}"
    }
}
