package org.example.project.whether.presentation.utils

import org.jetbrains.compose.resources.DrawableResource
import whethercmp.composeapp.generated.resources.Res
import whethercmp.composeapp.generated.resources.clear_sky
import whethercmp.composeapp.generated.resources.drizzle
import whethercmp.composeapp.generated.resources.few_clouds
import whethercmp.composeapp.generated.resources.freezing_drizzle
import whethercmp.composeapp.generated.resources.freezing_rain
import whethercmp.composeapp.generated.resources.mist
import whethercmp.composeapp.generated.resources.overcast
import whethercmp.composeapp.generated.resources.shower_rain
import whethercmp.composeapp.generated.resources.snow
import whethercmp.composeapp.generated.resources.thunderstorm

fun resolveWhetherAppIcon(code: Int): DrawableResource {
  return  when(code){
        0-> Res.drawable.clear_sky  //clear sky
        1-> Res.drawable.clear_sky  //mainly clear
        2-> Res.drawable.few_clouds  //partly cloudy
        3-> Res.drawable.overcast  //overcast
        45-> Res.drawable.mist  //fog
        48-> Res.drawable.mist  //depositing rime fog
        51-> Res.drawable.drizzle  //drizzle
        53-> Res.drawable.drizzle  //drizzle
        55-> Res.drawable.drizzle  //drizzle
        56-> Res.drawable.freezing_drizzle  //freezing drizzle
        57-> Res.drawable.freezing_drizzle  //freezing drizzle
        61-> Res.drawable.shower_rain  //rain
        63-> Res.drawable.shower_rain  //rain
        65-> Res.drawable.shower_rain  //rain
        66-> Res.drawable.freezing_rain  //freezing rain
        67-> Res.drawable.freezing_rain //freezing rain
        71-> Res.drawable.snow  //snow
        73-> Res.drawable.snow  //snow
        75-> Res.drawable.snow  //snow
        77-> Res.drawable.snow  //snow
        80-> Res.drawable.shower_rain  //rain showers
        81-> Res.drawable.shower_rain  //rain showers
        82-> Res.drawable.shower_rain  //rain showers
        85-> Res.drawable.shower_rain  //snow showers
        86-> Res.drawable.shower_rain  //snow showers
        95-> Res.drawable.thunderstorm  //thunderstorm
        96-> Res.drawable.thunderstorm  //thunderstorm
        99-> Res.drawable.thunderstorm  //thunderstorm
        else -> Res.drawable.few_clouds
    }
}

fun resolveWhetherDescription(code: Int): String {
    return when (code) {
        0 -> "Clear sky"
        1 -> "Mainly clear"
        2 -> "Partly cloudy"
        3 -> "Overcast"
        45 -> "Fog"
        48 -> "Depositing rime fog"
        51 -> "Drizzle"
        53 -> "Drizzle"
        55 -> "Drizzle"
        56 -> "Freezing drizzle"
        57 -> "Freezing drizzle"
        61 -> "Rain"
        63 -> "Rain"
        65 -> "Rain"
        66 -> "Freezing rain"
        67 -> "Freezing rain"
        71 -> "Snow"
        73 -> "Snow"
        75 -> "Snow"
        77 -> "Snow"
        80 -> "Rain showers"
        81 -> "Rain showers"
        82 -> "Rain showers"
        85 -> "Snow showers"
        86 -> "Snow showers"
        95 -> "Thunderstorm"
        96 -> "Thunderstorm"
        99 -> "Thunderstorm"
        else -> "Unknown"




    }
}