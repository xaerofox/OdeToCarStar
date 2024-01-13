package com.jtor.odetocarstar.presentation.trims.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailSection(
    headerTitle: String, content: @Composable () -> Unit,
    onHeaderClicked: (Boolean) -> Unit = {}
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .clickable { isExpanded = !isExpanded }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(headerTitle)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand/Collapse"
            )
        }


        if (isExpanded) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                content()
            }
        }
    }

    onHeaderClicked(isExpanded)
}

@Preview(showBackground = true)
@Composable
fun DetailSectionPreview() {
    DetailSection(
        headerTitle = "Test",
        content = {
            Column {
                TrimElementItem(name = "Engine", value = "Cool V98")
                TrimElementItem(name = "Body", value = "Slick")
                TrimElementItem(name = "Did this work?", value = "maybe")
            }
        },
    )
}