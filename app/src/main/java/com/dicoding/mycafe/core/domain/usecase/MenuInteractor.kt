package com.dicoding.mycafe.core.domain.usecase

import com.dicoding.mycafe.core.data.Resource
import com.dicoding.mycafe.core.domain.model.MenuItemModel
import com.dicoding.mycafe.core.domain.model.OrderModel
import com.dicoding.mycafe.core.domain.repository.IMenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenuInteractor @Inject constructor(private val menuRepository: IMenuRepository): MenuUseCase{
    override fun getMenu(): Flow<Resource<List<MenuItemModel>>> {
        return menuRepository.getMenu()
    }

    override fun getOrder(): Flow<Resource<List<OrderModel>>> {
        return menuRepository.getOrder()
    }

    override suspend fun sendOrder(
        noTable: String,
        isCompleted: Boolean,
        totalPrice: Double,
        date: String
    ) {
        menuRepository.sendOrder(noTable, isCompleted, totalPrice, date)
    }
}