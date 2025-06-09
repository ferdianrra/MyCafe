package com.dicoding.mycafe.core.data

import com.dicoding.mycafe.core.data.source.local.LocalDataSource
import com.dicoding.mycafe.core.domain.model.CartItemModel
import com.dicoding.mycafe.core.domain.repository.ICartRepository
import com.dicoding.mycafe.core.utils.AppExecutors
import com.dicoding.mycafe.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): ICartRepository{
    override fun getCart(): Flow<Resource<List<CartItemModel>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val listCart = localDataSource.getCart().first()
                val cartMapper = DataMapper.mapEntityToDomain(listCart)
                emit(Resource.Success(cartMapper))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Error Occurred"))
            }
        }
    }

    override suspend fun insertCart(cart: CartItemModel) {
        val cartMap = DataMapper.mapDomainToEntity(cart)
        localDataSource.insertCart(cartMap)
    }

    override suspend fun deleteCart(cart: CartItemModel) {
        val cartMap = DataMapper.mapDomainToEntity(cart)
        localDataSource.deleteCart(cartMap)
    }

    override fun getCartByTitle(title: String): Flow<Resource<List<CartItemModel>>> {
        return localDataSource.getCartByTitle(title)
            .map { listCart ->
                val cartMapper = DataMapper.mapEntityToDomain(listCart)
                Resource.Success(cartMapper)
            }
    }

    override suspend fun updateCartByTitle(title: String, quantity: Int) {
        localDataSource.updateCartByTitle(title, quantity)
    }

    override suspend fun deleteAllCart() {
        localDataSource.deleteAllCart()
    }
}