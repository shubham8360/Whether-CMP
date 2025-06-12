package org.example.project.whether.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.whether.domain.models.Whether
import org.example.project.whether.presentation.preview.previewWhether
import org.example.project.whether.presentation.ui.textColor
import org.example.project.whether.presentation.ui.whiteColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import whethercmp.composeapp.generated.resources.Res
import whethercmp.composeapp.generated.resources.air_pressure
import whethercmp.composeapp.generated.resources.day_temp
import whethercmp.composeapp.generated.resources.feels_like
import whethercmp.composeapp.generated.resources.humidity
import whethercmp.composeapp.generated.resources.pressure
import whethercmp.composeapp.generated.resources.temprature
import whethercmp.composeapp.generated.resources.uv_rays
import whethercmp.composeapp.generated.resources.visibility
import whethercmp.composeapp.generated.resources.wind_speed


@Composable
fun WeatherScreenContent(currentWhether: Whether) {
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
            formatterDateTime = currentWhether.formattedDateTime
        )
        headerFormatterTime(
            modifier = Modifier.padding(start = 20.dp),
            currentWhether.formattedDateTime
        )
        headerCurrentWhether(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp), currentWhether
        )

        headerCoordinates(
            modifier = Modifier.padding(vertical = 4.dp),
            value = currentWhether.latLongString
        )

        midCurrentWhetherFirst(
            Modifier.fillMaxWidth().height(120.dp),
            currentWhether.current,
            currentWhether.currentUnits
        )
        midCurrentWhetherSecond(
            Modifier.fillMaxWidth().height(120.dp),
            currentWhether.current,
            currentWhether.currentUnits
        )

        itemsIndexed(items = currentWhether.daily.time) { position, time ->
            ForeCastItem(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                time = time.formattedTime,
                weatherCode = currentWhether.daily.weatherCode[position],
                maxTemp = currentWhether.daily.temperature2mMax[position],
                minTemp = currentWhether.daily.temperature2mMin[position]
            )
        }
    }
}


@Preview()
@Composable
private fun PreviewWhetherScreen() {
    MaterialTheme {
        WeatherScreenContent(currentWhether = previewWhether)
    }
}
