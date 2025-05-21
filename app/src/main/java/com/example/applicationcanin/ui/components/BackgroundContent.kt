package com.example.applicationcanin.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.applicationcanin.ui.theme.fondBloc
import com.example.applicationcanin.ui.theme.interieurBloc
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme

@Composable
fun RectangleFond(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    contentPadding: Int = 32,
    sizeStroke: Int = 8,
    content: @Composable () -> Unit
) {
    Surface(
        color = interieurBloc,
        shape = shape,
        border = BorderStroke(sizeStroke.dp, fondBloc),
        modifier = modifier
    ) {
        Box(modifier = Modifier.padding(contentPadding.dp)) {
            content()
        }
    }
}
