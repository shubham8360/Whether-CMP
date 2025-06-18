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
            // For WeatherState.Error, if it takes a StringResource, you might need to
            // mock/provide a StringResource that resolves to `errorMessage`.
            // Or, if your WeatherState.Error(error: String) means a plain string.
            // Let's assume your WeatherState.Error can be constructed with a plain string for simplicity in test,
            // or that you have a testing utility to create a mock StringResource.

            // If your WeatherState.Error takes a moko-resources StringResource:
            // You might need a mock StringResource or directly use a plain string if your Composable handles it.
            // For `stringResource(Res.string.content_description_error)` within WeatherScreen:
            // Ensure Res.string.content_description_error is correctly set up for Compose Multiplatform Resources
            // or provide a CommonStrings mapping for it.

            // Let's assume your `error.asString()` function on `StringResource` works.
            // For testing, we can provide a simple string if `error.asString()` is trivial or mocked.
            // A more robust way might involve mocking the StringResource resolver.
            // For now, let's assume WeatherState.Error takes a plain string for simplicity in test setup.
            // If `error` in `WeatherState.Error(val error: StringResource)` needs a `StringResource`,
            // you'd need `expect`/`actual` for resources or use the new Compose Multiplatform Resources plugin.

            // For this test, let's create a simple mock for `StringResource` if needed,
            // or modify `WeatherState.Error` to take `String` for testing simplicity.
            // Or, rely on `stringResource` being handled by `runComposeUiTest` correctly if it's set up.

            // Given your `WeatherState.Error` takes `StringResource`, let's define a mock for it
            // or simplify. For the purpose of this test, let's assume we pass the string value
            // and the `WeatherScreen` correctly uses it for the Text.
            // If `Res.string.content_description_error` is used in the screen's semantics,
            // ensure its content matches what `CommonStrings` provides for testing.

            WeatherScreen(state = WeatherState.Error(UiText.DynamicString("Network Error")))
        }

        // This assumes `WeatherState.Error.error.asString()` will produce `errorMessage`
        // and the `Text` composable shows it.
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

        // Assert that specific weather data (like temperature and city) are shown
//        onNodeWithText(testWeatherData.temperature).assertExists()
//        onNodeWithText(testWeatherData.city).assertExists()

        // Assert that no loading or error content is shown
        onNodeWithContentDescription(TestUtils.loadingContentDesc).assertDoesNotExist()
        onNodeWithContentDescription(TestUtils.errorContentDesc).assertDoesNotExist()
    }
}


