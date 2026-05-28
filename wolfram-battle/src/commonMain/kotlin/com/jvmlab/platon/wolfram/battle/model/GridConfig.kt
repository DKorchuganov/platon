package com.jvmlab.platon.wolfram.battle.model

import androidx.compose.ui.graphics.Color

data class ChipStyle(
    val fillColor: Color,
    val outlineColor: Color? = null,
) {
    val resolvedOutlineColor: Color get() = outlineColor ?: fillColor
}

/*
 * All important board configuration values live here.
 * This makes the project easier to change later.
 */
object GridConfig {
    /** Logical grid height. Rows are shown as 1..50 in the UI. */
    const val ROWS: Int = 50

    /** Logical grid width. Column 1 is the left side, column 100 is the right side. */
    const val COLUMNS: Int = 100

    /** Each side may contain at most 10 chips. */
    const val MAX_CHIPS_PER_SIDE: Int = 10

    /** Chip style for the left side. */
    val LEFT_CHIP_STYLE: ChipStyle = ChipStyle(fillColor = Color.Black)

    /** Chip style for the right side. */
    val RIGHT_CHIP_STYLE: ChipStyle = ChipStyle(
        fillColor = Color.White,
        outlineColor = Color.Black,
    )
}
