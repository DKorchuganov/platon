package com.jvmlab.platon.wheel

class BooleanEraser(
    private val row: Int,
    private val step: Int,
    private val sieve: Array<BooleanArray>
) : ColumnEraser {

    private val columns = mutableListOf<Int>()

    override fun addColumn(column: Int) {
        columns.add(column)
    }

    override fun erase() {
        for (currentRow in row .. sieve[0].lastIndex step step) {
            columns.forEach {
                sieve[it][currentRow] = false
            }
        }
    }
}