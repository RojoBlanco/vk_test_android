package ru.vk.test.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("id")
    @Expose
     val id: Int? = null,

    @SerializedName("title")
    @Expose
     val title: String? = null,

    @SerializedName("description")
    @Expose
     val description: String? = null,

    @SerializedName("price")
    @Expose
     val price: Int? = null,

    @SerializedName("discountPercentage")
    @Expose
     val discountPercentage: Double? = null,

    @SerializedName("rating")
    @Expose
     val rating: Double? = null,

    @SerializedName("stock")
    @Expose
     val stock: Int? = null,

    @SerializedName("brand")
    @Expose
     val brand: String? = null,

    @SerializedName("category")
    @Expose
     val category: String? = null,

    @SerializedName("thumbnail")
    @Expose
     val thumbnail: String? = null,

    @SerializedName("images")
    @Expose

     val images: List<String>? = null,
)
