package com.jvmlab.platon.wolfram.battle.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.jvmlab.platon.wolfram.battle.model.CellPosition
import com.jvmlab.platon.wolfram.battle.model.ChipGridState
import com.jvmlab.platon.wolfram.battle.model.GridConfig
import kotlin.math.floor

/*
 * This component draws the board.
 *
 * We use Canvas because the grid has many cells: 100 × 50 = 5000 cells.
 * Drawing many small boxes as normal UI elements would be more complex.
 * Canvas lets us draw lines and circles directly.
 */
@Composable
fun ChipGridCanvas(
    state: ChipGridState,
    onCellClicked: (CellPosition) -> Unit,
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier
            .border(width = 1.dp, color = Color(0xFF222222))
            .pointerInput(state) {
                detectTapGestures { offset ->
                    /*
                     * offset is the mouse click position inside the canvas.
                     * It is measured in pixels, not in grid cells.
                     *
                     * We convert pixels to a logical row and column:
                     * column = x / cellWidth
                     * row = y / cellHeight
                     */
                    val cellWidth = size.width.toFloat() / GridConfig.COLUMNS
                    val cellHeight = size.height.toFloat() / GridConfig.ROWS

                    val column = floor(offset.x / cellWidth).toInt()
                        .coerceIn(0, GridConfig.COLUMNS - 1)
                    val row = floor(offset.y / cellHeight).toInt()
                        .coerceIn(0, GridConfig.ROWS - 1)

                    onCellClicked(CellPosition(column = column, row = row))
                }
            },
    ) {
        val canvasSize = size
        val cellWidth = canvasSize.width / GridConfig.COLUMNS
        val cellHeight = canvasSize.height / GridConfig.ROWS

        drawRect(color = Color.White, size = canvasSize)

        // Slightly shade the only two columns that accept chips.
        drawRect(
            color = Color(0xFFECECEC),
            topLeft = Offset.Zero,
            size = Size(cellWidth, canvasSize.height),
        )
        drawRect(
            color = Color(0xFFECECEC),
            topLeft = Offset(canvasSize.width - cellWidth, 0f),
            size = Size(cellWidth, canvasSize.height),
        )

        drawGridLines(canvasSize = canvasSize, cellWidth = cellWidth, cellHeight = cellHeight)
        drawChips(state = state, canvasSize = canvasSize, cellWidth = cellWidth, cellHeight = cellHeight)
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawGridLines(
    canvasSize: Size,
    cellWidth: Float,
    cellHeight: Float,
) {
    val thinLine = Color(0xFFD8D8D8)
    val majorLine = Color(0xFF9E9E9E)

    /*
     * Thin lines show every cell.
     * Darker major lines every 10 cells make the big board easier to read.
     */
    for (column in 0..GridConfig.COLUMNS) {
        val x = column * cellWidth
        val isMajor = column % 10 == 0 || column == GridConfig.COLUMNS
        drawLine(
            color = if (isMajor) majorLine else thinLine,
            start = Offset(x, 0f),
            end = Offset(x, canvasSize.height),
            strokeWidth = if (isMajor) 1.2f else 0.5f,
        )
    }

    for (row in 0..GridConfig.ROWS) {
        val y = row * cellHeight
        val isMajor = row % 10 == 0 || row == GridConfig.ROWS
        drawLine(
            color = if (isMajor) majorLine else thinLine,
            start = Offset(0f, y),
            end = Offset(canvasSize.width, y),
            strokeWidth = if (isMajor) 1.2f else 0.5f,
        )
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawChips(
    state: ChipGridState,
    canvasSize: Size,
    cellWidth: Float,
    cellHeight: Float,
) {
    /*
     * The chip radius is based on the current cell size.
     * So when the browser window becomes wider or narrower, chips resize too.
     */
    val radius = minOf(cellWidth, cellHeight) * 0.42f
    val leftColumnCenterX = cellWidth / 2f
    val rightColumnCenterX = canvasSize.width - cellWidth / 2f

    state.blackChipRows.forEach { row ->
        val center = Offset(
            x = leftColumnCenterX,
            y = (row + 0.5f) * cellHeight,
        )
        drawCircle(color = Color.Black, radius = radius, center = center)
    }

    state.whiteChipRows.forEach { row ->
        val center = Offset(
            x = rightColumnCenterX,
            y = (row + 0.5f) * cellHeight,
        )
        drawCircle(color = Color.White, radius = radius, center = center)
        drawCircle(
            color = Color.Black,
            radius = radius,
            center = center,
            style = Stroke(width = 1.5f),
        )
    }
}
