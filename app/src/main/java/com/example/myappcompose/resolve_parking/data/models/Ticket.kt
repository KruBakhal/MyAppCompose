package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class Ticket(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("shiftId") var shiftId: Int? = null,
    @SerializedName("licensePlateNo") var licensePlateNo: String? = null,
    @SerializedName("ticketAmount") var ticketAmount: Double? = null,
    @SerializedName("duration") var duration: String? = null,
    @SerializedName("durationStr") var durationStr: String? = null,
    @SerializedName("durationType") var durationType: Int? = null,
    @SerializedName("isValet") var isValet: Boolean? = null,
    @SerializedName("notes") var notes: String? = null,
    @SerializedName("generatedOn") var generatedOn: String? = null,
    @SerializedName("dueOn") var dueOn: String? = null,
    @SerializedName("closedOn") var closedOn: String? = null,
    @SerializedName("isClosedManually") var isClosedManually: Boolean? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("closedType") var closedType: Int? = null,
    @SerializedName("shift") var shift: Shift? = Shift(),
    @SerializedName("ticketManualCloseLogs") var ticketManualCloseLogs: ArrayList<TicketManualCloseLogs> = arrayListOf(),
    @SerializedName("ticketPrints") var ticketPrints: ArrayList<TicketPrints> = arrayListOf(),
    @SerializedName("ticketTransactions") var ticketTransactions: ArrayList<TicketTransactions> = arrayListOf(),
    @SerializedName("notifications") var notifications: ArrayList<Notifications> = arrayListOf()

) {
    fun getDurationTime(): String {
        return if (durationStr.isNullOrEmpty())
            ""
        else if (durationStr.equals("All Day")) {
            durationStr.toString()
        } else if (durationStr.equals("Overnight")) {
            durationStr.toString()
        } else
            "${durationStr?.replace("Hours", "Hrs")}"
    }


    fun getTicketAmounts(): String {
        return "$$ticketAmount"
    }
}