package com.jvmlab.platon.wolfram.battle.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jvmlab.platon.wolfram.battle.model.GridConfig

@Composable
internal fun SideStatusColumn(
    title: String,
    chipCount: Int,
    rows: Set<Int>,
    modifier: Modifier = Modifier.Companion,
    textAlign: TextAlign = TextAlign.Companion.Start,
    horizontalAlignment: Alignment.Horizontal = Alignment.Companion.Start,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Companion.Bold,
            textAlign = textAlign,
            modifier = Modifier.Companion.fillMaxWidth(),
        )
        Text(
            text = "Chips: $chipCount/${GridConfig.MAX_CHIPS_PER_SIDE}",
            textAlign = textAlign,
            modifier = Modifier.Companion.fillMaxWidth(),
        )
        Text(
            text = "Rows: ${rows.toDisplayRows()}",
            textAlign = textAlign,
            modifier = Modifier.Companion.fillMaxWidth(),
        )
    }
}

private fun Set<Int>.toDisplayRows(): String =
    if (isEmpty()) "—" else sorted().joinToString { (it + 1).toString() }