package com.example.space_x.others.composable

import androidx.compose.material.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class LoadingWrapperTest {


    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun loadingAndValueIsNull() {
        val state = LoadingState<String>(null, isLoading = true, showError = false)
        val loading = "Loading"
        val error = "Error"
        val content = "Content"


        composeTestRule.setContent {
            LoadingWrapper(loadingState = state,
                loading = {
                    Text(loading)
                },
                error = {
                    Text(text = error)
                },
                content = {
                    Text(text = content)
                }
            )
        }

        composeTestRule.onNodeWithText(loading).assertExists()
        composeTestRule.onNodeWithText(error).assertDoesNotExist()
        composeTestRule.onNodeWithText(content).assertDoesNotExist()
    }

    @Test
    fun errorAndValueIsNull(){
        val state = LoadingState<String>(null, isLoading = false, showError = true)
        val loading = "Loading"
        val error = "Error"
        val content = "Content"


        composeTestRule.setContent {
            LoadingWrapper(loadingState = state,
                loading = {
                    Text(loading)
                },
                error = {
                    Text(text = error)
                },
                content = {
                    Text(text = content)
                }
            )
        }

        composeTestRule.onNodeWithText(loading).assertDoesNotExist()
        composeTestRule.onNodeWithText(error).assertExists()
        composeTestRule.onNodeWithText(content).assertDoesNotExist()
    }

    @Test
    fun errorButContentIsNotNullShouldShowContent(){
        val content = "Content"
        val state = LoadingState(content, isLoading = false, showError = true)
        val loading = "Loading"
        val error = "Error"
        


        composeTestRule.setContent {
            LoadingWrapper(loadingState = state,
                loading = {
                    Text(loading)
                },
                error = {
                    Text(text = error)
                },
                content = {
                    Text(text = content)
                }
            )
        }

        composeTestRule.onNodeWithText(loading).assertDoesNotExist()
        composeTestRule.onNodeWithText(error).assertDoesNotExist()
        composeTestRule.onNodeWithText(content).assertExists()
    }


    @Test
    fun noLoadingNoErrorContentIsNotNull(){
        val content = "Content"
        val state = LoadingState<String>(content, isLoading = false, showError = false)
        val loading = "Loading"
        val error = "Error"



        composeTestRule.setContent {
            LoadingWrapper(loadingState = state,
                loading = {
                    Text(loading)
                },
                error = {
                    Text(text = error)
                },
                content = {
                    Text(text = it)
                }
            )
        }

        composeTestRule.onNodeWithText(loading).assertDoesNotExist()
        composeTestRule.onNodeWithText(error).assertDoesNotExist()
        composeTestRule.onNodeWithText(content).assertExists()
    }
}