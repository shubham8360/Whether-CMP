package org.example.project

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.runComposeUiTest
import org.example.project.core.presentation.UiText
import org.example.project.whether.presentation.WeatherScreen
import org.example.project.whether.presentation.WeatherState
import org.example.project.whether.presentation.preview.previewWeather
import org.example.project.whether.presentation.utils.TestUtils
import kotlin.test.Test

class WeatherScreenTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun weatherScreen_whenLoadingState_showsLoadingIndicator() = runComposeUiTest {

        setContent {
            WeatherScreen(state = WeatherState.Loading)
        }

        // Assert that the CircularProgressIndicator (with its content description) is shown
        onNodeWithContentDescription(TestUtils.loadingContentDesc).assertExists()

        // Assert that no error text or success content is shown
        onNodeWithContentDescription(TestUtils.successContentDesc).assertDoesNotExist()
        onNodeWithContentDescription(TestUtils.errorContentDesc).assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun weatherScreen_whenErrorState_showsErrorMessage() = runComposeUiTest {
        setContent {
            WeatherScreen(state = WeatherState.Error(UiText.DynamicString("Network Error")))
        }

        onNodeWithContentDescription(TestUtils.errorContentDesc).assertExists()

        // Assert that no loading or success content is shown
        onNodeWithContentDescription(TestUtils.successContentDesc).assertDoesNotExist()
        onNodeWithContentDescription(TestUtils.loadingContentDesc).assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun weatherScreen_whenSuccessState_showsWeatherData() = runComposeUiTest {
        val testWhetherState: WeatherState = WeatherState.Success(previewWeather)
        setContent {
            WeatherScreen(state = testWhetherState)
        }

        // Assert that the success content description is present
        onNodeWithContentDescription(TestUtils.successContentDesc).assertExists()

        // Assert that no loading or error content is shown
        onNodeWithContentDescription(TestUtils.loadingContentDesc).assertDoesNotExist()
        onNodeWithContentDescription(TestUtils.errorContentDesc).assertDoesNotExist()
    }
}


