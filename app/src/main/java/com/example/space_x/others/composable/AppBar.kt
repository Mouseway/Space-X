package com.example.space_x.others

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OptionsMenu(options: List<Pair<String, () -> Unit>>){
    val showMenu = remember { mutableStateOf(false)}
    Box{
        IconButton(onClick = { showMenu.value = true }) {
            Icon(Icons.Rounded.MoreVert, contentDescription = "More")
        }
        DropdownMenu(
            expanded = showMenu.value,
            onDismissRequest = { showMenu.value = false }) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.first)},
                    onClick = {
                        showMenu.value = false
                        it.second()
                    })
            }
        }
    }
}

// Menu icon button
@Composable
fun NavigationIcon(onNavigationClick: () -> Unit){
    IconButton(onClick = { onNavigationClick() }) {
        Icon(Icons.Rounded.Menu, contentDescription = "Menu")
    }
}


// App bar
// Contains title and menu button for opening drawer
// It can also contain a filter button (when filterOnClick isn't null)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, drawerState: DrawerState, options: List<Pair<String, () -> Unit>>, filterOnClick: (() -> Unit)? = null) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        // App bar title
        title = {Text(text = title)},
        // Menu icon that opens drawer
        navigationIcon = { NavigationIcon {
            scope.launch {
                drawerState.open()
            }
        }},
        // filter icon and its options
        actions = {
            filterOnClick?.let {
                FilterButton(it)
            }
            OptionsMenu(options = options)
        },

        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

// Filter icon button
@Composable
fun FilterButton(onClick: () -> Unit){
    IconButton(onClick = { onClick() }) {
        Icon(Icons.Rounded.Tune, null)
    }
}