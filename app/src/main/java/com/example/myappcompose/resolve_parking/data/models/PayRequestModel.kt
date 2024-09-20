package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class PayRequestModel(

    @SerializedName("chargeableDuration") var chargeableDuration: String? = null,
    @SerializedName("isForTicketExtend") var isForTicketExtend: Boolean? = false,
    @SerializedName("extendedTicketDurationType") var extendedTicketDurationType: Int? = 0,
    @SerializedName("transactionDetail") var transactionDetail: TransactionDetailRequest? = TransactionDetailRequest()

)