package com.jtorr.odetocarstar.presentation.makes

import androidx.compose.animation.core.spring
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.jtorr.odetocarstar.data.model.CarMake
import com.jtorr.odetocarstar.presentation.makes.components.VelocityMakeListItem
import com.jtorr.odetocarstar.presentation.util.LocalSharedTransitionContext
import com.jtorr.odetocarstar.presentation.util.route.Screen
import com.jtorr.odetocarstar.presentation.util.theme.OdeToCarStarTheme

@Composable
fun VelocityMakeScreen(
    navController: NavController,
    viewModel: CarMakeViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMakes()
    }

    VelocityMakeScreen(
        onMakeClick = { make ->
            navController.navigate(Screen.CarYearScreen.withArgs(make.name))
        },
        state = viewModel.state.collectAsState().value
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VelocityMakeScreen(
    onMakeClick: (CarMake) -> Unit,
    state: MakeListState
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val transitionContext = LocalSharedTransitionContext.current

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "ODE TO CAR*",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primaryFixedDim
                ),
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (state) {
                is MakeListState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }

                MakeListState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primaryFixedDim
                )

                is MakeListState.Success -> {
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(state.makes) { make ->
                            if (transitionContext != null) {
                                with(transitionContext.sharedTransitionScope) {
                                    VelocityMakeListItem(
                                        modifier = Modifier
                                            .sharedElement(
                                                sharedContentState = rememberSharedContentState(key = "image-${make.name.lowercase()}"),
                                                animatedVisibilityScope = transitionContext.animatedVisibilityScope,
                                                boundsTransform = { _, _ ->
                                                    spring(
                                                        dampingRatio = 0.8f,
                                                        stiffness = 380f
                                                    )
                                                }
                                            ),
                                        make = make,
                                        onItemClick = onMakeClick,
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "*ONLY US VEHICLE INFORMATION BETWEEN 2015 - 2020",
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun VelocityMakeScreenPreview() {
    val carMake = CarMake(id = 0, name = "Ford")

    OdeToCarStarTheme(darkTheme = true) {
        VelocityMakeScreen(
            onMakeClick = {},
            state = MakeListState.Success(
                makes = listOf(
                    carMake,
                    carMake.copy(id = 1, name = "Honda"),
                    carMake.copy(id = 2, name = "Nissan"),
                    carMake.copy(id = 3, name = "BMW"),
                )
            )
        )
    }
}
