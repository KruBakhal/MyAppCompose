package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class Location(

    @SerializedName("addedOn") var addedOn: String? = null,
    @SerializedName("updatedOn") var updatedOn: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("locationCode") var locationCode: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("addressId") var addressId: Int? = null,
    @SerializedName("allDay") var allDay: String? = null,
    @SerializedName("overNight") var overNight: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("showOnDashboard") var showOnDashboard: Boolean? = null,
    @SerializedName("devicePIN") var devicePIN: String? = null,
    @SerializedName("isValetEnabled") var isValetEnabled: Boolean? = null,
    @SerializedName("isRequestMyCarEnabled") var isRequestMyCarEnabled: Boolean? = null,
    @SerializedName("ticketGracePeriodMinutes") var ticketGracePeriodMinutes: Int? = null,
    @SerializedName("ticketTitle") var ticketTitle: String? = null,
    @SerializedName("ticketSubheader") var ticketSubHeader: String? = null,
    @SerializedName("ticketSubheader2") var ticketSubHeader2: String? = null,
    @SerializedName("isTipEnabled") var isTipEnabled: Boolean? = null,
    @SerializedName("address") var address: Address? = Address(),
    @SerializedName("devices") var devices: ArrayList<String> = arrayListOf(),
    @SerializedName("locationDurations") var locationDurations: ArrayList<LocationDuration> = arrayListOf(),
    @SerializedName("locationRates") var locationRates: ArrayList<LocationRate> = arrayListOf(),
    @SerializedName("locationTips") var locationTips: ArrayList<LocationTip> = arrayListOf(),
    @SerializedName("masterTimeZone") var masterTimeZone: MasterTimeZone= MasterTimeZone(),
    @SerializedName("timeZoneId") var timeZoneId: Int?= null

)
