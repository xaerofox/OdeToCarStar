package com.jtor.odetocarstar.presentation.trims.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jtor.odetocarstar.domain.model.CarTrimDetail
import com.jtor.odetocarstar.presentation.util.extensions.toCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrimDetailBottomSheet(
    trimDetail: CarTrimDetail?,
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }) {

        Column(modifier = Modifier.fillMaxSize()) {
            trimDetail?.let { detail ->
                TrimElementItem(name = "Year", value = detail.year.toString())
                TrimElementItem(name = "Name", value = detail.name)
                TrimElementItem(name = "Description", value = detail.description)
                TrimElementItem(name = "MSRP", value = detail.msrp.toCurrency())
            }


            Spacer(modifier = Modifier.height(300.dp))
        }
    }
}