package com.jvmlab.platon.wheel


open class BooleanSieve(size: Int) : AbstractSieve<Array<BooleanArray>>(size) {
    override val sieve = Array(8) { BooleanArray(size) { true } }
    override val currentPrimePosition = createPosition(0, 0)

    init {
        currentPrimePosition.erase()
    }

    override fun createPosition(row: Int, column: Int): Position<Array<BooleanArray>> =
        BooleanPosition(row, column, sieve)
}