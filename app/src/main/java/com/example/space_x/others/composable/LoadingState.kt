package com.example.space_x.others.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.space_x.R

// object for
data class LoadingState<T>(
    val value: T? = null,
    val isLoading: Boolean = false,
    val showError: Boolean = false,
)


// wrapper around composable function (screen)
// that handles loading state
@Composable
fun <T> LoadingWrapper(
    loadingState: LoadingState<T>,
    // The composable function is called if the state is loading.
    // Shows Linear progress bar by default
    loading: @Composable () -> Unit = {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    },
    // The composable function is called if the state is error
    // Shows Text with error by default.
    error: @Composable () -> Unit = {
        Text(text = stringResource(id = R.string.loading_error), color = MaterialTheme.colorScheme.error)
    },
    // The composable function is called when the data are loaded correctly.
    // Takes the loaded data as a parameter
    content: @Composable (T) -> Unit
){
    if(loadingState.isLoading){
        loading()
    }

    if(loadingState.showError && loadingState.value == null){
        error()
    }

    if(loadingState.value != null)
        content(loadingState.value)
}