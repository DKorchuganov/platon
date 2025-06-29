package com.jvmlab.platon.wheel


class BooleanPosition(row: Int, column: Int, sieve: Array<BooleanArray>) :
    Position<Array<BooleanArray>>(row, column, sieve) {
    override val size = sieve[0].size

    override val isPrime: Boolean
        get() = sieve[column][row]

    override fun erase() {
        sieve[column][row] = false
    }

    override fun copy(): Position<Array<BooleanArray>> = BooleanPosition(row, column, sieve)

}