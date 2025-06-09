package com.dicoding.mycafe.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycafe.core.data.Resource
import com.dicoding.mycafe.core.domain.model.CartItemModel
import com.dicoding.mycafe.core.domain.usecase.CartUseCase
import com.dicoding.mycafe.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(val cartUseCase: CartUseCase): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartItemModel>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartItemModel>> get() = _uiState

    init {
        getCart()
    }
    fun getCart() {
        viewModelScope.launch {
            cartUseCase.getCart().collect{ resource ->
                _uiState.value = when(resource) {
                    is Resource.Error -> UiState.Error(resource.message.toString())
                    is Resource.Loading -> UiState.Loading
                    is Resource.Success -> UiState.Success(resource.data)
                }
            }
        }
    }

    fun deleteCart(data: CartItemModel) {
        viewModelScope.launch {
            cartUseCase.deleteCart(data)
            getCart()
        }
    }

    fun deleteAllCart() {
        viewModelScope.launch {
            cartUseCase.deleteAllCart()
            getCart()
        }
    }
}