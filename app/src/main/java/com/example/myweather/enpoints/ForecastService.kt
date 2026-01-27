package com.example.myweather.enpoints

import com.example.myweather.glossary.FORECAST_ENDPOINT
import com.example.myweather.models.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET(FORECAST_ENDPOINT)
    suspend fun forecast(@Query("query") city: String): Response<Result>
}
