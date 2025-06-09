@file:Suppress("UNCHECKED_CAST")

package com.dicoding.mycafe.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycafe.core.domain.model.MenuItemModel
import com.dicoding.mycafe.ui.common.UiState
import com.dicoding.mycafe.ui.detail.DialogContent
import com.dicoding.mycafe.ui.component.MenuItem
import com.dicoding.mycafe.ui.component.SearchBar
import com.dicoding.mycafe.ui.component.ShowError
import com.dicoding.mycafe.ui.component.ShowLoading
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    paddingValues: PaddingValues
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    when(uiState) {
        is UiState.Error -> {
            ShowError(onClick = {
                viewModel.viewModelScope.launch { viewModel.getFood() }
            })
        }
        UiState.Loading -> {
            ShowLoading()
        }
        is UiState.Success -> {
            HomeContent(
                listMenu = (uiState as UiState.Success<List<MenuItemModel>>).data as List<MenuItemModel>,
                navigateToDetail = navigateToDetail,
                modifier = modifier,
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
fun HomeContent(
    paddingValues: PaddingValues,
    listMenu: List<MenuItemModel>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedMenuItem by remember { mutableStateOf<MenuItemModel?>(null) }
    if (showDialog) {
        selectedMenuItem?.let { DialogContent(
            data = it, onDismiss = {showDialog = false}
        ) }
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues( start = 16.dp, end = 16.dp, bottom = 30.dp ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(span = { GridItemSpan(2) }) { // Menggunakan span untuk membuat SearchBar mengambil dua kolom
            SearchBar(onSearch = {})
        }
        items(listMenu) { data ->
            MenuItem(
                title = data.name,
                price = data.price,
                rating = data.rating,
                imageUrl = data.imageUrl,
                modifier = modifier.clickable {
                    selectedMenuItem = data
                    showDialog = true
                }
            )
        }
    }
}
