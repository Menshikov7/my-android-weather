package com.example.myweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.enpoints.ForecastService
import com.example.myweather.models.Current
import com.example.myweather.ui.theme.MyWeatherTheme

private lateinit var forecastService: ForecastService

class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWeatherTheme {
                WeatherScreen(viewModel)
            }
        }
    }
}


@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val cityInput = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Погода",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = cityInput.value,
            onValueChange = { cityInput.value = it },
            label = { Text("Город") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.fetchWeather(cityInput.value) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Получить")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = viewModel.weatherResult.value,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp
        )
    }
}


// Заглушка для получения погоды
//private suspend fun fetchWeather(city: String, result: MutableState<String>) {
//    var current: Current? = null
//
//    if (city.isBlank()) {
//        result.value = "Введите название города"
//        return
//    }

//    current = forecastService.forecast(city).execute().body()?.current
//    result.value = """
//            Погода в городе: $city
//            Температура: ${current?.temperature}°C
//            Влажность: ${current?.humidity}%
//            Ветер: ${current?.wind_speed}м/с
//            Качество воздуха: ${current?.air_quality}
//        """.trimIndent()
//    result.value = "Тут будет погода"
//}

//@Preview(showBackground = true)
//@Composable
//fun WeatherScreenPreview() {
//    MyWeatherTheme {
//        WeatherScreen(viewModel)
//    }
//}
