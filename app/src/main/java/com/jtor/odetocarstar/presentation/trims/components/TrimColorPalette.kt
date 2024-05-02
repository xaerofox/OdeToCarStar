package com.jtor.odetocarstar.presentation.trims.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.jtor.odetocarstar.domain.model.TrimColor

@Composable
fun TrimColorPalette(
    title: String,
    trimColors: List<TrimColor>
) {
    var selectedTrimColorName by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(6.dp),
            text = title,
            style = MaterialTheme.typography.labelLarge
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    columns = GridCells.Adaptive(30.dp)
                ) {
                    items(trimColors) { trimColor ->
                        ColorTile(
                            trimColor = trimColor,
                            isSelected = selectedTrimColorName == trimColor.name
                        ) {
                            selectedTrimColorName = it.name
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = selectedTrimColorName,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TrimColorPalettePreview() {
    TrimColorPalette(
        title = "Interior Colors",
        trimColors = listOf(
            TrimColor(
                id = -1,
                trimId = 0,
                name = "Flamin' Hot Red",
                rgb = "254,22,90"
            ),
            TrimColor(
                id = -1,
                trimId = 1,
                name = "Electric Blue",
                rgb = "20,11,255"
            ),
            TrimColor(
                id = -1,
                trimId = 2,
                name = "Unreal Green",
                rgb = "22,220,40"
            )
        )
    )
}