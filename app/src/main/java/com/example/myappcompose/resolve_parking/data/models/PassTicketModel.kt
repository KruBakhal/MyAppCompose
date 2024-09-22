package com.example.myappcompose.resolve_parking.data.models

data class PassTicketModel(
    val licPlate: String,
    val otherInfo: String,
    val amount: Double,
    val tipAmount: Double = 0.0,
    val transactionAmount: Double = 0.0,
    val durationId: Int,
    val durationStr: String,
    val customRate: Boolean = false,
    val payOnExist: Boolean = false,
    val customRateModelStr: String?,
    val isValet: Boolean = true
) {
    fun getTotalPaidAmount(): String {
        return "$${amount + tipAmount + transactionAmount}"
    }
}