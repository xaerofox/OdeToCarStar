package com.jtorr.odetocarstar.presentation.util.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VelocitySpacingRow(
    label: String,
    width: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
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
            modifier = Modifier.width(140.dp)
        )
        Box(
            modifier = Modifier
                .height(24.dp)
                .width(width.dp)
                .background(Color(0xFF00F0FF), shape = Shapes.zero)
                .border(1.dp, Color(0xFFDBFCFF), shape = Shapes.zero)
        )
    }
}

@Preview(name = "Spacing Preview", heightDp = 1300)
@Composable
private fun VelocitySpacingPreview() {
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
                text = "VELOCITY SPACING",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(color = Color(0xFF353436))

            val spacingItems = listOf(
                "unit (4dp)" to Spacing.unit,
                "unit2 (8dp)" to Spacing.unit2,
                "unit4 (16dp)" to Spacing.unit4,
                "unit8 (32dp)" to Spacing.unit8,
                "unit12 (48dp)" to Spacing.unit12
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                spacingItems.forEach { (label, dp) ->
                    VelocitySpacingRow(
                        label = label,
                        width = dp.value.toInt(),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            HorizontalDivider(color = Color(0xFF353436))

            Text(
                text = "GUTTERS & MARGINS",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val marginItems = listOf(
                "gutter (16dp)" to Spacing.gutter,
                "marginMobile (20dp)" to Spacing.marginMobile,
                "marginDesktop (64dp)" to Spacing.marginDesktop
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                marginItems.forEach { (label, dp) ->
                    VelocitySpacingRow(
                        label = label,
                        width = dp.value.toInt(),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            HorizontalDivider(color = Color(0xFF353436))

            Text(
                text = "STACKS",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val stackItems = listOf(
                "stackSm (8dp)" to Spacing.stackSm,
                "stackMd (24dp)" to Spacing.stackMd,
                "stackLg (48dp)" to Spacing.stackLg
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                stackItems.forEach { (label, dp) ->
                    VelocitySpacingRow(
                        label = label,
                        width = dp.value.toInt(),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            HorizontalDivider(color = Color(0xFF353436))

            Text(
                text = "PADDING",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val paddingItems = listOf(
                "paddingSmall (8dp)" to Spacing.paddingSmall,
                "paddingMedium (16dp)" to Spacing.paddingMedium,
                "paddingLarge (24dp)" to Spacing.paddingLarge,
                "paddingXLarge (32dp)" to Spacing.paddingXLarge
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                paddingItems.forEach { (label, dp) ->
                    VelocitySpacingRow(
                        label = label,
                        width = dp.value.toInt(),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            HorizontalDivider(color = Color(0xFF353436))

            Text(
                text = "ELEVATION OFFSETS",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val elevationItems = listOf(
                "glassBlurSurface (20dp)" to Spacing.glassBlurSurface,
                "glassBlurActive (30dp)" to Spacing.glassBlurActive
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                elevationItems.forEach { (label, dp) ->
                    VelocitySpacingRow(
                        label = label,
                        width = dp.value.toInt(),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}
