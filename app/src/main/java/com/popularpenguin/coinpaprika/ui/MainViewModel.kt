package com.popularpenguin.coinpaprika.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popularpenguin.coinpaprika.api.ApiService
import com.popularpenguin.coinpaprika.data.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val apiService: ApiService
) : ViewModel() {
    var uiState = MutableStateFlow<List<Currency>>(listOf())
        private set

    init {
        viewModelScope.launch {
            while (true) {
                getTickerData()
                delay(10_000L) // update every 10 seconds
            }
        }
    }

    private suspend fun getTickerData() {
        uiState.value = apiService.getTicker()
    }
}