package com.dicoding.mycafe.core.domain.usecase

import com.dicoding.mycafe.core.data.Resource
import com.dicoding.mycafe.core.domain.model.MenuItemModel
import com.dicoding.mycafe.core.domain.model.OrderModel
import kotlinx.coroutines.flow.Flow


interface MenuUseCase {
    fun getMenu(): Flow<Resource<List<MenuItemModel>>>
    fun getOrder(): Flow<Resource<List<OrderModel>>>
    suspend fun sendOrder(noTable: String, isCompleted: Boolean, totalPrice: Double, date: String)
}