package com.jvmlab.platon.wolfram.battle.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jvmlab.platon.wolfram.battle.model.CellPosition
import com.jvmlab.platon.wolfram.battle.model.ChipGridState
import com.jvmlab.platon.wolfram.battle.model.GridConfig

@Composable
fun FlexibleBoard(
    gridState: ChipGridState,
    onCellClicked: (CellPosition) -> Unit,
) {
    /*
     * BoxWithConstraints gives us the size that the parent offers.
     * maxWidth changes when the browser window changes.
     *
     * The grid is drawn by ChipGridCanvas. The canvas does not know about
     * browser windows. It only receives a size from Modifier.
     */
    BoxWithConstraints(modifier = Modifier.Companion.fillMaxWidth()) {
        val boardWidth = maxWidth
        val boardAspectRatio: Float = GridConfig.COLUMNS.toFloat() / GridConfig.ROWS.toFloat()
        val boardHeight = boardWidth / boardAspectRatio

        ChipGridCanvas(
            state = gridState,
            onCellClicked = onCellClicked,
            modifier = Modifier.Companion
                .width(boardWidth)
                .height(boardHeight),
        )
    }
}