package org.example.project

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.runComposeUiTest
import org.example.project.whether.presentation.WeatherScreen
import org.example.project.whether.presentation.WeatherState
import org.example.project.whether.presentation.utils.TestUtils
import kotlin.test.Test
import kotlin.test.assertEquals

class ComposeAppCommonTest {

    @Test
    fun example() {
        assertEquals(3, 1 + 2)
    }



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

}