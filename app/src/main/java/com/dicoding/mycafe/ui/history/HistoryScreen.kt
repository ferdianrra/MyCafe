package com.dicoding.mycafe.ui.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycafe.core.domain.model.OrderModel
import com.dicoding.mycafe.ui.common.UiState
import com.dicoding.mycafe.ui.component.OrderItem
import com.dicoding.mycafe.ui.component.ShowError
import com.dicoding.mycafe.ui.component.ShowLoading
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    val viewModel: HistoryViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.viewModelScope.launch {
            viewModel.getOrder()
        }
    }
    when(uiState) {
        is UiState.Error -> {
            ShowError {
                viewModel.viewModelScope.launch {
                    viewModel.getOrder()
                }
            }
        }
        UiState.Loading ->  {
            ShowLoading()
        }
        is UiState.Success -> {
            val list = (uiState as UiState.Success<List<OrderModel>>).data as List<OrderModel>
            if (list.isEmpty()) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No Activity",
                        fontSize = 24.sp,
                    )
                }
            } else {
                HistoryContent(
                    listHistory = list,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }

    }
}

@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    listHistory: List<OrderModel>,
) {

    Column(
        modifier = modifier.padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier.padding(top = 40.dp),
        ) {
            items(listHistory) { data ->
                OrderItem(
                    noTable = data.noTable,
                    isCompleted = data.isCompleted,
                    price = data.totalPrice,
                    date = data.date,
                )
            }
        }
    }
}