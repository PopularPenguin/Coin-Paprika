package com.popularpenguin.coinpaprika.api

import com.popularpenguin.coinpaprika.data.Currency
import retrofit2.http.GET

interface ApiService {
    @GET("tickers")
    suspend fun getTicker() : List<Currency>
}