package com.dicoding.mycafe.core.data.source.local

import com.dicoding.mycafe.core.data.source.local.enitity.CartEntity
import com.dicoding.mycafe.core.data.source.local.room.CartDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val cartDao: CartDao){
    fun getCart(): Flow<List<CartEntity>> {
        return cartDao.getCart()
    }

    suspend fun insertCart(cart: CartEntity) {
        return cartDao.insertCart(cart)
    }

    suspend fun deleteCart(cart:CartEntity) {
        return cartDao.deleteCart(cart)
    }

    fun getCartByTitle(title: String): Flow<List<CartEntity>> {
        return cartDao.getCartByTitle(title)
    }

    suspend fun updateCartByTitle(title: String, quantity: Int) {
        return cartDao.updateCartByTitle(title, quantity)
    }
    suspend fun deleteAllCart() {
        return cartDao.deleteAllCarts()
    }

}