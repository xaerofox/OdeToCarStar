package com.jtor.odetocarstar.presentation.trims

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jtor.odetocarstar.presentation.trims.components.TrimDetailBottomSheet
import com.jtor.odetocarstar.presentation.trims.components.TrimListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarTrimScreen(
    navController: NavController? = null,
    viewModel: CarTrimViewModel = hiltViewModel(),
    modelName: String,
    year: String,
    modelId: String,
    make: String
) {
    val rememberedYear by remember { mutableIntStateOf(year.toInt()) }
    val rememberedModelId by remember { mutableIntStateOf(modelId.toInt()) }
    val trimDetailState by viewModel.detailState.collectAsState()
    val carFactState by viewModel.carFactState.collectAsState()

    LaunchedEffect(rememberedYear, rememberedModelId) {
        viewModel.getTrims(rememberedYear, rememberedModelId)
    }

    var showSheet by remember { mutableStateOf(false) }
    var isButtonVisible by remember { mutableStateOf(true) }

    if (showSheet) {
        TrimDetailBottomSheet(trimDetailState = trimDetailState) {
            showSheet = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "$modelName Trims")
                },
                colors = topAppBarColors(
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
        val state = viewModel.state.value //Could be an issue, use collect as state
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                item {
                    if (isButtonVisible) {
                        Button(
                            modifier = Modifier
                                .align(Alignment.Center),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                            onClick = {
                                viewModel.genCarFact(year.toInt(), make, modelName)
                            }) {
                            Text(text = "Did you know?", color = Color.Black)
                        }
                    }
                }

                item {
                    if (carFactState.isLoading) {
                        isButtonVisible = true
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(10.dp)
                                .align(Alignment.Center)
                        )
                    }

                    if (carFactState.response.isNotBlank()) {
                        isButtonVisible = false
                        Card(
                            modifier = Modifier
                                .padding(10.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Yellow)
                        ) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = carFactState.response,
                                color = Color.Black
                            )
                        }
                    }
                }

                items(state.trims) { trim ->
                    TrimListItem(trim = trim) {
                        viewModel.getTrimDetail(trim.id)
                        showSheet = true
                    }
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
        }
    }
}