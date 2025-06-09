package com.dicoding.mycafe.core.domain.repository

import com.dicoding.mycafe.core.data.Resource
import com.dicoding.mycafe.core.domain.model.CartItemModel
import kotlinx.coroutines.flow.Flow

interface ICartRepository {

    fun getCart(): Flow<Resource<List<CartItemModel>>>
    suspend fun insertCart(cart: CartItemModel)
    suspend fun deleteCart(cart: CartItemModel)
    fun getCartByTitle(title: String): Flow<Resource<List<CartItemModel>>>
    suspend fun updateCartByTitle(title: String, quantity: Int)
    suspend fun deleteAllCart()
}