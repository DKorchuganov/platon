package com.jvmlab.platon.wolfram.battle.model

import androidx.compose.ui.graphics.Color

/*
 * All important board configuration values live here.
 * This makes the project easier to change later.
 */
object GridConfig {
    /** Logical grid height. Rows are shown as 1..50 in the UI. */
    const val ROWS: Int = 50

    /** Logical grid width. Column 1 is the black side, column 100 is the white side. */
    const val COLUMNS: Int = 100

    /** Each side may contain at most 10 chips. */
    const val MAX_CHIPS_PER_SIDE: Int = 10

    /** Fill color for chips in the left column. */
    val LEFT_CHIP_COLOR: Color = Color.Black

    /** Optional outline color for chips in the left column. Null means use the fill color. */
    val LEFT_CHIP_OUTLINE_COLOR: Color? = null

    /** Fill color for chips in the right column. */
    val RIGHT_CHIP_COLOR: Color = Color.White

    /** Optional outline color for chips in the right column. Null means use the fill color. */
    val RIGHT_CHIP_OUTLINE_COLOR: Color? = Color.Black
}
