package com.example.applicationcanin.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.applicationcanin.ui.theme.fondBloc
import com.example.applicationcanin.ui.theme.interieurBloc
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun BlocText(
    text: String,
    modifier: Modifier = Modifier,
    contentPadding: Int = 16,
    sizeStroke: Int = 6
) {
    Surface(
        color = fondBloc,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(contentPadding.dp),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
}
