package com.jtor.odetocarstar.presentation.year

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CarYearScreen(make: String) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Select a year for ${make}",
            fontSize = 20.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CarYearScreenPreview() {
    CarYearScreen(make = "TEST")
}