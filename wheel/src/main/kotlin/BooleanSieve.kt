package com.jvmlab.platon.wheel

import com.jvmlab.platon.cli.BooleanEraser
import com.jvmlab.platon.cli.ColumnEraser


open class BooleanSieve(size: Int) : AbstractSieve<Array<BooleanArray>>(size) {
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