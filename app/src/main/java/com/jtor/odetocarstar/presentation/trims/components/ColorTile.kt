package com.jtor.odetocarstar.presentation.trims.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jtor.odetocarstar.domain.model.TrimColor

@Composable
fun ColorTile(trimColor: TrimColor, onClick: (TrimColor) -> Unit) {
    val colorArr = trimColor.rgb.split(',').map { it.toInt() }
    val color = Color(red = colorArr[0], green = colorArr[1], blue = colorArr[2])
    Box(modifier = Modifier
        .clip(CircleShape)
        .size(30.dp)
        .background(color)

        .clickable { onClick(trimColor) })
}