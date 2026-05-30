package com.jvmlab.platon.wolfram.battle.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jvmlab.platon.wolfram.battle.model.BoardSide
import com.jvmlab.platon.wolfram.battle.model.ChipGridState
import com.jvmlab.platon.wolfram.battle.model.GridConfig

@Composable
fun StatusPanel(
    gridState: ChipGridState,
    boardMessage: BoardMessage,
    onClear: () -> Unit,
) {
    /*
     * The status panel has three columns:
     * - left side state
     * - game rules and actions
     * - right side state
     */
    Row(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .background(Color.Companion.White)
            .padding(12.dp),
        verticalAlignment = Alignment.Companion.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SideStatusColumn(
            title = "Left side",
            chipCount = gridState.count(BoardSide.Left),
            rows = gridState.rows(BoardSide.Left),
            message = if (boardMessage.side == BoardSide.Left || boardMessage.side == null) boardMessage.message else "",
            modifier = Modifier.Companion.weight(1f),
        )

        Column(
            modifier = Modifier.Companion.weight(1.4f),
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "Rules",
                fontWeight = FontWeight.Companion.Bold,
            )
            Text(
                text = "Use column 1 for left chips and column ${GridConfig.COLUMNS} for right chips. " +
                        "Each side can hold ${GridConfig.MAX_CHIPS_PER_SIDE} chips. " +
                        "Click the left edge column to toggle left chips, or the right edge column to toggle right chips.",
                textAlign = TextAlign.Companion.Start,
                modifier = Modifier.Companion.fillMaxWidth(),
            )
           /* Text(
                text = message,
                textAlign = TextAlign.Companion.Start,
                modifier = Modifier.Companion.fillMaxWidth(),
            )*/
            Button(onClick = onClear) {
                Text("Clear")
            }
        }

        SideStatusColumn(
            title = "Right side",
            chipCount = gridState.count(BoardSide.Right),
            rows = gridState.rows(BoardSide.Right),
            message = if (boardMessage.side == BoardSide.Right || boardMessage.side == null) boardMessage.message else "",
            modifier = Modifier.Companion.weight(1f),
            textAlign = TextAlign.Companion.End,
            horizontalAlignment = Alignment.Companion.End,
        )
    }
}

