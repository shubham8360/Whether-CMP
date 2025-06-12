package org.example.project.whether.presentation.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.whether.domain.models.Weather
import org.example.project.whether.presentation.preview.previewWeather
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun WeatherScreenContent(currentWeather: Weather) {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = state
    ) {

        headerDateTime(
            modifier = Modifier.padding(start = 20.dp),
            formatterDateTime = currentWeather.formattedDateTime
        )
        headerFormatterTime(
            modifier = Modifier.padding(start = 20.dp),
            currentWeather.formatterTime
        )
        headerCurrentWhether(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp), currentWeather
        )

        headerCoordinates(
            modifier = Modifier.padding(vertical = 4.dp),
            value = currentWeather.latLongString
        )

        midCurrentWhetherFirst(
            Modifier.fillMaxWidth().height(120.dp),
            currentWeather.current,
            currentWeather.currentUnits
        )
        midCurrentWhetherSecond(
            Modifier.fillMaxWidth().height(120.dp),
            currentWeather.current,
            currentWeather.currentUnits
        )

        itemsIndexed(items = currentWeather.daily.time) { position, time ->
            ForeCastItem(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                time = time.formattedTime,
                weatherCode = currentWeather.daily.weatherCode[position],
                maxTemp = currentWeather.daily.temperature2mMax[position],
                minTemp = currentWeather.daily.temperature2mMin[position]
            )
        }
    }
}


@Preview()
@Composable
private fun PreviewWhetherScreen() {
    MaterialTheme {
        WeatherScreenContent(currentWeather = previewWeather)
    }
}
