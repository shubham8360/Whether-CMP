package org.example.project.whether.data.location

import kotlinx.coroutines.flow.Flow


 interface LocationProvider {
    operator fun invoke():Flow<LocationState>
}