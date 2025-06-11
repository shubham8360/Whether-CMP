package org.example.project.whether.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.core.data.safeCall
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.whether.data.dto.WhetherDto

class KtorRemoteDataSource(private val client: HttpClient) : RemoteDataSource {
    companion object {
        const val BASE_URL = "https://api.open-meteo.com/v1"
    }

    override suspend fun fetchWhetherUpdates(map: Map<String, Any>): Result<WhetherDto, DataError.Remote> {
        return safeCall<WhetherDto> {
            client.get("$BASE_URL/forecast") {
                //if initally location fetching takes time
                if (map.isEmpty()){
                    parameter("latitude", 32.2748)
                    parameter("longitude", 75.6529)
                }else{
                    map.forEach { (key, value) ->
                        parameter(key, value)
                    }
                }
                parameter(
                    "current",
                    "temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m,visibility,pressure_msl,is_day,shortwave_radiation"
                )
                parameter("daily", "weather_code,temperature_2m_max,temperature_2m_min")

            }
        }
    }
}