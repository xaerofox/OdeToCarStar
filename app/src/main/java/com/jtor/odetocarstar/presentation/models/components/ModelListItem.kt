package com.jtor.odetocarstar.presentation.models.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jtor.odetocarstar.data.model.CarModel
import com.jtor.odetocarstar.presentation.util.theme.OdeToCarStarTheme

@Composable
fun ModelListItem(
    make: String,
    model: CarModel,
    onItemClick: (CarModel) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(model) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "$make ${model.name}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModelListItemPreview() {
    OdeToCarStarTheme {
        ModelListItem(
            make = "Ford",
            model = CarModel(1, 2, "Explorer"),
            onItemClick = {})
    }
}
