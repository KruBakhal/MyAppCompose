package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TicketRequest(
    @SerializedName("shiftId") val shiftId: Int?,
    @SerializedName("licensePlateNo") val licensePlateNo: String,
    @SerializedName("ticketAmount") val ticketAmount: Double,
    @SerializedName("dueOn") val dueOn: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("durationType") val durationType: Int?=null,
    @SerializedName("isValet") val isValet: Boolean,
    @SerializedName("notes") val notes: String,
    @SerializedName("generatedOn") val generatedOn: String,
    @SerializedName("status") val status: Int,
    @SerializedName("ticketPrints") val ticketPrints: List<TicketPrints>,
    @SerializedName("ticketTransactions") val ticketTransactions: List<TicketTransactions>
) :Serializable{
    var printedSerialNo: String?=null
    var durationStr: String?=null
    @SerializedName("tipAmount") var tipAmount: Double = 0.0
    @SerializedName("transactionFee") var transactionFee: Double = 0.0
}
