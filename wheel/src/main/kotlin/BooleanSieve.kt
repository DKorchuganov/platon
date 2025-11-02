package com.jvmlab.platon.wheel


open class BooleanSieve(size: Int, newEraser: Boolean, parallel: Boolean) :
    AbstractSieve<Array<BooleanArray>>(size, newEraser, parallel) {
    override val sieve = Array(8) { BooleanArray(size) { true } }
    override val currentPrimePosition = createPosition(0, 0)

    init {
        currentPrimePosition.erase()
    }

    override fun createPosition(row: Int, column: Int): Position<Array<BooleanArray>> =
        BooleanPosition(row, column, sieve)

    override fun createColumnEraser(row: Int, step: Int): ColumnEraser =
        BooleanEraser(row, step, sieve)

}