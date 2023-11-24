package com.jtor.odetocarstar.presentation.makes.components

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
import com.jtor.odetocarstar.domain.model.CarMake
import com.jtor.odetocarstar.presentation.util.theme.OdeToCarStarTheme

@Composable
fun MakeListItem(
    make: CarMake,
    onItemClick: (CarMake) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(make) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${make.id}. ${make.name}",
            style = MaterialTheme.typography.headlineLarge
        )

    }
}

@Preview(showBackground = true)
@Composable
fun MakeListItemPreview() {
    OdeToCarStarTheme {
        MakeListItem(make = CarMake(0, "Ford"), onItemClick = {})
    }
}
