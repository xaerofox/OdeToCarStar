package com.jtorr.odetocarstar.presentation.makes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jtorr.odetocarstar.R
import com.jtorr.odetocarstar.data.model.CarMake
import com.jtorr.odetocarstar.presentation.util.theme.OdeToCarStarTheme
import com.jtorr.odetocarstar.presentation.util.theme.Spacing

@Composable
fun VelocityMakeListItem(
    modifier: Modifier = Modifier,
    make: CarMake,
    onItemClick: (CarMake) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(make) }
            .padding(Spacing.unit)
            .shadow(Spacing.unit2, MaterialTheme.shapes.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium)
                    .background(makeBackground(make.name)),
                model = findMakeLogo(make.name.lowercase()),
                contentDescription = "${make.name} logo",
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(64.dp),
                        color = MaterialTheme.colorScheme.primaryFixedDim
                    )
                },
                error = {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(Spacing.unit4),
                        painter = painterResource(id = R.drawable.baseline_directions_car_filled_24),
                        contentDescription = "Fallback image",
                    )
                }
            )

            // Accent bar marks the boundary between the logo art and the make name label.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter)
                    .height(Spacing.unit)
                    .background(MaterialTheme.colorScheme.primaryFixedDim)
            )
        }

        Text(
            text = make.name.uppercase(),
            modifier = Modifier
                .padding(start = Spacing.unit4, end = Spacing.unit4, bottom = Spacing.unit2 + Spacing.unit),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primaryFixedDim
        )
    }
}

@Composable
private fun makeBackground(make: String): Color {
    return when (make.lowercase()) {
        "bmw" -> MaterialTheme.colorScheme.background
        else -> MaterialTheme.colorScheme.surfaceContainer
    }
}

@Preview(showBackground = true)
@Composable
fun VelocityMakeListItemPreview() {
    OdeToCarStarTheme {
        VelocityMakeListItem(
            make = CarMake(0, "Ford"),
            onItemClick = {}
        )
    }
}
