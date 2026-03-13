package org.project.weather.cmp

import androidx.compose.ui.window.ComposeUIViewController
import org.project.weather.cmp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) { App(canSubscribeForLocation = true) }