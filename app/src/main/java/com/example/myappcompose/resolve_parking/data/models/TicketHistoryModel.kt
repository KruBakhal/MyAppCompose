package com.example.myappcompose.resolve_parking.data.models

import com.google.gson.annotations.SerializedName

data class TicketHistoryModel(

  @SerializedName("resultData") var resultData: ArrayList<TicketData> = arrayListOf(),
  @SerializedName("rowCount") var rowCount: Int? = null,
  @SerializedName("pageIndex") var pageIndex: Int? = null,
  @SerializedName("pageSize") var pageSize: Int? = null,
  @SerializedName("hasMoreRecord") var hasMoreRecord: Boolean? = null,
  @SerializedName("totalPages") var totalPages: Int? = null,
  @SerializedName("startRow") var startRow: Int? = null,
  @SerializedName("endRow") var endRow: Int? = null

)