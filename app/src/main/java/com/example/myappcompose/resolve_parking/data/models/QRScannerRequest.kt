package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName


data class QRScannerRequest(
    @SerializedName("scannedQrContent") var scannedQrContent: String? = null,
    @SerializedName("ticketCloseType") var ticketCloseType: Int? = null
)