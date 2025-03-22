package com.jvmlab.platon.wheel

import kotlin.math.sqrt


private class Indices(var row: Int, var column: Int) {
    private val remainderByColumn = longArrayOf(1, 7, 11, 13, 17, 19, 23, 29)

    fun next() {
        if (column < 7) {
            column++
        } else {
            column = 0
            row++
        }
    }

    fun toNumber(): Long = row.toLong() * 30 + remainderByColumn[column]
}


class Sieve(private val size: Int) {
    private val sieve = Array(8) { BooleanArray(size) { true } }
    private val remainderByColumn = longArrayOf(1, 7, 11, 13, 17, 19, 23, 29)
    private val columnByRemainder = intArrayOf(
        0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 3, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 0, 0, 0, 0, 0, 7
    )
    private val maxFind = sqrt(size.toDouble() * 30)
    private val currentPrimeIndices = Indices(0, 1)

    var count = 4
        private set

    var currentPrime: Long = 7
        private set

    init {
        sieve[0][0] = false
    }

    fun nextPrime(): Boolean {
        currentPrimeIndices.next()

        while (currentPrimeIndices.row < size) {
            while (currentPrimeIndices.column < 8) {
                if (sieve[currentPrimeIndices.column][currentPrimeIndices.row]) {
                    count++
                    currentPrime = currentPrimeIndices.toNumber()
                    return true
                }
                currentPrimeIndices.column++
            }
            currentPrimeIndices.column = 0
            currentPrimeIndices.row ++
        }

        return false
    }


    fun removeComposite() {
        if (currentPrime > maxFind) return

        var nextNumber = currentPrime
        val nextNumberIndices = Indices(currentPrimeIndices.row, currentPrimeIndices.column)
        var product: Long = 0
        var productRow = 0
        var productColumn = 0

        repeat(8) {
            product = currentPrime * nextNumber
            productRow = (product / 30).toInt()
            productColumn = columnByRemainder[(product % 30).toInt()]

            removeCompositeColumn(productRow, productColumn)

            nextNumberIndices.next()
            nextNumber = nextNumberIndices.toNumber()
        }
    }


    private fun removeCompositeColumn(productRow: Int, productColumn: Int) {
        for (row in productRow .. sieve[productColumn].lastIndex step currentPrime.toInt()) {
            sieve[productColumn][row] = false
        }
    }

}