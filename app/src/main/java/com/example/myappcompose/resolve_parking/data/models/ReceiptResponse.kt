package com.example.myappcompose.resolve_parking.data.models

data class ReceiptResponse(
    val ticketSerialNo: String,
    val customerSlip: PrintStubDetail?,
    val valetWidowStub: PrintStubDetail?,
    val valetKeyStub: PrintStubDetail?,
    val paymentReceipt: PrintStubDetail?
)