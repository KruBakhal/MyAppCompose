package com.example.example

import com.google.gson.annotations.SerializedName


data class AppBrandData (

  @SerializedName("brandCategory" ) var brandCategory : ArrayList<BrandCategory> = arrayListOf()

)