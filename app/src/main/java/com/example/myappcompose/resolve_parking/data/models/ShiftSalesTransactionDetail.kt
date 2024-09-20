package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName
import com.example.myappcompose.resolve_parking.utils.enums.DurationType
import com.example.myappcompose.resolve_parking.utils.enums.TicketStatusEnum
import com.example.myappcompose.resolve_parking.utils.enums.TransactionReasonEnum

data class ShiftSalesTransactionDetail(

    @SerializedName("ticketNumber") var ticketNumber: String? = null,
    @SerializedName("time") var time: String? = null,
    @SerializedName("duration") var duration: Long = 0L,
    @SerializedName("licensePlateNo") var licensePlateNo: String? = null,
    @SerializedName("amount") var amount: Double? = null,
    @SerializedName("parkingType") var parkingType: String? = null,
    @SerializedName("timeStr") var timeStr: String? = null,
    @SerializedName("durationStr") var durationStr: String? = null,
    @SerializedName("durationType") var durationType: Int? = null,
    @SerializedName("transactionReason") var transactionReason: Int? = null,
    @SerializedName("status") var status: Int? = null

) {
    fun getDurationString(): String {
        if (durationType == null)
            return ""
        else if (durationType == DurationType.AllDay.id) {
            return "Dur: AD"
        } else if (durationType == DurationType.Overnight.id) {
            return "Dur: OVN"
        } else
            return "Dur: ${durationStr?.replace("Hours", "Hrs")}"
    }

    fun getPrice(): String {
        return "$$amount"
    }

    fun getLicensePlateString(): String {
        return "Lic: $licensePlateNo"
    }

    fun getParkingTypes(): String {
        return if (parkingType.equals("SP")) "Self Parking" else "Valet Parking"
    }


    fun getTimeString(): String {
        return "Time: ${timeStr}"
    }

    fun getTicketStatus(): String {
        var type = when (transactionReason) {
            TransactionReasonEnum.EXTEND.id -> "EX"
            TransactionReasonEnum.OVERTIME.id -> "OT"
            else -> ""
        }
        if (status == TicketStatusEnum.VOID.id) {
            type = "VD"
        }
        return type
    }

    var statusColorCode =0

    fun getStatusColorCode() {
        statusColorCode = when (transactionReason) {
            TransactionReasonEnum.EXTEND.id -> android.graphics.Color.parseColor("#FF2196F3") // blue
            TransactionReasonEnum.OVERTIME.id -> android.graphics.Color.parseColor("#FFF44336") // red
            else -> 0 // normal ticket
        }
        if (status == TicketStatusEnum.VOID.id) {
            statusColorCode = android.graphics.Color.parseColor("#FFFF9800") // yellow
        }
    }
}