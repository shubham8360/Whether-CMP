package org.example.project.whether.presentation.utils

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import org.example.project.whether.domain.models.Whether
import org.example.project.whether.presentation.ui.textColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import whethercmp.composeapp.generated.resources.Res
import whethercmp.composeapp.generated.resources.day_temp
import whethercmp.composeapp.generated.resources.humidity
import whethercmp.composeapp.generated.resources.pressure
import whethercmp.composeapp.generated.resources.temprature
import whethercmp.composeapp.generated.resources.uv_rays
import whethercmp.composeapp.generated.resources.visibility
import whethercmp.composeapp.generated.resources.wind_speed

@Composable
fun WeatherLoadedScreen(currentWhether: Whether) {
    Spacer(modifier = Modifier.size(10.dp))
    Text(
        text = "City Name",
        modifier = Modifier.padding(start = 20.dp),
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp, color = textColor)
    )
    Text(
        text = currentWhether.current.time,
        modifier = Modifier.padding(start = 20.dp),
        style = TextStyle(fontSize = 16.sp, color = textColor)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
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
                fontWeight = FontWeight.Bold, fontSize = 70.sp, color = textColor
            )
        )
        Text(
            text = "°C",
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 22.sp, color = textColor
            )
        )
        Text(
            text = resolveWhetherDescription(currentWhether.current.weatherCode),
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterVertically),
            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 25.sp, color = textColor,
            )
        )
    }
    Spacer(modifier = Modifier.size(15.dp))
    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DailyItem(
                Res.drawable.temprature, currentWhether.current.temperature2m.toString(), "Feels Like"
            )
            DailyItem(
                Res.drawable.visibility, currentWhether.current.visibility.toString(), "Visibility"
            )
            DailyItem(
                Res.drawable.uv_rays, "NA", "UV Rays"
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DailyItem(
                Res.drawable.humidity, currentWhether.current.relativeHumidity2m.toString(), "Humidity"
            )
            DailyItem(
                Res.drawable.wind_speed, currentWhether.current.windSpeed10m.toString(), "Wind Speed"
            )
            DailyItem(
                Res.drawable.pressure, currentWhether.current.pressureMsl.toString(), "Air Pressure"
            )
        }
    }
    Spacer(modifier = Modifier.size(25.dp))
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {

        itemsIndexed (items = currentWhether.daily.time){position,time->

            Column {
                Card(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(CornerSize(15.dp)),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = time,
                            style = TextStyle(
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = textColor
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 15.dp) // Added some padding for separation
                        )

                        val whetherCode=currentWhether.daily.weatherCode[position]
                        Image(
                            modifier = Modifier
                                .weight(1f)
                                .size(40.dp), // Using fixed size for the image
                            painter = painterResource(resolveWhetherAppIcon(whetherCode)),
                            contentScale = ContentScale.Fit,
                            contentDescription = "",
                        )
                        val maxTemp=currentWhether.daily.temperature2mMax[position]
                        val minTemp=currentWhether.daily.temperature2mMin[position]
                        Row(
                            modifier = Modifier
                                .weight(1f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = maxTemp.toString(),
                                style = TextStyle(fontSize = 14.sp, color = textColor),
                            )

                            Text(
                                text = minTemp.toString(),
                                style = TextStyle(fontSize = 14.sp, color = textColor),
                                modifier = Modifier.padding(start = 20.dp) // Added some padding for separation
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun DailyItem(icDay: DrawableResource, data: String, stringText: String) {
    Card(
        modifier = Modifier.size(100.dp),
        shape = RoundedCornerShape(15.dp),

    ) {
        Column(modifier = Modifier, verticalArrangement = Arrangement.Center) {
            Icon(
                painter = painterResource(icDay),
                contentDescription = stringResource(Res.string.day_temp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(25.dp)
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = data,
                style = TextStyle(fontSize = 16.sp, color = textColor),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 5.dp)
            )

            Text(
                text = stringText,
                style = TextStyle(fontSize = 14.sp, color = textColor),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
