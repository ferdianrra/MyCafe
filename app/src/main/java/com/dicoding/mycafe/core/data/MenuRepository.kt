package com.dicoding.mycafe.core.data

import com.dicoding.mycafe.core.data.source.remote.RemoteDataSource
import com.dicoding.mycafe.core.data.source.remote.network.ApiResponse
import com.dicoding.mycafe.core.domain.model.MenuItemModel
import com.dicoding.mycafe.core.domain.model.OrderModel
import com.dicoding.mycafe.core.domain.repository.IMenuRepository
import com.dicoding.mycafe.core.utils.AppExecutors
import com.dicoding.mycafe.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.stream.Collectors.toList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val appExecutorrs: AppExecutors
): IMenuRepository{
    override fun getMenu(): Flow<Resource<List<MenuItemModel>>> {
        return remoteDataSource.getFoods().map { apiResponse ->
            when(apiResponse) {
                is ApiResponse.Success -> {
                    val data = apiResponse.data.values.toList()
                    val newList = DataMapper.mapResponseToDomain(data)
                    Resource.Success(newList)
                }
                is ApiResponse.Empty -> {
                    Resource.Error("Empty")
                }
                is ApiResponse.Error -> {
                    Resource.Error(apiResponse.errorMessage)
                }
            }
        }
    }

    override fun getOrder(): Flow<Resource<List<OrderModel>>> {
        return remoteDataSource.getOrder().map { apiResponse ->
            when(apiResponse) {
                is ApiResponse.Success -> {
                    val data = apiResponse.data.values.toList()
                    val newList = DataMapper.mapResponseOrderToDomain(data)
                    Resource.Success(newList)
                }
                is ApiResponse.Empty -> {
                    Resource.Error("Empty")
                }
                is ApiResponse.Error -> {
                    Resource.Error(apiResponse.errorMessage)
                }
            }
        }
    }

    override suspend fun sendOrder(
        noTable: String,
        isCompleted: Boolean,
        totalPrice: Double,
        date: String
    ) {
        remoteDataSource.sendOrder(noTable, isCompleted, totalPrice, date).collect() { apiResponse ->
            when(apiResponse) {
                is ApiResponse.Success -> {
                    val data = apiResponse.data
                    Resource.Success(data)
                }
                is ApiResponse.Empty -> {
                    Resource.Error("Empty")
                }
                is ApiResponse.Error -> {
                    Resource.Error(apiResponse.errorMessage)
                }
            }
        }
    }
}