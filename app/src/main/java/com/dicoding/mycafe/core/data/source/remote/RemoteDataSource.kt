package com.dicoding.mycafe.core.data.source.remote

import android.util.Log
import com.dicoding.mycafe.core.data.source.remote.network.ApiResponse
import com.dicoding.mycafe.core.data.source.remote.network.ApiService
import com.dicoding.mycafe.core.data.source.remote.response.ItemMenu
import com.dicoding.mycafe.core.data.source.remote.response.OrderItem
import com.dicoding.mycafe.core.data.source.remote.response.SendResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getFoods(): Flow<ApiResponse<Map<String, ItemMenu>>> {
        return flow {
            try {
                val response = apiService.getFoods()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(ApiResponse.Success(body))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                } else {
                    emit(ApiResponse.Error("Error: ${response.message()}"))

                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getOrder(): Flow<ApiResponse<Map<String, OrderItem>>> {
        return flow {
            try {
                val response = apiService.getOrders()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(ApiResponse.Success(body))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                } else {
                    emit(ApiResponse.Error("Error: ${response.message()}"))

                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun sendOrder(noTable: String, isCompleted: Boolean, totalPrice: Double, date: String): Flow<ApiResponse<SendResponse>> {
        val body = OrderItem(
            noTable = noTable,
            isCompleted = isCompleted,
            totalPrice = totalPrice,
            date = date
        )
        Log.e("RemoteDataSource", body.toString())

        return flow {
            try {
                val response = apiService.sendOrders(body)
                if (response.isSuccessful) {
                    Log.e("RemoteDataSource", "Order sent successfully.")
                    response.body()?.let {
                        emit(ApiResponse.Success(it))
                    } ?: emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Error("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
