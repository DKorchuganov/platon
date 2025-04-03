package com.jvmlab.platon.wheel

import kotlin.math.sqrt


open class BasicSieve(private val size: Int) : Sieve {
    private val sieve = Array(8) { BooleanArray(size) { true } }
    protected val columnByRemainder = intArrayOf(
        0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 3, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 0, 0, 0, 0, 0, 7
    )
    protected val maxFind = sqrt(size.toDouble() * 30)
    protected val currentPrimePosition = Position(0, 1)

    final override var count = 4
        private set

    override val currentPrime
        get() = currentPrimePosition.value

    init {
        sieve[0][0] = false
    }

    protected inner class Position(row: Int, column: Int) {
        private val remainderByColumn = longArrayOf(1, 7, 11, 13, 17, 19, 23, 29)

        var row = row
            private set

        var column = column
            private set

        var value: Long = toNumber()
            private set

        var last = false
            private set

        val isPrime
            get() = sieve[column][row]

        constructor(position: Position) : this(position.row, position.column)

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

        private fun toNumber(): Long = row.toLong() * 30 + remainderByColumn[column]
    }


    override fun nextPrime(): Boolean {
        currentPrimePosition.next()

        while (! currentPrimePosition.last) {
            if (currentPrimePosition.isPrime) {
                count++
                return true
            }
            currentPrimePosition.next()
        }

        return false
    }


    override fun removeComposite() {
        if (currentPrimePosition.value > maxFind) return

        val nextNumberPosition = Position(currentPrimePosition)
        var product: Long
        var productRow: Int
        var productColumn: Int

        repeat(8) {
            product = currentPrimePosition.value * nextNumberPosition.value
            productRow = (product / 30).toInt()
            productColumn = columnByRemainder[(product % 30).toInt()]

            removeCompositeColumn(productRow, productColumn)
            nextNumberPosition.next()
        }
    }


    protected fun removeCompositeColumn(productRow: Int, productColumn: Int) {
        for (row in productRow .. sieve[productColumn].lastIndex step currentPrimePosition.value.toInt()) {
            sieve[productColumn][row] = false
        }
    }

}