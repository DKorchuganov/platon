package com.jvmlab.platon.wolfram.battle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jvmlab.platon.wolfram.battle.components.BoardMessage
import com.jvmlab.platon.wolfram.battle.components.FlexibleBoard
import com.jvmlab.platon.wolfram.battle.components.StatusPanel
import com.jvmlab.platon.wolfram.battle.model.BoardSide
import com.jvmlab.platon.wolfram.battle.model.CellPosition
import com.jvmlab.platon.wolfram.battle.model.ChipGridState
import com.jvmlab.platon.wolfram.battle.model.GridConfig

/*
 * This file contains the main screen of the app.
 *
 * In Compose, a function marked with @Composable describes a piece of UI.
 * You can think about it like this:
 * - StatusPanel() describes the text panel and Clear button.
 * - FlexibleBoard() describes the grid area.
 * - WolframBattleApp() puts all these pieces together.
 */


@Composable
fun WolframBattleApp() {
    /*
     * remember { mutableStateOf(...) } stores values inside the Compose UI.
     * When we assign a new value to gridState or message, Compose redraws
     * the parts of the screen that use that value.
     */
    var gridState by remember { mutableStateOf(ChipGridState()) }
    var boardMessage by remember { mutableStateOf(BoardMessage()) }

    // The whole page can scroll vertically if the browser window is short.
    val verticalScrollState = rememberScrollState()

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFF7F7F7)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScrollState)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                StatusPanel(
                    gridState = gridState,
                    boardMessage = boardMessage,
                    onClear = {
                        gridState = gridState.clear()
                        boardMessage = BoardMessage("Board cleared.")
                    },
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    FlexibleBoard(
                        gridState = gridState,
                        onCellClicked = { position ->
                            val result = handleCellClick(
                                currentState = gridState,
                                position = position,
                            )
                            gridState = result.state
                            boardMessage = result.boardMessage
                        },
                    )
                }
            }
        }
    }
}

private data class ClickResult(
    val state: ChipGridState,
    val boardMessage: BoardMessage,
)

private fun handleCellClick(
    currentState: ChipGridState,
    position: CellPosition,
): ClickResult {
    /*
     * Internally, rows and columns start from 0.
     * In messages for the user, rows and columns start from 1.
     */
    val toggleResult = when (position.column) {
        0 -> currentState.toggle(BoardSide.Left, position.row)
        GridConfig.COLUMNS - 1 -> currentState.toggle(BoardSide.Right, position.row)
        else -> return ClickResult(
            state = currentState,
            boardMessage = BoardMessage(
                "Column ${position.column + 1} is not playable. Use column 1 for left chips or column ${GridConfig.COLUMNS} for right chips."
            )
        )
    }

    return ClickResult(
        state = toggleResult.state,
        boardMessage = toggleResult.boardMessage,
    )
}

