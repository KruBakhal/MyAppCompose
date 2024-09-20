package com.example.myappcompose.resolve_parking.utils

import com.example.myappcompose.resolve_parking.data.models.QRScannerRequest
import com.example.myappcompose.resolve_parking.data.models.Ticket
import com.example.myappcompose.resolve_parking.utils.enums.DurationType
import com.example.myappcompose.resolve_parking.utils.enums.TicketStatusEnum

object Constants {
    var IS_PRODUCTION: Boolean = false
    val CLOVER_APP_ID: String = if (IS_PRODUCTION) "JKYNSY9GMYJYG" else "5C4GBW0D4B58T"
    var TIMEZONEIDENTIFIER: String = "America/Los_Angeles"
    const val SHARED_PREFERENCES_NAME = "resolveParkingAppPreferences"
    const val LOGIN_DATE = "loginDate"
    const val IS_SHIFT_KEY = "isShiftStarted"
    const val TICKET_LIST_KEY = "ticketList"
    const val AUTH_TOKEN_KEY = "authToken"
    const val SHIFT_ID_KEY = "shiftId"
    const val DEVICE_SERIAL_NO_KEY = "deviceSerialNo"
    val BASE_URL =
        if (IS_PRODUCTION) "https://api.resolveparking.com/" else "https://api-dev.resolveparking.com/"
    const val EMULATOR_SERIAL_NO = "EMULATOR31X3X14X0"
    const val EMULATOR_SERIAL_NO_2 = "EMULATOR31X3X14X1"
    const val UNKNOWN = "UNKNOWN"
    const val IS_CLOVER_DEVICE = "isCloverDevice"
    const val TICKET_ID = "ticketID"
    const val TICKET_AMOUNT = "ticketAmount"
    const val TOTAL_PAID = "totalPaid"
    const val TICKET_DURATION_ID = "ticketDurationId"
    const val TICKET_VEHICLE_NO = "vehicleNo"
    const val TICKET_VEHICLE_OTHER_INFO = "vehicleOtherInfo"
    const val TICKET_LAST_SERIAL_NOS = "ticketLastSerialNos"
    const val TICKET_CUSTOM_LOCATION_RATE = "customRateItem"
    const val TICKET_CUSTOM_RATE = "isCustomRate"
    const val TICKET_SALE_TYPE = "saleType"
    const val TICKET_PAYMENT_TYPE = "paymentType"
    const val TICKET_PAYMENT_MODEL = "paymentModel"
    const val TICKET_PAY_ON_EXIT = "payOnExist"
    const val TICKET_TIPS_AMOUNT = "amountTip"
    const val DEVICE_CONFIG_VALUE_KEY = "deviceConfigValue"
    const val LAST_PRINTED_SERIAL_NO_KEY = "lastPrintedSerialNo"
    const val QRContentTemplate = "qrContentTemplate"
    const val ManuallyTicketClose = "manuallyTicketClose"
    const val QRSCANNERFLOW = "isQRScannerFlow"
    const val CLOSE_TICKET_FLOW = "closeTicketFlow"
    const val IS_TICKET_DETAIL = "isTicketDetail"
    const val IS_VALLET = "isValletEnable"
    const val IS_SELF = "isSelf"
    const val ISTip = "isTip"
    const val IS_PAY_ON_EXIST = "isPayOnExist"
    const val PAYMENT_FLOW_TYPE = "paymentFlowType"
    const val TRANSACTION_IDENTIFIER = "transactionIdentifier"
    const val PAYMENT_CALLBACK = "paymentCallback"
    const val PAYMENT_CALLBACK_REASON = "paymentCallbackReason"
    const val BROADCAST_MSG = "broadcastMsg"
    const val BROADCAST_ACTION = "SYNC_OFFLINE_TICKET"
    const val LICENSE_PLATE_NOS_MAX_LENGTH = 4
    const val CONST_AUTHENTICATION_FAILED = 153
    const val VOID_FLOW_TYPE = "VOID"
    const val REFUND_FLOW_TYPE = "REFUND"
    const val MAX = "SYNC_OFFLINE_TICKET"
    const val DATE_TIME_TIMEZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX"
    const val DATE_TIME_UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    const val DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_A = "yyyy/MM/dd hh:mm a"
    const val DATE_TIME_FORMAT_E_MMM_DD_YYYY = "E, MMM dd yyyy"
    const val DATE_TIME_FORMAT_DD_YYYY_HH_MM_SS = "dd MMM yyyy HH:mm:ss"
    const val DATE_TIME_FORMAT_MMM_DD_YYYY_HH_MM = "MMM dd yyyy HH:mm"
    const val DATE_TIME_FORMAT_MMM_DD_YYYY = "MM/dd/yyyy"
    const val TIME_FORMAT_HH_MM = "HH:mm"
    const val TIME_FORMAT_HH_MM_SS = "HH:mm:ss"
    val activeStatus = TicketStatusEnum.ACTIVE
    val inactiveStatus = TicketStatusEnum.INACTIVE
    val ticketList = mutableListOf<Ticket>()
    val normalDuration = DurationType.Normal
    val allDayDuration = DurationType.AllDay
    val overnightDuration = DurationType.Overnight
    val priceList = arrayOf(
        "5", "10", "15", "20",
        "25", "30", "35", "40",
        "45", "50", "55", "60",
    )

    // TESTING constants
     var lastPaymentId: String? = null
     var lastExternalPaymentId1: String? = null
     var lastOrderId: String? = null
     var lastUpdateTime: String? = null
}