package com.example.example

import com.google.gson.annotations.SerializedName


data class BrandCategory (

  @SerializedName("brandName"    ) var brandName    : String?                 = null,
  @SerializedName("categoryList" ) var categoryList : ArrayList<CategoryList> = arrayListOf()

)