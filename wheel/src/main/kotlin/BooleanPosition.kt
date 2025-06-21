package com.jvmlab.platon.wheel


class BooleanPosition(row: Int, column: Int, val sieve: Array<BooleanArray>) : Position {
    private val size = sieve[0].size
    private val remainderByColumn = longArrayOf(1, 7, 11, 13, 17, 19, 23, 29)

    private var row = row
    private var column = column

    override var value: Long = toNumber()
        private set

    override var last = false
        private set

    override val isPrime: Boolean
        get() = sieve[column][row]

    constructor(position: BooleanPosition) : this(position.row, position.column, position.sieve)

    override fun erase() {
        sieve[column][row] = false
    }

    override fun next() {
        if (column < 7) {
            column++
        } else {
            if (row == size - 1) {
                last = true
                return
            }
            column = 0
            row++
        }
        value = toNumber()
    }

    private fun toNumber(): Long = row.toLong() * 30 + remainderByColumn[column]

    override fun nextRow(step: Int): Boolean {
        val newRow = row + step
        if (newRow <= size - 1) {
            row = newRow
            return true
        }
        return false
    }

}