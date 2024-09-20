package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class LocationRateCustomizations(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("locationRateId") var locationRateId: Int? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("duration") var duration: String? = null,
    @SerializedName("note") var note: String? = null,
    @SerializedName("paidOnExit") var paidOnExit: Boolean? = null,
    @SerializedName("addedOn") var addedOn: String? = null,
    @SerializedName("updatedOn") var updatedOn: String? = null

)