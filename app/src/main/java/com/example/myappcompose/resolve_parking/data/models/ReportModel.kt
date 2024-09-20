package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class ReportModel(

    @SerializedName("salesLast4Week") var salesLast4Week: SalesLastCommonWeek? = SalesLastCommonWeek(),
    @SerializedName("salesPreviousShift") var salesPreviousShift: SalesLastCommonWeek? = SalesLastCommonWeek(),
    @SerializedName("salesCurrentShift") var salesCurrentShift: SalesLastCommonWeek? = SalesLastCommonWeek(),
    @SerializedName("salesCurrentShiftOverTimeBreakdown") var salesCurrentShiftOverTimeBreakdown: ArrayList<SalesShiftBreakdown> = arrayListOf(),
    @SerializedName("salesCurrentShiftBreakdown") var salesCurrentShiftBreakdown: ArrayList<SalesShiftBreakdown> = arrayListOf(),
    @SerializedName("shiftSalesTransactionDetail" ) var shiftSalesTransactionDetail : ArrayList<ShiftSalesTransactionDetail> = arrayListOf()
)
