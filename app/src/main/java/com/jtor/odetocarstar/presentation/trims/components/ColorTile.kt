package com.jtor.odetocarstar.presentation.trims.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jtor.odetocarstar.R
import com.jtor.odetocarstar.domain.model.TrimColor

@Composable
fun ColorTile(trimColor: TrimColor, isSelected: Boolean = false, onClick: (TrimColor) -> Unit) {
    var hasNoColor = false
    val colorArr = if (trimColor.rgb.isBlank()) {
        hasNoColor = true
        null
    } else {
        trimColor.rgb.split(',').map { it.toInt() }
    }
    val color = if (colorArr != null)
        Color(red = colorArr[0], green = colorArr[1], blue = colorArr[2])
    else {
        Color.Transparent
    }
    Box(modifier = Modifier
        .clip(CircleShape)
        .size(30.dp)
        .background(color)
        .border(
            width = 2.dp,
            color = if (isSelected) Color.Black else Color.Transparent,
            shape = CircleShape
        )
        .clickable { onClick(trimColor) }) {
        if (hasNoColor)
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_no_color),
                contentDescription = "No color available",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
    }
}

@Preview(showBackground = true)
@Composable
fun ColorTilePreview() {
    ColorTile(trimColor = TrimColor(0, 0, "TEST COLOR", "22,220,40")) {

    }
}