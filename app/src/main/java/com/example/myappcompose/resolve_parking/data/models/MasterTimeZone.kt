package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class MasterTimeZone(

    @SerializedName("timeZoneId") var timeZoneId: Int? = null,
    @SerializedName("timeZoneIdentifier") var timeZoneIdentifier: String? = null,
    @SerializedName("displayName") var displayName: String? = null,
    @SerializedName("isDST") var isDST: Boolean? = null,
    @SerializedName("offset") var offset: Int? = null

)