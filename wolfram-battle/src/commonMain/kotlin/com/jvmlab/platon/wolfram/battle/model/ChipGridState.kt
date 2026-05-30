package com.jvmlab.platon.wolfram.battle.model

import com.jvmlab.platon.wolfram.battle.components.BoardMessage

/*
 * One side only needs to remember which rows contain chips.
 */
data class BoardSideState(
    val chipRows: Set<Int> = emptySet(),
) {
    val count: Int get() = chipRows.size

    fun hasChip(row: Int): Boolean = row in chipRows

    fun addChip(row: Int): BoardSideState = copy(chipRows = chipRows + row)

    fun removeChip(row: Int): BoardSideState = copy(chipRows = chipRows - row)
}

/*
 * This class is the state of the board.
 *
 * We do not store every cell in the 100 × 50 grid.
 * We only need to remember the state of each playable side.
 *
 * Sets are useful here because one row can either have a chip or not.
 */
data class ChipGridState(
    val left: BoardSideState = BoardSideState(),
    val right: BoardSideState = BoardSideState(),
) {
    fun rows(side: BoardSide): Set<Int> = sideState(side).chipRows

    fun count(side: BoardSide): Int = sideState(side).count

    fun toggle(side: BoardSide, row: Int): ToggleResult {
        require(row in 0 until GridConfig.ROWS) {
            "Row must be between 0 and ${GridConfig.ROWS - 1}, but was $row"
        }

        val currentSide = sideState(side)
        val rowLabel = row + 1

        // If the chip already exists, a click removes it.
        if (currentSide.hasChip(row)) {
            return ToggleResult(
                state = withSideState(side, currentSide.removeChip(row)),
                boardMessage = BoardMessage("Removed ${side.label} chip from row $rowLabel.", side)
            )
        }

        // If the side is full, do not add a new chip.
        if (currentSide.count >= GridConfig.MAX_CHIPS_PER_SIDE) {
            return ToggleResult(
                state = this,
                boardMessage = BoardMessage(
                    "${side.sentenceLabel} side already has ${GridConfig.MAX_CHIPS_PER_SIDE} chips. Remove one before adding another.",
                    side
                )
            )
        }

        return ToggleResult(
            state = withSideState(side, currentSide.addChip(row)),
            boardMessage = BoardMessage("Added ${side.label} chip to row $rowLabel.", side)
        )
    }

    fun clear(): ChipGridState = ChipGridState()

    private fun sideState(side: BoardSide): BoardSideState =
        when (side) {
            BoardSide.Left -> left
            BoardSide.Right -> right
        }

    private fun withSideState(side: BoardSide, sideState: BoardSideState): ChipGridState =
        when (side) {
            BoardSide.Left -> copy(left = sideState)
            BoardSide.Right -> copy(right = sideState)
        }
}
