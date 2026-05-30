package com.jtor.odetocarstar.presentation.year

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.jtor.odetocarstar.presentation.makes.components.customBackgroundSetup
import com.jtor.odetocarstar.presentation.makes.components.findMakeLogo
import com.jtor.odetocarstar.presentation.util.LocalSharedTransitionContext
import com.jtor.odetocarstar.presentation.util.route.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarYearScreen(
    navController: NavController? = null,
    make: String,
) {
    val transitionContext = LocalSharedTransitionContext.current

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
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(transitionContext != null) {
                with(transitionContext.sharedTransitionScope) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .aspectRatio(1f)
                            .background(customBackgroundSetup(make.lowercase()))
                            .padding(8.dp)
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = "image-${make.lowercase()}"),
                                animatedVisibilityScope = transitionContext.animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    spring(
                                        dampingRatio = 0.8f,
                                        stiffness = 380f
                                    )
                                }
                            ),
                        model = findMakeLogo(make.lowercase()),
                        contentDescription = "$make logo",
                        contentScale = ContentScale.Fit,
                        loading = {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(64.dp)
                            )
                        },
                    )
                }
            }

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
    CarYearScreen(make = "TEST",)
}