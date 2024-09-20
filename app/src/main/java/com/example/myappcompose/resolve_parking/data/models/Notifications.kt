package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class Notifications(

    @SerializedName("addedOn") var addedOn: String? = null,
    @SerializedName("updatedOn") var updatedOn: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("ticketId") var ticketId: Int? = null,
    @SerializedName("deviceId") var deviceId: Int? = null,
    @SerializedName("notificationType") var notificationType: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("isRead") var isRead: Boolean? = null,
    @SerializedName("ticket") var ticket: String? = null,
    @SerializedName("device") var device: Device? = null

)