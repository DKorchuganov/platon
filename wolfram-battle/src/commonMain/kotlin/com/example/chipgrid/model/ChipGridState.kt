package com.example.chipgrid.model

/*
 * There are only two chip sides in this app:
 * - black chips in the left column
 * - white chips in the right column
 */
enum class ChipSide {
    BlackLeft,
    WhiteRight,
}

/*
 * A small result object for user actions.
 * It contains the new state and a message to show in the UI.
 */
data class ToggleResult(
    val state: ChipGridState,
    val message: String,
)

/*
 * This class is the state of the board.
 *
 * We do not store every cell in the 100 × 50 grid.
 * We only need to remember which rows have chips in the left column
 * and which rows have chips in the right column.
 *
 * Sets are useful here because one row can either have a chip or not.
 */
data class ChipGridState(
    val blackChipRows: Set<Int> = emptySet(),
    val whiteChipRows: Set<Int> = emptySet(),
) {
    val blackCount: Int get() = blackChipRows.size
    val whiteCount: Int get() = whiteChipRows.size

    fun toggle(side: ChipSide, row: Int): ToggleResult {
        require(row in 0 until GridConfig.ROWS) {
            "Row must be between 0 and ${GridConfig.ROWS - 1}, but was $row"
        }

        return when (side) {
            ChipSide.BlackLeft -> toggleBlack(row)
            ChipSide.WhiteRight -> toggleWhite(row)
        }
    }

    fun clear(): ChipGridState = ChipGridState()

    private fun toggleBlack(row: Int): ToggleResult {
        val rowLabel = row + 1

        // If the chip already exists, a click removes it.
        if (row in blackChipRows) {
            return ToggleResult(
                state = copy(blackChipRows = blackChipRows - row),
                message = "Removed black chip from row $rowLabel.",
            )
        }

        // If the side is full, do not add a new chip.
        if (blackChipRows.size >= GridConfig.MAX_CHIPS_PER_SIDE) {
            return ToggleResult(
                state = this,
                message = "Black side already has ${GridConfig.MAX_CHIPS_PER_SIDE} chips. Remove one before adding another.",
            )
        }

        return ToggleResult(
            state = copy(blackChipRows = blackChipRows + row),
            message = "Added black chip to row $rowLabel.",
        )
    }

    private fun toggleWhite(row: Int): ToggleResult {
        val rowLabel = row + 1

        // If the chip already exists, a click removes it.
        if (row in whiteChipRows) {
            return ToggleResult(
                state = copy(whiteChipRows = whiteChipRows - row),
                message = "Removed white chip from row $rowLabel.",
            )
        }

        // If the side is full, do not add a new chip.
        if (whiteChipRows.size >= GridConfig.MAX_CHIPS_PER_SIDE) {
            return ToggleResult(
                state = this,
                message = "White side already has ${GridConfig.MAX_CHIPS_PER_SIDE} chips. Remove one before adding another.",
            )
        }

        return ToggleResult(
            state = copy(whiteChipRows = whiteChipRows + row),
            message = "Added white chip to row $rowLabel.",
        )
    }
}
