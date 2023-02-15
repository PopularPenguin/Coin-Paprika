package com.popularpenguin.coinpaprika.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val rank: String = "",
    @Json(name = "circulating_supply") val circulatingSupply: Long = 0L,
    @Json(name = "total_supply") val totalSupply: Long = 0L,
    @Json(name = "max_supply") val maxSupply: Long = 0L,
    @Json(name = "beta_value") val betaValue: Double = 0.0,
    @Json(name = "first_data_at") val firstDataAt: String = "",
    @Json(name = "last_updated") val lastUpdated: String = "",
    val quotes: Quotes = Quotes()
)

@JsonClass(generateAdapter = true)
data class Quotes(
    @Json(name = "USD") val usd: UsdQuotes = UsdQuotes()
)

@JsonClass(generateAdapter = true)
data class UsdQuotes(
    val price: Double = 0.0,
    @Json(name = "volume_24h") val volume24h: Double = 0.0,
    @Json(name = "volume_24h_change_24h") val volume24hChange24h: Float = 0f,
    @Json(name = "market_cap") val marketCap: Long = 0L,
    @Json(name = "market_cap_change_24h") val marketCapChange24h: Float = 0f,
    @Json(name = "percent_change_15m") val percentChange15m: Float = 0f,
    @Json(name = "percent_change_30m") val percentChange30m: Float = 0f,
    @Json(name = "percent_change_1h") val percentChange1h: Float = 0f,
    @Json(name = "percent_change_6h") val percentChange6h: Float = 0f,
    @Json(name = "percent_change_12h") val percentChange12h: Float = 0f,
    @Json(name = "percent_change_24h") val percentChange24h: Float = 0f,
    @Json(name = "percent_change_7d") val percentChange7d: Float = 0f,
    @Json(name = "percent_change_30d") val percentChange30d: Float = 0f,
    @Json(name = "percent_change_1y") val percentChange1y: Float = 0f,
    @Json(name = "ath_price") val athPrice: Double = 0.0,
    @Json(name = "ath_date") val athDate: String = "",
    @Json(name = "percent_from_price_ath") val percentFromPriceAth: Float = 0f
)