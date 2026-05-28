package com.jvmlab.platon.wolfram.battle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jvmlab.platon.wolfram.battle.components.ChipGridCanvas
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

/*
 * The board is 100 columns and 50 rows by default.
 * This number is 2.0, so the board is twice as wide as it is high.
 * We calculate it from GridConfig so the UI follows the configuration.
 */
private val BoardAspectRatio: Float = GridConfig.COLUMNS.toFloat() / GridConfig.ROWS.toFloat()

@Composable
fun WolframBattleApp() {
    /*
     * remember { mutableStateOf(...) } stores values inside the Compose UI.
     * When we assign a new value to gridState or message, Compose redraws
     * the parts of the screen that use that value.
     */
    var gridState by remember { mutableStateOf(ChipGridState()) }
    var message by remember {
        mutableStateOf("Click the left edge column to toggle left chips, or the right edge column to toggle right chips.")
    }

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
                    message = message,
                    onClear = {
                        gridState = gridState.clear()
                        message = "Board cleared."
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
                            message = result.message
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun FlexibleBoard(
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
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val boardWidth = maxWidth
        val boardHeight = boardWidth / BoardAspectRatio

        ChipGridCanvas(
            state = gridState,
            onCellClicked = onCellClicked,
            modifier = Modifier
                .width(boardWidth)
                .height(boardHeight),
        )
    }
}

@Composable
private fun StatusPanel(
    gridState: ChipGridState,
    message: String,
    onClear: () -> Unit,
) {
    /*
     * Row means: place children from left to right.
     * Column means: place children from top to bottom.
     *
     * Modifier.weight(1f) tells the text column to take all free horizontal
     * space, so the Clear button can stay on the right.
     */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Left chips: ${gridState.count(BoardSide.Left)}/${GridConfig.MAX_CHIPS_PER_SIDE}    " +
                    "Right chips: ${gridState.count(BoardSide.Right)}/${GridConfig.MAX_CHIPS_PER_SIDE}",
                fontWeight = FontWeight.Bold,
            )
            Text(text = message)
            Text(text = "Left rows: ${gridState.rows(BoardSide.Left).toDisplayRows()}")
            Text(text = "Right rows: ${gridState.rows(BoardSide.Right).toDisplayRows()}")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = onClear) {
            Text("Clear")
        }
    }
}

private data class ClickResult(
    val state: ChipGridState,
    val message: String,
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
            message = "Column ${position.column + 1} is not playable. Use column 1 for left chips or column ${GridConfig.COLUMNS} for right chips.",
        )
    }

    return ClickResult(
        state = toggleResult.state,
        message = toggleResult.message,
    )
}

private fun Set<Int>.toDisplayRows(): String =
    if (isEmpty()) "—" else sorted().joinToString { (it + 1).toString() }
