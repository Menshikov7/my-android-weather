package com.example.myweather.models

import models.Request

data class Result(
    val current: Current,
    val location: Location,
    val request: Request
)
