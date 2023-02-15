package com.popularpenguin.coinpaprika.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.popularpenguin.coinpaprika.data.Currency

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val data by viewModel.uiState.collectAsState()

    var selectedCurrency: Currency by remember { mutableStateOf(Currency()) }
    val setSelection: (Currency) -> Unit = { currency -> selectedCurrency = currency }

    Column(
        modifier = Modifier
    ) {
        CurrencyList(
            currencyList = data,
            onCurrencySelected = setSelection,
            height = 1f
        )
        QuoteBox(
            currency = selectedCurrency,
            height = 1f
        )
    }
}

@Composable
fun CurrencyList(
    currencyList: List<Currency>,
    onCurrencySelected: (Currency) -> Unit,
    height: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            items(currencyList) { currency ->
                ListItem(currency, onCurrencySelected, height)
            }
        }
    }
}

@Composable
fun ListItem(currency: Currency, onCurrencySelected: (Currency) -> Unit, height: Float) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(height)
            .clickable { onCurrencySelected(currency) },
        text = "${currency.name} (${currency.symbol})",
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun QuoteBox(currency: Currency, height: Float) {
    val currentQuotes = currency.quotes.usd // set this according to user's preference

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(height)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            if (currency.id.isEmpty()) {
                Text(
                    text = "No currency selected",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            } else {
                Text(
                    text = "${currency.name} (${currency.symbol})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(text = "Rank: ${currency.rank}")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Price: ${currentQuotes.price}")
                Text(text = "Market Cap: ${currentQuotes.marketCap}")
                Text(text = "Market Cap Change 24h: ${currentQuotes.marketCapChange24h}")
                Text(text = "Change 15m: ${currentQuotes.percentChange15m}")
                Text(text = "Change 30m: ${currentQuotes.percentChange30m}")
                Text(text = "Change 1h: ${currentQuotes.percentChange1h}")
                Text(text = "Change 1d: ${currentQuotes.percentChange24h}")
                Text(text = "Change 7d: ${currentQuotes.percentChange7d}")
                Text(text = "Change 30d: ${currentQuotes.percentChange30d}")

                val lastUpdated = currency.lastUpdated
                    .replace('T', ' ')
                    .replace('Z', ' ')

                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.Bottom)
                        .padding(bottom = 8.dp),
                    text = "Last Updated: $lastUpdated"
                )
            }
        }
    }
}