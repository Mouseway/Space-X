package com.example.space_x.others.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(items: List<DrawerItem>, selectedIndex: Int){
    ModalDrawerSheet() {
        items.forEachIndexed { index, item ->
            Spacer(Modifier.height(10.dp))
            NavigationDrawerItem(
                label = { Text(text = item.label) },
                selected = index == selectedIndex,
                onClick = { item.onClick(index) },
                icon = { Icon(item.icon, contentDescription = null) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

data class DrawerItem(
    val label: String,
    val icon: ImageVector,
    val onClick: (Int) -> Unit
)