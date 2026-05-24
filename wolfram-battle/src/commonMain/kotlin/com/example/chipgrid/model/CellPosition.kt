package com.example.chipgrid.model

/*
 * A CellPosition is the logical place that the user clicked.
 *
 * Important: this app uses zero-based numbers in the code.
 * The first column is 0, and the first row is 0.
 * In text messages for the user, we add 1 and show 1-based numbers.
 */
data class CellPosition(
    /** Zero-based column index: 0..99 by default. */
    val column: Int,

    /** Zero-based row index: 0..49 by default. */
    val row: Int,
)
