package org.example.project.whether.presentation

import org.example.project.core.presentation.UiText
import org.example.project.whether.domain.models.Whether

sealed class WhetherState {
    object Loading : WhetherState()

    data class Success(val whether: Whether) : WhetherState()

    data class Error(val error: UiText) : WhetherState()
}
