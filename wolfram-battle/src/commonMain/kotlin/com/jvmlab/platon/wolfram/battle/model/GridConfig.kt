package com.jvmlab.platon.wolfram.battle.model

/*
 * All important business numbers for the board live here.
 * This makes the project easier to change later.
 */
object GridConfig {
    /** Logical grid height. Rows are shown as 1..50 in the UI. */
    const val ROWS: Int = 50

    /** Logical grid width. Column 1 is the black side, column 100 is the white side. */
    const val COLUMNS: Int = 100

    /** Each side may contain at most 10 chips. */
    const val MAX_CHIPS_PER_SIDE: Int = 10
}
