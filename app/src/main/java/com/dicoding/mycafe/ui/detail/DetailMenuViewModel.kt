package com.dicoding.mycafe.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycafe.core.data.Resource
import com.dicoding.mycafe.core.domain.model.CartItemModel
import com.dicoding.mycafe.core.domain.usecase.CartUseCase
import com.dicoding.mycafe.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMenuViewModel @Inject constructor(val cartUseCase: CartUseCase): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<CartItemModel>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartItemModel>> get() = _uiState

    fun insertCart(cart: CartItemModel) {
        viewModelScope.launch {
            Log.d("DetailMenuViewModel", "insertCart called with: ${cart.title}")
            val cartItems = getCartByTitle(cart.title)

            when (cartItems) {
                is UiState.Error -> {
                    _uiState.value = UiState.Error(cartItems.errorMessage ?: "Error")
                    Log.d("DetailMenuViewModel", "Error: ${cartItems.errorMessage}")
                }
                is UiState.Loading -> {
                    _uiState.value = UiState.Loading
                    Log.d("DetailMenuViewModel", "Loading")
                }
                is UiState.Success -> {
                    Log.d("DetailMenuViewModel", "Success: ${cartItems.data}")
                    val existingCartItem = (cartItems.data as? List<CartItemModel>)?.firstOrNull { it.title == cart.title }

                    if (existingCartItem != null) {
                        val newQuantity = existingCartItem.quantity + cart.quantity
                        Log.d("DetailMenuViewModel", "Updating cart with new quantity: $newQuantity")
                        updateCart(cart.title, newQuantity)
                    } else {
                        Log.d("DetailMenuViewModel", "Inserting new item to cart")
                        cartUseCase.insertCart(cart)
                    }
                }
            }
        }
    }


    private suspend fun getCartByTitle(title: String): UiState<List<CartItemModel>> {
        return try {
            cartUseCase.getCartByTitle(title)
                .map { resource ->
                    when (resource) {
                        is Resource.Success -> UiState.Success(resource.data )
                        is Resource.Error -> UiState.Error(resource.message ?: "Error")
                        is Resource.Loading -> UiState.Loading
                    }
                }
                .first()
        } catch (e: Exception) {
            UiState.Error(e.message ?: "Exception")
        }
    }


    fun updateCart(title: String, quantity: Int) {
        viewModelScope.launch {
            cartUseCase.updateCartByTitle(title, quantity)
        }
    }
}