package com.jtor.odetocarstar.presentation.trims

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
    modelId: String
) {
    val rememberedYear by remember { mutableIntStateOf(year.toInt()) }
    val rememberedModelId by remember { mutableIntStateOf(modelId.toInt()) }
    val state: TrimListState by viewModel.state
    val trimDetailState by viewModel.detailState.collectAsState()

    LaunchedEffect(rememberedYear, rememberedModelId) {
        viewModel.getTrims(rememberedYear, rememberedModelId)
    }

    var showSheet by remember { mutableStateOf(false) }

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
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                state.error.isNotBlank() -> {
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

                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.trims) { trim ->
                            TrimListItem(trim = trim) {
                                viewModel.getTrimDetail(trim.id)
                                showSheet = true
                            }
                        }
                    }
                }
            }
        }
    }
}