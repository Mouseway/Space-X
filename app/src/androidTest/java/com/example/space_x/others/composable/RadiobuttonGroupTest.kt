package com.example.space_x.others.composable

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class RadiobuttonGroupTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun haveTitle(){
        val title = "Title"
        composeTestRule.setContent {
            RadiobuttonGroup(
                title = title,
                radioOptions = emptyList(),
                selectedOption = -1,
                onOptionSelected = {})
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun allOptionsAreDisplayed(){
        val options = listOf(
            "Option 1",
            "Option 2",
            "Option 3"
        )

        composeTestRule.setContent {
            RadiobuttonGroup(
                radioOptions = options,
                selectedOption = 0,
                onOptionSelected = {})
        }

        options.forEach {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
    }

    @Test
    fun clickOnRadiobuttonCallsOnOptionSelected(){
        val options = listOf(
            "Option 1",
            "Option 2",
            "Option 3"
        )

        var clickedIndex: Int = -1;

        composeTestRule.setContent {
            RadiobuttonGroup(
                radioOptions = options,
                selectedOption = 0,
                onOptionSelected = {clickedIndex = it})
        }
        options.forEachIndexed { i, option ->
            composeTestRule.onNodeWithText(option).performClick()
            assertThat(clickedIndex).isEqualTo(i)
        }
    }


    @Test
    fun rightOptionIsSelected() {
        val options = listOf(
            "Option 1",
            "Option 2",
            "Option 3"
        )
        val selectedOption = 1

        composeTestRule.setContent {
           RadiobuttonGroup(
               radioOptions = options,
               selectedOption = selectedOption,
               onOptionSelected = {})
        }
        composeTestRule.onNode(isSelected())
        composeTestRule.onNode(isSelected()).assert(hasText(options[selectedOption]))
    }
}
