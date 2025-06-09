package com.dicoding.mycafe.core.data.source.remote.network

import com.dicoding.mycafe.core.data.source.remote.response.ItemMenu
import com.dicoding.mycafe.core.data.source.remote.response.OrderItem
import com.dicoding.mycafe.core.data.source.remote.response.SendResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("foods.json")
    suspend fun getFoods(): Response<Map<String, ItemMenu>>

    @GET("order.json")
    suspend fun getOrders(): Response<Map<String, OrderItem>>

    @POST("order.json")
    suspend fun sendOrders(@Body requestBody: OrderItem): Response<SendResponse>
}