package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName


data class CloseTicketVehicleDetailModel(

    @SerializedName("message") var message: String? = null, // for close ticket
    @SerializedName("url") var url: String? = null, // for image upload
    // error model
    @SerializedName("title") var title: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("detail") var detail: String? = null,
    @SerializedName("messages")
    var messages: ArrayList<String> = arrayListOf(),

    @SerializedName("source")
    var source: String? = null,

    @SerializedName("exception")
    var exception: String? = null,

    @SerializedName("errorId")
    var errorId: String? = null,

    @SerializedName("supportMessage")
    var supportMessage: String? = null,

    @SerializedName("statusCode")
    var statusCode: Int? = null,

    @SerializedName("stackTrace")
    var stackTrace: String? = null,



)