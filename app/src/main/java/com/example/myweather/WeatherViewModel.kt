package com.example.myweather

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.enpoints.ForecastService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import com.example.myweather.models.Result as WeatherResult

class WeatherViewModel : ViewModel() {
    private val retrofit = RetrofitBuilder.createRetrofit()
    private val forecastService: ForecastService = retrofit.create(ForecastService::class.java)

    private val _weatherResult = mutableStateOf("")
    val weatherResult = _weatherResult

    fun fetchWeather(city: String) {
        if (city.isBlank()) {
            _weatherResult.value = "Введите название города"
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<WeatherResult> = forecastService.forecast(city)
                if (response.isSuccessful) {
                    val current = response.body()?.current
                    _weatherResult.value = """
                        Погода в городе: $city
                        Температура: ${current?.temperature}°C
                        Влажность: ${current?.humidity}%
                        Ветер: ${current?.wind_speed}м/с
                        Качество воздуха: ${current?.air_quality}
                    """.trimIndent()
                } else {
                    _weatherResult.value = "Ошибка: ${response.message()}"
                }
            } catch (e: Exception) {
                _weatherResult.value = "Ошибка сети: ${e.message}"
            }
        }
    }
}
