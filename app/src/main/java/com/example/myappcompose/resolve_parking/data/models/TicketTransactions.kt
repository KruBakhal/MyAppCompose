package com.example.myappcompose.resolve_parking.data.models

import com.example.myappcompose.resolve_parking.utils.CommonUtil.toDateTimeToInputFormatString
import com.example.myappcompose.resolve_parking.utils.ResolveParkingConstants.DATE_TIME_FORMAT_MMM_DD_YYYY_HH_MM
import com.google.gson.annotations.SerializedName
import com.example.myappcompose.resolve_parking.utils.enums.TransactionModeEnum
import java.text.DecimalFormat

data class TicketTransactions(

    @SerializedName("addedOn") var addedOn: String? = null,
    @SerializedName("updatedOn") var updatedOn: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("ticketId") var ticketId: Int? = null,
    @SerializedName("ticketAmount") var ticketAmount: Double = 0.0,
    @SerializedName("tipAmount") var tipAmount: Double = 0.0,
    @SerializedName("transactionFee") var transactionFee: Double = 0.0,
    @SerializedName("duration") var duration: String? = null,
    @SerializedName("transactionReason") var transactionReason: Int = 0,
    @SerializedName("transactionMode") var transactionMode: Int? = null,
    @SerializedName("transactionIdentifier") var transactionIdentifier: String? = null,
    @SerializedName("transactionTime") var transactionTime: String? = null,
    @SerializedName("cardHolderName") var cardHolderName: String? = null,
    @SerializedName("cardNumber") var cardNumber: String? = null,
    @SerializedName("cardCompany") var cardCompany: String? = null,
    @SerializedName("ticket") var ticket: String? = null,
    @SerializedName("isOverTime") var isOverTime: Boolean = false,

    ) {
    fun getTransactionModes(): String {
        when (transactionMode) {
            1 -> {
                return TransactionModeEnum.CASH.text
            }

            2 -> {
                return TransactionModeEnum.CARD.text
            }

            3 -> {
                return TransactionModeEnum.APPLE_PAY.text
            }

            else -> {
                return transactionMode.toString()
            }
        }
    }

    fun getTicketAmounts(): String {
        return "$${
            DecimalFormat("0.00").format((ticketAmount + tipAmount + transactionFee))
        }"
    }

    fun getTimeDateFormat(): String {
        return "${transactionTime?.toDateTimeToInputFormatString(DATE_TIME_FORMAT_MMM_DD_YYYY_HH_MM)}"
    }
}