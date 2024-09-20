package com.example.myappcompose.resolve_parking.data.models

data class CardPaymentModel(
    val result: Boolean=false,
    val transactionIdentifier: String?,
    val cardHolderName: String?,
    val transactionTime: Long?,
    val failedReason: String?
)