package ru.vk.test.api



import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.vk.test.model.ProductsResponse

interface ApiService {
    @GET("products")
     fun getProducts(
        @Query("limit") limit: Int = 20 ,
        @Query("skip") skip: Int = 0,

    ): Single<ProductsResponse>

}