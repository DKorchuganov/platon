package com.jvmlab.platon.wheel

abstract class Position<T>(protected var row: Int, protected var column: Int, val sieve: T) {
    private val remainderByColumn = longArrayOf(1, 7, 11, 13, 17, 19, 23, 29)
    protected abstract val size: Int

    var value: Long = toNumber()
        private set

    var last = false
        private set

    abstract val isPrime: Boolean

    abstract fun copy(): Position<T>


    abstract fun erase()

    fun next() {
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

    fun nextRow(step: Int): Boolean {
        val newRow = row + step
        if (newRow <= size - 1) {
            row = newRow
            return true
        }
        return false
    }

    private fun toNumber(): Long = row.toLong() * 30 + remainderByColumn[column]
}