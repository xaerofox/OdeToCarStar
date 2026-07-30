package com.jtorr.odetocarstar.presentation.util.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VelocityShapePreview(
    label: String,
    shape: androidx.compose.foundation.shape.CornerBasedShape,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color(0xFF2A2A2B), shape = shape)
                .border(1.dp, Color(0xFF849495), shape = shape)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color(0xFF849495)
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(name = "Shapes Preview")
@Composable
private fun VelocityShapesPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(VelocitySurface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "VELOCITY SHAPES",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(color = Color(0xFF353436))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.Top
            ) {
                VelocityShapePreview(
                    label = "extraSmall",
                    shape = Shapes.all.extraSmall,
                    modifier = Modifier.weight(1f)
                )
                VelocityShapePreview(
                    label = "small",
                    shape = Shapes.all.small,
                    modifier = Modifier.weight(1f)
                )
                VelocityShapePreview(
                    label = "medium",
                    shape = Shapes.all.medium,
                    modifier = Modifier.weight(1f)
                )
                VelocityShapePreview(
                    label = "large",
                    shape = Shapes.all.large,
                    modifier = Modifier.weight(1f)
                )
                VelocityShapePreview(
                    label = "extraLarge",
                    shape = Shapes.all.extraLarge,
                    modifier = Modifier.weight(1f)
                )
            }

            HorizontalDivider(color = Color(0xFF353436))

            val shapes = listOf(
                "zero" to Shapes.zero,
                "chamfered" to Shapes.chamfered
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.Top
            ) {
                shapes.forEach { (name, shape) ->
                    VelocityShapePreview(
                        label = name,
                        shape = shape,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
