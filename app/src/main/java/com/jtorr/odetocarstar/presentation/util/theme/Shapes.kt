package com.jtorr.odetocarstar.presentation.util.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

object Shapes {
    // Sharp strategy - zero rounded corners for performance aesthetic
    val zero: CornerBasedShape
        get() = RoundedCornerShape(0.dp)

    // Clipped corners for performance look (top-right and bottom-left chamfer)
    val chamfered: CornerBasedShape
        get() = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 8.dp,
            bottomStart = 8.dp,
            bottomEnd = 0.dp,
        )

    // Full Shapes set
    val all: Shapes
        get() = Shapes(
            extraSmall = RoundedCornerShape(2.dp),
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(12.dp),
            extraLarge = RoundedCornerShape(16.dp)
        )
}
