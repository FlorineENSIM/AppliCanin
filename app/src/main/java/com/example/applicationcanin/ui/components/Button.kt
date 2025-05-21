package com.example.applicationcanin.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Shape
import com.example.applicationcanin.ui.theme.ColorText
import com.example.applicationcanin.ui.theme.fondBloc
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.unit.dp

@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = fondBloc,
            contentColor = ColorText
        ),
        border = BorderStroke(1.dp, ColorText),
        shape = shape
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}