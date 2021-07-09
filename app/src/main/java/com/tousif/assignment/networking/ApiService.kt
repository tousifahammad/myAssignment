package com.tousif.assignment.networking

import com.tousif.assignment.data.models.ResponseData
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("60b69209c75d5115e064bdde")
    suspend fun getProducts(): Response<ResponseData>

}