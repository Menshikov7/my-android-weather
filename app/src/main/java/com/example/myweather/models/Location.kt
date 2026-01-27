package com.example.myweather.models

data class Location(
    val country: String,
    val lat: String,
    val localtime: String,
    val localtimeEpoch: Int,
    val lon: String,
    val name: String,
    val region: String,
    val timezoneId: String,
    val utcOffset: String
)
