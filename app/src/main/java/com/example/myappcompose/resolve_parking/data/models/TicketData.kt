package com.example.myappcompose.resolve_parking.data.models

import com.example.myappcompose.resolve_parking.utils.CommonUtil.toDateTimeToInputFormatString
import com.example.myappcompose.resolve_parking.utils.Constants.TIME_FORMAT_HH_MM
import com.google.gson.annotations.SerializedName


data class TicketData(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("licensePlateNo") var licensePlateNo: String? = null,
    @SerializedName("amount") var amount: Double? = null,
    @SerializedName("duration") var duration: String? = null,
    @SerializedName("durationType") var durationType: Int? = null,
    @SerializedName("generatedOn") var generatedOn: String? = null,
    @SerializedName("dueOn") var dueOn: String? = null,
    @SerializedName("closedOn") var closedOn: String? = null,
    @SerializedName("ticketSerialNo") var ticketSerialNo: String? = null,
    @SerializedName("durationStr") var durationStr: String? = null,
    @SerializedName("timeExceededAfterDue") var timeExceededAfterDue: String? = null,
    @SerializedName("isPaymentRequired") var isPaymentRequired: Boolean = false
) {


    fun getPrice(): String {
        return "$$amount"
    }

    fun getLicensePlateString(): String {
        return "Lic: $licensePlateNo"
    }

    fun getDurationString(): String {
        return if (durationStr.isNullOrEmpty()) ""
        else if (durationStr.equals("All Day")) {
            "Dur: AD"
        } else if (durationStr.equals("Overnight")) {
            "Dur: OVN"
        } else "Dur: ${durationStr?.replace("Hours", "Hrs")}"
    }

    fun getTimeString(): String {
        return "Time: ${generatedOn?.toDateTimeToInputFormatString(TIME_FORMAT_HH_MM)}"
    }

    var gracePeriodStatus = 0
    fun getGracePeriod() {
        gracePeriodStatus = if (closedOn.isNullOrEmpty()) if (isPaymentRequired) {
            if (!timeExceededAfterDue.isNullOrEmpty()) { // in case need to show for overtime
                android.graphics.Color.parseColor("#FFF44336") // red
            } else android.graphics.Color.parseColor("#FF04AA6D") // green
        } else {
            if (!timeExceededAfterDue.isNullOrEmpty()) {
                android.graphics.Color.parseColor("#FFFF9800") // yellow
            } else android.graphics.Color.parseColor("#FF04AA6D") // green
        }
        else {
            android.graphics.Color.parseColor("#FF04AA6D")
        }

    }
}