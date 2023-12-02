package com.jtor.odetocarstar.presentation.year

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CarYearScreen(
    navController: NavController? = null,
    make: String
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select a year for $make",
            fontSize = 20.sp
        )
        val years = listOf("2015", "2016", "2017", "2018", "2019", "2020")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(years.size) {
                OutlinedButton(
                    modifier = Modifier.padding(4.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = years[it])
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CarYearScreenPreview() {
    CarYearScreen(make = "TEST")
}