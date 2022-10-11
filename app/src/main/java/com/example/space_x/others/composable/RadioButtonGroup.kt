package com.example.space_x.others.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun RadiobuttonGroup(
    // name of the group
    title: String? = null,
    // list of options names
    radioOptions: List<String>,
    // index of selected option
    selectedOption: Int,
    // on option selected action
    onOptionSelected: (Int) -> Unit,
    // colors used in radio group (text color, border color, button color, ...)
    colors: RadioButtonGroupColors = RadioButtonGroupDefaults.colors()
) {
    Column(
        Modifier
            .border(
                width = 2.dp,
                color = colors.groupBorderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    ) {
        title?.let {
            RadiobuttonGroupTitle(title = it, color = colors.labelColor)
        }
        radioOptions.forEachIndexed { index, option ->
            RadiobuttonGroupOption(label = option, selected = index == selectedOption, colors = colors) {
                onOptionSelected(index)
            }
        }
    }

}

@Composable
fun RadiobuttonGroupTitle(title: String, color: Color){
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        color = color,
        modifier = Modifier.padding(bottom = 10.dp))
}

// Radiobutton with label
@Composable
fun RadiobuttonGroupOption(label: String, selected: Boolean, colors: RadioButtonGroupColors, onOptionSelected: ()->Unit){
    Row(
        Modifier
            .fillMaxWidth()
            .height(35.dp)
            .clip(RoundedCornerShape(10.dp))
            .selectable(
                selected = selected,
                onClick = { onOptionSelected() },
                role = Role.RadioButton
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null, // null recommended for accessibility with screensavers
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.selectedButtonColor,
                unselectedColor = colors.unselectedButtonColor
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp),
            color = colors.labelColor
        )
    }
}

// Default colors of Radiobutton group
class RadioButtonGroupDefaults {
    companion object {
        @Composable
        fun colors (
            groupBorderColor: Color = MaterialTheme.colorScheme.primary,
            labelColor: Color = MaterialTheme.colorScheme.onSurface,
            selectedButtonColor: Color = MaterialTheme.colorScheme.primary,
            unselectedButtonColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
        ): RadioButtonGroupColors {
            return RadioButtonGroupColors(
                groupBorderColor = groupBorderColor,
                labelColor = labelColor,
                selectedButtonColor = selectedButtonColor,
                unselectedButtonColor = unselectedButtonColor
            )
        }
    }
}

// Configurable colors of radiobutton group
data class RadioButtonGroupColors(
    val groupBorderColor: Color,
    val labelColor: Color,
    val selectedButtonColor: Color,
    val unselectedButtonColor: Color
)