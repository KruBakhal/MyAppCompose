package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class ErrorResponseModel(
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
    var stackTrace: String? = null
)
