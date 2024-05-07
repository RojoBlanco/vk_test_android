package ru.vk.test.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class ProductsResponse (

    @SerializedName("products")
    @Expose
     val products: List<Product>? = null,

    @SerializedName("total")
    @Expose
     val total: Int? = null,

    @SerializedName("skip")
    @Expose
     val skip: Int? = null,

    @SerializedName("limit")
    @Expose
     val limit: Int? = null,
)