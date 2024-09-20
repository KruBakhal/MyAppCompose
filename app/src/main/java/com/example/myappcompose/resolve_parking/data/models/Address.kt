package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class Address(

    @SerializedName("addedOn") val addedOn: String? = null,
    @SerializedName("updatedOn") val updatedOn: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("address1") val address1: String? = null,
    @SerializedName("address2") val address2: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("zip") val zip: String? = null,
    @SerializedName("locations") val locations: ArrayList<String> = arrayListOf()

)