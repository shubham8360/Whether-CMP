package org.example.project.whether.domain.models


data class Daily(
     val temperature2mMax: List<Double>,
     val temperature2mMin: List<Double>,
     val time: List<String>,
     val weatherCode: List<Int>
)