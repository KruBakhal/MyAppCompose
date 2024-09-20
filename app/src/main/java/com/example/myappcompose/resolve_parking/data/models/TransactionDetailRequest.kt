package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class TransactionDetailRequest(

    @SerializedName("ticketAmount") var ticketAmount: Double? = null,
    @SerializedName("tipAmount") var tipAmount: Double? = null,
    @SerializedName("transactionFee") var transactionFee: Double? = null,
    @SerializedName("transactionMode") var transactionMode: Int? = null,
    @SerializedName("transactionIdentifier") var transactionIdentifier: String? = null,
    @SerializedName("cardHolderName") var cardHolderName: String? = null,
    @SerializedName("cardNumber") var cardNumber: String? = null,
    @SerializedName("cardCompany") var cardCompany: String? = null,
    @SerializedName("transactionTime") var transactionTime: String? = null
)