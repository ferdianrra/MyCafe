package com.dicoding.mycafe.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dicoding.mycafe.core.data.source.local.enitity.CartEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getCart(): Flow<List<CartEntity>>

    @Insert
    suspend fun insertCart(cart: CartEntity)

    @Delete
    suspend fun deleteCart(cart: CartEntity)

    @Query("SELECT * FROM cart WHERE title LIKE :title")
    fun getCartByTitle(title: String): Flow<List<CartEntity>>

    @Query("UPDATE cart SET quantity = :newQuantity WHERE title = :title")
    suspend fun updateCartByTitle(title: String, newQuantity: Int)

    @Query("DELETE FROM cart")
    suspend fun deleteAllCarts()
}