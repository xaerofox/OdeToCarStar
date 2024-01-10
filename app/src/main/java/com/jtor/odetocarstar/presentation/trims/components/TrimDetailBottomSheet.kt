package com.jtor.odetocarstar.presentation.trims.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jtor.odetocarstar.domain.model.CarTrimDetail
import com.jtor.odetocarstar.presentation.trims.TrimDetailState
import com.jtor.odetocarstar.presentation.util.extensions.toCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrimDetailBottomSheet(
    trimDetailState: TrimDetailState?,
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }) {

        Column(modifier = Modifier.fillMaxSize()) {
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
            }


            Spacer(modifier = Modifier.height(300.dp))
        }
    }
}