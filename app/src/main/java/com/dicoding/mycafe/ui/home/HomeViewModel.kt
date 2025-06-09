package com.dicoding.mycafe.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycafe.core.data.Resource
import com.dicoding.mycafe.core.domain.model.MenuItemModel
import com.dicoding.mycafe.core.domain.usecase.MenuUseCase
import com.dicoding.mycafe.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val menuUseCase: MenuUseCase): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<MenuItemModel>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<MenuItemModel>>> get() = _uiState

    init {
        viewModelScope.launch {
            getFood()
        }
    }

    suspend fun getFood() {
        _uiState.value = UiState.Loading
        menuUseCase.getMenu().collect{resource ->
            _uiState.value = when (resource) {
                is Resource.Success -> UiState.Success(resource.data)
                is Resource.Error -> UiState.Error(resource.message.toString())
                is Resource.Loading -> UiState.Loading
            }
        }
    }

}