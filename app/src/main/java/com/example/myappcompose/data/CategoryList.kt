package com.example.example

import com.google.gson.annotations.SerializedName


data class CategoryList (

  @SerializedName("category_id"       ) var categoryId      : String?                = null,
  @SerializedName("category_name"     ) var categoryName    : String?                = null,
  @SerializedName("product_list"      ) var productList     : ArrayList<ProductList> = arrayListOf(),
  @SerializedName("sub_category_name" ) var subCategoryName : String?                = null

)