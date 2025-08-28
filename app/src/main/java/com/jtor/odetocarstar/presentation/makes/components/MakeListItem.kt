package com.jtor.odetocarstar.presentation.makes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.presentation.util.Constants
import com.jtor.odetocarstar.presentation.util.theme.OdeToCarStarTheme

@Composable
fun MakeListItem(
    make: CarMake,
    onItemClick: (CarMake) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(make) }
            .padding(10.dp)
    ) {
        SubcomposeAsyncImage (
            model = findMakeLogo(make.name.lowercase()),
            contentDescription = "${make.name} logo",
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(64.dp)
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .aspectRatio(100f/100f, true)
                .background(customBackgroundSetup(make.name.lowercase()))
                .padding(8.dp)
        )
    }
}

fun customBackgroundSetup(make: String): Color {
    return when(make) {
        "bmw" -> Color.Black
        else -> Color.White
    }
}

fun findMakeLogo(make: String): String {
    return Constants.IMAGE_BASE_URL + Constants.IMAGE_PATH_THUMB + make.replace(" ", "-") + ".png?raw=true"
}

@Preview(showBackground = true)
@Composable
fun MakeListItemPreview() {
    OdeToCarStarTheme {
        MakeListItem(make = CarMake(0, "Ford"), onItemClick = {})
    }
}
