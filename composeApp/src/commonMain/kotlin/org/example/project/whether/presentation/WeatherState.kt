package org.example.project.whether.presentation

import org.example.project.core.presentation.UiText
import org.example.project.whether.domain.models.Weather

sealed class WeatherState {
    object Loading : WeatherState()

    data class Success(val whether: Weather) : WeatherState()

    data class Error(val error: UiText) : WeatherState()
}
