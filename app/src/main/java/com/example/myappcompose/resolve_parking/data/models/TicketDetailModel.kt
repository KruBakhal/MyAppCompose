package com.example.myappcompose.resolve_parking.data.models

import android.content.Context
import com.example.myappcompose.R
import com.google.gson.annotations.SerializedName
import com.example.myappcompose.resolve_parking.utils.enums.TicketCloseTypeEnum
import com.example.myappcompose.resolve_parking.utils.enums.TicketStatusEnum

data class TicketDetailModel(

    @SerializedName("ticket") var ticket: Ticket? = Ticket(),
    @SerializedName("timeExceededAfterDue") var timeExceededAfterDue: String? = null,
    @SerializedName("isPaymentRequired") var isPaymentRequired: Boolean = false,
    @SerializedName("isTicketClosed") var isTicketClosed: Boolean = false

) {
    fun getLatestTicketSerialNos(): String {
        return if (ticket?.ticketPrints?.size != 0) {
            ticket?.ticketPrints?.last()?.ticketSerialNo!!
        } else {
            ""
        }
    }

    fun isPrinted(): Boolean? {
        return if (ticket?.ticketPrints?.size != 0) {
            ticket?.ticketPrints?.last()?.isPrinted
        } else {
            true
        }
    }

    fun getTicketStatus(context: Context): String {
        return if (ticket != null) {
            when (ticket!!.status) {
                TicketStatusEnum.ACTIVE.id -> {
                    if (ticket != null && ticket!!.isClosedManually == true) {
                        return context.resources.getString(R.string.closed_manually_str)
                    } else if (isTicketClosed) {
                        return context.resources.getString(R.string.closed_str)
                    } else
                        return context.resources.getString(R.string.opened_str)
                }

                TicketStatusEnum.INACTIVE.id -> {
                    if (ticket!!.closedType == TicketCloseTypeEnum.AUTO_CLOSE.id) {
                        return context.resources.getString(R.string.closed_auto_str)
                    } else if (ticket!!.closedType == TicketCloseTypeEnum.SCAN.id) {
                        return context.resources.getString(R.string.closed_by_scan_str)
                    } else if (ticket!!.isClosedManually == true) {
                        return context.resources.getString(R.string.closed_manually_str)
                    } else
                        return context.resources.getString(R.string.closed_str)

                }

                TicketStatusEnum.VOID.id -> {
                    context.resources.getString(R.string.void_str)
                }

                TicketStatusEnum.REFUND.id -> {
                    context.resources.getString(R.string.refund_str)
                }

                else -> {
                    ""
                }
            }
        } else {
            ""
        }

    }

    fun getExceededTimeDuration(): String {
        val timeParts: List<String> = timeExceededAfterDue!!.split(":")
        val hours = timeParts[0].toInt()
        val minutes = timeParts[1].toInt()
//        val seconds = timeParts[2].toInt()
        return "$hours hrs $minutes min"
    }
}