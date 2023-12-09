package com.jtor.odetocarstar.presentation.year

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jtor.odetocarstar.presentation.util.route.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarYearScreen(
    navController: NavController? = null,
    make: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Select a year for $make")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val years = listOf("2015", "2016", "2017", "2018", "2019", "2020")
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(years.size) {
                    OutlinedButton(
                        modifier = Modifier.padding(4.dp),
                        onClick = { navController?.navigate(Screen.CarModelScreen.withArgs(make, years[it])) }
                    ) {
                        Text(text = years[it])
                    }
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