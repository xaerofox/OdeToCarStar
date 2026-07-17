package com.jtor.odetocarstar.presentation.util.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

object VelocityTypes {
    val displayLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = FontAnton,
        fontWeight = FontWeight.Normal,
        fontSize = 72.sp,
        lineHeight = 72.sp,
        letterSpacing = 0.02.em
    )

    val displayMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = FontAnton,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        lineHeight = 53.sp,
        letterSpacing = 0.04.em
    )

    val displaySmall = androidx.compose.ui.text.TextStyle(
        fontFamily = FontAnton,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 35.sp
    )

    val headlineMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = FontAnton,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 29.sp,
        letterSpacing = 0.04.em
    )

    val bodyLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = FontGeist,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 29.sp
    )

    val bodyMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = FontGeist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    val labelLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = FontJetBrainsMono,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.1.em
    )

    val bodySmall = androidx.compose.ui.text.TextStyle(
        fontFamily = FontJetBrainsMono,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 20.sp
    )
}

@Composable
fun VelocityTypeColumn(
    label: String,
    textStyle: androidx.compose.ui.text.TextStyle,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                fontFamily = FontJetBrainsMono,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                color = Color(0xFF849495)
            ),
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = "Velocity",
            style = textStyle,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(name = "Light Theme")
@Composable
private fun VelocityTypesPreviewLight() {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography,
        shapes = Shapes.all
    ) {
        VelocityTypesPreview()
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun VelocityTypesPreviewDark() {
    MaterialTheme(
        colorScheme = darkColorScheme(),
        typography = Typography,
        shapes = Shapes.all
    ) {
        VelocityTypesPreview()
    }
}

@Preview(name = "Midnight Carbon Theme")
@Composable
private fun VelocityTypesPreview() {
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
                text = "VELOCITY TYPOGRAPHY",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(color = Color(0xFF353436))

            VelocityTypeColumn(
                label = "displayLarge",
                textStyle = VelocityTypes.displayLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            VelocityTypeColumn(
                label = "displayMedium",
                textStyle = VelocityTypes.displayMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            VelocityTypeColumn(
                label = "displaySmall",
                textStyle = VelocityTypes.displaySmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            VelocityTypeColumn(
                label = "headlineMedium",
                textStyle = VelocityTypes.headlineMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            HorizontalDivider(color = Color(0xFF353436))
            VelocityTypeColumn(
                label = "bodyLarge",
                textStyle = VelocityTypes.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            VelocityTypeColumn(
                label = "bodyMedium",
                textStyle = VelocityTypes.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            HorizontalDivider(color = Color(0xFF353436))
            VelocityTypeColumn(
                label = "bodySmall",
                textStyle = VelocityTypes.bodySmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            VelocityTypeColumn(
                label = "labelLarge",
                textStyle = VelocityTypes.labelLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
