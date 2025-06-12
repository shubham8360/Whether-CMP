package org.example.project.whether.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import org.example.project.whether.domain.models.Current
import org.example.project.whether.domain.models.CurrentUnits
import org.example.project.whether.domain.models.Whether
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
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


fun LazyListScope.headerDateTime(modifier: Modifier = Modifier, formatterDateTime: String) {
    item {
        Text(
            text = formatterDateTime,
            modifier = modifier,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
    }
}

fun LazyListScope.headerFormatterTime(modifier: Modifier = Modifier, time: String) {
    item {
        Text(
            text = time,
            modifier = modifier,
            style = TextStyle(fontSize = 16.sp)
        )
    }
}

fun LazyListScope.headerCoordinates(modifier: Modifier = Modifier, value: String) {
    item {
        Text(
            text = value,
            modifier = modifier,
            style = TextStyle(fontSize = 16.sp)
        )
    }
}

fun LazyListScope.headerCurrentWhether(modifier: Modifier = Modifier, currentWhether: Whether) {
    item {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
        ) {
            Card(
                modifier = Modifier.size(80.dp).align(Alignment.CenterVertically),
                shape = RoundedCornerShape(40.dp),
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    painter = painterResource(resolveWhetherAppIcon(currentWhether.current.weatherCode)),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                )
            }
            Text(
                text = currentWhether.current.temperature2m.toString(),
                modifier = Modifier.padding(start = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 70.sp
                )
            )
            Text(
                text = currentWhether.currentUnits.temperature2m,
                modifier = Modifier.padding(top = 20.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 22.sp
                )
            )
            Text(
                text = resolveWhetherDescription(currentWhether.current.weatherCode),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 25.sp,
                )
            )
        }
    }
}


fun LazyListScope.midCurrentWhetherFirst(
    modifier: Modifier = Modifier,
    current: Current, currentUnits: CurrentUnits,
) {
    item {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DailyItem(
                Res.drawable.temprature,
                current.temperature2m.toString(),
                currentUnits.temperature2m,
                stringResource(Res.string.feels_like),
            )
            DailyItem(
                Res.drawable.visibility,
                current.visibility.toString(),
                currentUnits.visibility,
                stringResource(Res.string.visibility)
            )
            DailyItem(
                Res.drawable.uv_rays,
                current.shortwaveRadiation.toString(),
                currentUnits.shortwaveRadiation,
                stringResource(Res.string.uv_rays)
            )
        }
    }
}

fun LazyListScope.midCurrentWhetherSecond(
    modifier: Modifier = Modifier,
    current: Current,
    currentUnits: CurrentUnits,
) {
    item {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DailyItem(
                Res.drawable.humidity,
                current.relativeHumidity2m.toString(),
                currentUnits.relativeHumidity2m,
                stringResource(Res.string.humidity)
            )
            DailyItem(
                Res.drawable.wind_speed,
                current.windSpeed10m.toString(),
                currentUnits.windSpeed10m, stringResource(Res.string.wind_speed)

            )
            DailyItem(
                Res.drawable.pressure,
                current.pressureMsl.toString(),
                currentUnits.pressureMsl, stringResource(Res.string.air_pressure)
            )
        }
    }
}

@Composable
fun ForeCastItem(
    modifier: Modifier,
    time: String,
    weatherCode: Int,
    maxTemp: String,
    minTemp: String
) {

    Column {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(CornerSize(15.dp)),
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = time,
                    style = TextStyle(
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 15.dp) // Added some padding for separation
                )

                Image(
                    modifier = Modifier
                        .weight(1f)
                        .size(40.dp), // Using fixed size for the image
                    painter = painterResource(resolveWhetherAppIcon(weatherCode)),
                    contentScale = ContentScale.Fit,
                    contentDescription = "",
                )

                Row(
                    modifier = Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = minTemp.toString(),
                        style = TextStyle(fontSize = 14.sp),
                    )

                    Text(
                        text = maxTemp.toString(),
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier.padding(start = 20.dp) // Added some padding for separation
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
    }
}

@Composable
fun DailyItem(icDay: DrawableResource, value: String, units: String, stringText: String) {
    Card(
        modifier = Modifier.size(100.dp),
        shape = RoundedCornerShape(15.dp),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(icDay),
                contentDescription = stringResource(Res.string.day_temp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(25.dp)
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = buildString {
                    append(value)
                    append(" ")
                    append(units)
                },
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 5.dp)
            )

            Text(
                text = stringText,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
