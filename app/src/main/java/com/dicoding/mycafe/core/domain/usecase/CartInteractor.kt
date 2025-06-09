package com.dicoding.mycafe.core.domain.usecase

import com.dicoding.mycafe.core.data.Resource
import com.dicoding.mycafe.core.domain.model.CartItemModel
import com.dicoding.mycafe.core.domain.repository.ICartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartInteractor @Inject constructor(private val cartRepository: ICartRepository): CartUseCase{
    override fun getCart(): Flow<Resource<List<CartItemModel>>> {
        return cartRepository.getCart()
    }

    override suspend fun insertCart(cartItemModel: CartItemModel) {
        return cartRepository.insertCart(cartItemModel)
    }

    override suspend fun deleteCart(cart: CartItemModel) {
        return cartRepository.deleteCart(cart)
    }

    override fun getCartByTitle(title: String): Flow<Resource<List<CartItemModel>>> {
        return cartRepository.getCartByTitle(title)
    }

    override suspend fun updateCartByTitle(title: String, quantity: Int) {
        return cartRepository.updateCartByTitle(title, quantity)
    }

    override suspend fun deleteAllCart() {
        cartRepository.deleteAllCart()
    }
}