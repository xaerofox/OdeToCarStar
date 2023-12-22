package com.jtor.odetocarstar.presentation.trims.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jtor.odetocarstar.domain.model.CarTrim
import com.jtor.odetocarstar.presentation.util.extensions.toCurrency
import com.jtor.odetocarstar.presentation.util.theme.OdeToCarStarTheme

@Composable
fun TrimListItem(
    trim: CarTrim,
    onItemCLick: (CarTrim) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemCLick(trim) }
                .padding(10.dp)
        ) {
            Text(
                text = trim.description,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "MSRP: ${trim.msrp.toCurrency()}",
                fontSize = 12.sp
            )
            if(trim.invoice > 0) {
                Text(
                    text = "Dealer's cost: ${trim.invoice.toCurrency()}",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrimListItemPreview() {
    OdeToCarStarTheme {
        TrimListItem(trim = CarTrim(
            created = "Today",
            description = "Some car description (idk)",
            id = 1,
            invoice = 21344,
            modelId = 11,
            modified = "12:00:00 mm/dd/yyyy (idk)",
            msrp = 23000,
            name = "Ford Fusion Drippin",
            year = 2015
        ), onItemCLick = {})
    }
}