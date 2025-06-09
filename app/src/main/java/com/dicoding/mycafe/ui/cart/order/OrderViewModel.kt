package com.dicoding.mycafe.ui.cart.order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycafe.core.data.Resource
import com.dicoding.mycafe.core.data.source.remote.network.ApiResponse
import com.dicoding.mycafe.core.data.source.remote.response.SendResponse
import com.dicoding.mycafe.core.domain.model.CartItemModel
import com.dicoding.mycafe.core.domain.usecase.MenuUseCase
import com.dicoding.mycafe.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(val menuUseCase: MenuUseCase): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<SendResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<SendResponse>> get() = _uiState

    fun sendOrder(noTable: String, isCompleted: Boolean, totalPrice: Double, date: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading // Set state to Loading initially
            try {
                // Panggil sendOrder dari menuUseCase
                val response = menuUseCase.sendOrder(noTable, isCompleted, totalPrice, date)
                Log.e("OrderViewModel", response.toString())
                _uiState.value = UiState.Success(response) // Jika berhasil, set ke Success
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error occurred") // Jika error, set Error
            }
        }
    }
}