package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class SalesLastCommonWeek(

    @SerializedName("shiftStartTicket") var shiftStartTicket: String? = null,
    @SerializedName("shiftEndTicket") var shiftEndTicket: String? = null,
    @SerializedName("shiftEndTime") var shiftEndTime: String? = null,
    @SerializedName("shiftEndTimeStr") var shiftEndTimeStr: String? = null,
    @SerializedName("shiftType") var shiftType: String? = null,
    @SerializedName("shiftDate") var shiftDate: String? = null,
    @SerializedName("ticketCount") var ticketCount: Int? = null,
    @SerializedName("valetCount") var valetCount: Int? = null,
    @SerializedName("selfParkingCount") var selfParkingCount: Int? = null,
    @SerializedName("totalAmount") var totalAmount: Double? = null,
    @SerializedName("totalCashAmount") var totalCashAmount: Double? = null,
    @SerializedName("totalCardAmount") var totalCardAmount: Double? = null,
    @SerializedName("avgAmountPerTicket") var avgAmountPerTicket: Double? = null,
    @SerializedName("totalTipAmount") var totalTipAmount: Double? = null,
    @SerializedName("totalTransactionFee") var totalTransactionFee: Double? = null,
    @SerializedName("shiftTotalOverTimeCars") var shiftTotalOverTimeCars: Int? = null,
    @SerializedName("shiftTotalOverTimeAmount") var shiftTotalOverTimeAmount: Double? = null,
    @SerializedName("totalVoidRefundCars") var totalVoidRefundCars: Int? = null,
    @SerializedName("totalVoidRefundAmount") var totalVoidRefundAmount: Double? = null,

)
