package com.jtorr.odetocarstar.presentation.makes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import coil.compose.SubcomposeAsyncImage
import com.jtorr.odetocarstar.R
import com.jtorr.odetocarstar.data.model.CarMake
import com.jtorr.odetocarstar.presentation.util.Constants
import com.jtorr.odetocarstar.presentation.util.theme.OdeToCarStarTheme

// Velocity Design System Colors
private val VelocityPrimaryAccent = Color(0xFF00DBE9) // Electric Blue
private val VelocityDarkBg = Color(0xFF131314) // Midnight Carbon
private val VelocityCardBg = Color(0xFF1E1E1E) // Slightly lighter dark for cards
private val VelocityWhite = Color.White

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
            .padding(4.dp)
            .shadow(8.dp, MaterialTheme.shapes.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = VelocityCardBg),
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
                    .background(getMakeBackground(make.name)),
                model = findLogo(make.name.lowercase()),
                contentDescription = "${make.name} logo",
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(64.dp),
                        color = VelocityPrimaryAccent
                    )
                },
                error = {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(VelocityDarkBg)
                            .padding(16.dp),
                        painter = painterResource(id = R.drawable.baseline_directions_car_filled_24),
                        contentDescription = "Fallback image",
                    )
                }
            )

            // Velocity accent bar at bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = androidx.compose.ui.Alignment.BottomCenter)
                    .height(4.dp)
                    .background(VelocityPrimaryAccent)
            )
        }

        // Make name label at bottom
        androidx.compose.material3.Text(
            text = make.name.uppercase(),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
            color = VelocityPrimaryAccent
        )
    }
}

private fun getMakeBackground(make: String): Color {
    return when (make.lowercase()) {
        "bmw" -> VelocityDarkBg
        else -> VelocityCardBg
    }
}

// This function is referenced but not defined in the file - need to add it
private fun findLogo(makeName: String): String {
    return Constants.IMAGE_BASE_URL + Constants.IMAGE_PATH_THUMB + makeName.replace(" ", "-") + ".png?raw=true"
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
