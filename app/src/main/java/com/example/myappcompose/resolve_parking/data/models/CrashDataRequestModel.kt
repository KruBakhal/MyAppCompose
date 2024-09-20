package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class CrashDataRequestModel(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("startDate") var startDate: String? = null,
    @SerializedName("crashDate") var crashDate: String? = null,
    @SerializedName("versionCode") var versionCode: String? = null,
    @SerializedName("versionName") var versionName: String? = null,
    @SerializedName("andriodVersion") var andriodVersion: String? = null,
    @SerializedName("deviceId") var deviceId: String? = null,
    @SerializedName("packageName") var packageName: String? = null,
    @SerializedName("reportId") var reportId: String? = null,
    @SerializedName("stackTrace") var stackTrace: String? = null,
    @SerializedName("deviceDetail") var deviceDetail: DeviceDetail? = null,
    @SerializedName("isSilent") var isSilent: Boolean? = null

)


data class DeviceDetail(

    @SerializedName("board") var board: String? = null,
    @SerializedName("device") var device: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("model") var model: String? = null,
    @SerializedName("iS_DEBUGGABLE") var iSDEBUGGABLE: Boolean? = null,
    @SerializedName("iS_EMULATOR") var iSEMULATOR: Boolean? = null

)