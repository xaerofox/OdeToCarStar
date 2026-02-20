package com.jtor.odetocarstar.presentation.trims.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jtor.odetocarstar.data.model.CarTrimDetail
import com.jtor.odetocarstar.data.model.Make
import com.jtor.odetocarstar.data.model.MakeModel
import com.jtor.odetocarstar.data.model.TrimColor
import com.jtor.odetocarstar.presentation.trims.TrimDetailState
import com.jtor.odetocarstar.presentation.util.extensions.toCurrency
import com.jtor.odetocarstar.presentation.util.theme.OdeToCarStarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrimDetailBottomSheet(
    trimDetailState: TrimDetailState?,
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(4.dp)
        ) {
            if (trimDetailState!!.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            if (trimDetailState.error.isNotBlank())
                Text(text = trimDetailState.error)

            trimDetailState.detail?.let {

                TrimElementItem(name = "Year", value = it.year.toString())
                TrimElementItem(name = "Name", value = it.name)
                TrimElementItem(name = "Description", value = it.description)
                TrimElementItem(name = "MSRP", value = it.msrp.toCurrency())

                DetailSection(headerTitle = "Body", content = {
                    Column {
                        TrimElementItem(name = "Length", value = it.trimBody?.length)
                        TrimElementItem(name = "Height", value = it.trimBody?.height)
                        TrimElementItem(name = "Cargo Capacity", value = it.trimBody?.cargoCapacity)
                        TrimElementItem(
                            name = "Max Cargo Capacity",
                            value = it.trimBody?.maxCargoCapacity
                        )
                        TrimElementItem(
                            name = "Max Towing Capacity",
                            value = it.trimBody?.maxTowingCapacity
                        )
                        TrimElementItem(
                            name = "Curb Weight",
                            value = "${it.trimBody?.curbWeight} lbs"
                        )
                        TrimElementItem(
                            name = "Gross Weight",
                            value = "${it.trimBody?.grossWeight} lbs"
                        )
                        TrimElementItem(name = "Doors", value = it.trimBody?.doors.toString())
                    }
                })

                DetailSection(headerTitle = "Engine", content = {
                    Column {
                        TrimElementItem(name = "Engine Type", value = it.trimEngine?.engineType)
                        TrimElementItem(name = "Fuel Type", value = it.trimEngine?.fuelType)
                        TrimElementItem(name = "Cylinders", value = it.trimEngine?.cylinders)
                        TrimElementItem(name = "Drive Type", value = it.trimEngine?.driveType)
                        TrimElementItem(
                            name = "Horsepower",
                            value = "${it.trimEngine?.horsepowerHp} HP"
                        )
                        TrimElementItem(
                            name = "Horsepower RPM",
                            value = "${it.trimEngine?.horsepowerRpm} RPM"
                        )
                        TrimElementItem(name = "Transmission", value = it.trimEngine?.transmission)
                    }
                })

                if (it.trimExteriorColors.count() > 0) {
                    TrimColorPalette(
                        title = "Exterior Colors",
                        trimColors = it.trimExteriorColors,
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.CenterHorizontally),
                        text = "No exterior colors",
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                }
                if(it.trimInteriorColors.count() > 0) {
                    TrimColorPalette(
                        title = "Interior Colors",
                        trimColors = it.trimInteriorColors,
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.CenterHorizontally),
                        text = "No interior colors",
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TrimDetailBottomSheetPreview() {
    val state = TrimDetailState(
        detail = CarTrimDetail(
            created = "Today",
            description = "Test description",
            id = 0,
            invoice = 123,
            makeModel = MakeModel(
                id = 0,
                make = Make(0, "Altas"),
                makeId = 0,
                name = "Gortys"
            ),
            modelId = 0,
            trimBody = null,
            trimEngine = null,
            trimExteriorColors = listOf(
                TrimColor(
                    id = -1,
                    trimId = 0,
                    name = "Flamin' Hot Red",
                    rgb = "254,22,90"
                ),
                TrimColor(
                    id = -1,
                    trimId = 1,
                    name = "Electric Blue",
                    rgb = "20,11,255"
                ),
                TrimColor(
                    id = -1,
                    trimId = 2,
                    name = "Unreal Green",
                    rgb = "22,220,40"
                )
            ),
            trimInteriorColors = emptyList(),
            trimMileage = null,
            modified = "Never",
            msrp = 1000000,
            name = "Gortys",
            year = 2026
        )
    )

    OdeToCarStarTheme(darkTheme = true) {
        TrimDetailBottomSheet(
            onDismiss = {},
            trimDetailState = state
        )
    }
}