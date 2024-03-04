package com.example.example

import com.google.gson.annotations.SerializedName


data class ProductModel (

  @SerializedName("available_qty" ) var availableQty : String? = null,
  @SerializedName("cart_image"    ) var cartImage    : String? = null,
  @SerializedName("category_id"   ) var categoryId   : String? = null,
  @SerializedName("description"   ) var description  : String? = null,
  @SerializedName("detail_image"  ) var detailImage  : String? = null,
  @SerializedName("id"            ) var id           : String? = null,
  @SerializedName("is_featured"   ) var isFeatured   : String? = null,
  @SerializedName("is_latest"     ) var isLatest     : String? = null,
  @SerializedName("is_special"    ) var isSpecial    : String? = null,
  @SerializedName("main_image"    ) var mainImage    : String? = null,
  @SerializedName("name"          ) var name         : String? = null,
  @SerializedName("price"         ) var price        : String? = null,
  @SerializedName("status"        ) var status       : String? = null,
  @SerializedName("tax_class"     ) var taxClass     : String? = null,
  @SerializedName("unit"          ) var unit         : String? = null,
  @SerializedName("url"           ) var url          : String? = null,
  @SerializedName("weight"        ) var weight       : String? = null

)