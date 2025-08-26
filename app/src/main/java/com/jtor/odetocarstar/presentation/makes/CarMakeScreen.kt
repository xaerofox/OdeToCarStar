package com.jtor.odetocarstar.presentation.makes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jtor.odetocarstar.presentation.makes.components.MakeListItem
import com.jtor.odetocarstar.presentation.util.route.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarMakeScreen(
    navController: NavController,
    viewModel: CarMakeViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Ode To Car*")
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        val state = viewModel.state.value
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(state.makes) { make ->
                    MakeListItem(make = make, onItemClick = {
                        navController.navigate(Screen.CarYearScreen.withArgs(make.name))
                    })

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

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .background(Color.Black)
            ) {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "*Only US vehicle information between 2015 - 2020",
                    color = Color.Magenta,
                    fontSize = 10.sp
                )
            }
        }
    }

}