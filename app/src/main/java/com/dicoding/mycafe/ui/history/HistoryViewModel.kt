package com.dicoding.mycafe.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycafe.core.data.Resource
import com.dicoding.mycafe.core.domain.model.CartItemModel
import com.dicoding.mycafe.core.domain.usecase.MenuUseCase
import com.dicoding.mycafe.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(val menuUseCase: MenuUseCase): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartItemModel>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartItemModel>> get() = _uiState
    init {
        getOrder()
    }

    fun getOrder() {
        viewModelScope.launch {
            menuUseCase.getOrder().collect{ resource ->
                _uiState.value = when(resource) {
                    is Resource.Error -> UiState.Error(resource.message.toString())
                    is Resource.Loading -> UiState.Loading
                    is Resource.Success -> UiState.Success(resource.data)
                }
            }
        }
    }
}