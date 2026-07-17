package com.jtor.odetocarstar.presentation.util.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ColorToken(
    val name: String,
    val color: Color,
    val category: String
)

val velocityColorTokens = listOf(
    // Surface hierarchy
    ColorToken("Velocity Surface", VelocitySurface, "Surface"),
    ColorToken("Surface Dim", SurfaceDim, "Surface"),
    ColorToken("Surface Bright", SurfaceBright, "Surface"),
    ColorToken("Surface Container Lowest", SurfaceContainerLowest, "Surface"),
    ColorToken("Surface Container Low", SurfaceContainerLow, "Surface"),
    ColorToken("Surface Container", SurfaceContainer, "Surface"),
    ColorToken("Surface Container High", SurfaceContainerHigh, "Surface"),
    ColorToken("Surface Container Highest", SurfaceContainerHighest, "Surface"),
    ColorToken("Surface Variant", SurfaceVariant, "Surface"),

    // On-surface
    ColorToken("On Surface", OnSurface, "On Surface"),
    ColorToken("On Surface Variant", OnSurfaceVariant, "On Surface"),
    ColorToken("Inverse Surface", InverseSurface, "Inverse"),
    ColorToken("Inverse On Surface", InverseOnSurface, "Inverse"),

    // Background
    ColorToken("Background", Background, "Background"),
    ColorToken("On Background", OnBackground, "Background"),

    // Outline
    ColorToken("Outline", Outline, "Outline"),
    ColorToken("Outline Variant", OutlineVariant, "Outline"),

    // Primary - Electric Blue
    ColorToken("Primary", Primary, "Primary"),
    ColorToken("On Primary", OnPrimary, "Primary"),
    ColorToken("Primary Container", PrimaryContainer, "Primary"),
    ColorToken("On Primary Container", OnPrimaryContainer, "Primary"),
    ColorToken("Inverse Primary", InversePrimary, "Primary"),
    ColorToken("Primary Fixed", PrimaryFixed, "Primary"),
    ColorToken("Primary Fixed Dim", PrimaryFixedDim, "Primary"),
    ColorToken("On Primary Fixed", OnPrimaryFixed, "Primary"),
    ColorToken("On Primary Fixed Variant", OnPrimaryFixedVariant, "Primary"),

    // Secondary - Racing Red
    ColorToken("Secondary", Secondary, "Secondary"),
    ColorToken("On Secondary", OnSecondary, "Secondary"),
    ColorToken("Secondary Container", SecondaryContainer, "Secondary"),
    ColorToken("On Secondary Container", OnSecondaryContainer, "Secondary"),
    ColorToken("Secondary Fixed", SecondaryFixed, "Secondary"),
    ColorToken("Secondary Fixed Dim", SecondaryFixedDim, "Secondary"),
    ColorToken("On Secondary Fixed", OnSecondaryFixed, "Secondary"),
    ColorToken("On Secondary Fixed Variant", OnSecondaryFixedVariant, "Secondary"),

    // Tertiary - Acid Green
    ColorToken("Tertiary", Tertiary, "Tertiary"),
    ColorToken("On Tertiary", OnTertiary, "Tertiary"),
    ColorToken("Tertiary Container", TertiaryContainer, "Tertiary"),
    ColorToken("On Tertiary Container", OnTertiaryContainer, "Tertiary"),
    ColorToken("Tertiary Fixed", TertiaryFixed, "Tertiary"),
    ColorToken("Tertiary Fixed Dim", TertiaryFixedDim, "Tertiary"),
    ColorToken("On Tertiary Fixed", OnTertiaryFixed, "Tertiary"),
    ColorToken("On Tertiary Fixed Variant", OnTertiaryFixedVariant, "Tertiary"),

    // Error
    ColorToken("Error", Error, "Error"),
    ColorToken("On Error", OnError, "Error"),
    ColorToken("Error Container", ErrorContainer, "Error"),
    ColorToken("On Error Container", OnErrorContainer, "Error"),

    // Special
    ColorToken("Surface Tint", SurfaceTint, "Special"),
)

@Preview(name = "Velocity Color Preview", heightDp = 2100)
@Composable
fun VelocityColorPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {


            // Color grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
            ) {
                val grouped = velocityColorTokens.groupBy { it.category }
                grouped.forEach { (category, tokens) ->
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        // Category label
                        Text(
                            text = category.uppercase(),
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding( Spacing.unit2)
                        )
                    }
                    items(tokens) { token ->
                        ColorSwatch(token)
                    }
                }
            }
        }
    }
}

@Composable
private fun ColorSwatch(token: ColorToken) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(token.color)
        )
        Text(
            text = token.name,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.unit)
        )
//        Text(
//            text = "#${token.color.value.toString(16).substring(2).uppercase()}",
//            style = MaterialTheme.typography.bodySmall.copy(fontSize = 8.sp),
//            color = OnSurfaceVariant.copy(alpha = 0.6f),
//            textAlign = TextAlign.Center,
//            modifier = Modifier.fillMaxWidth()
//        )
    }
}
