package com.jtorr.odetocarstar.presentation.models

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.jtorr.odetocarstar.data.model.CarModel
import com.jtorr.odetocarstar.presentation.models.components.ModelListItem
import com.jtorr.odetocarstar.presentation.util.route.Screen
import com.jtorr.odetocarstar.presentation.util.theme.OdeToCarStarTheme
import androidx.compose.runtime.collectAsState


@Composable
fun CarModelScreen(
    navController: NavController,
    viewModel: CarModelViewModel = hiltViewModel(),
    make: String,
    year: String,
) {
    val rememberYear by remember { mutableIntStateOf(year.toInt()) }
    val rememberMake by remember { mutableStateOf(make) }

    LaunchedEffect(rememberYear, rememberMake) {
        viewModel.getModels(year.toInt(), make)
    }

    CarModelScreen(
        onBackClick = { navController.popBackStack() },
        onModelClick = { model ->
            navController.navigate(
                Screen.CarTrimScreen.withArgs(
                    model.name,
                    model.id.toString(),
                    year
                )
            )
        },
        state = viewModel.state.collectAsState().value,
        make = rememberMake,
        year = rememberYear
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CarModelScreen(
    onBackClick: () -> Unit,
    onModelClick: (CarModel) -> Unit,
    state: ModelListState,
    make: String,
    year: Int
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "$year $make Models")
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.models) { model ->
                    ModelListItem(
                        make = make,
                        model = model,
                        onItemClick = onModelClick,
                    )
                }
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            if (state.models.isEmpty() && !state.isLoading) {
                Text(
                    text = "No models are available",
                    color = Color.Yellow,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CarModelScreenPreview() {
    val carModel = CarModel(
        id = 1,
        makeId = 1,
        name = "Scooter"
    )

    OdeToCarStarTheme(darkTheme = true) {
        CarModelScreen(
            make = "FORD",
            year = 2026,
            onBackClick = { },
            onModelClick = { },
            state = ModelListState(
                models = listOf(
                    carModel
                )
            )
        )
    }
}