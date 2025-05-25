package com.jvmlab.platon.wheel

import kotlin.math.sqrt

open class BasicSieve(private val size: Int) : Sieve {
    protected val sieve = Array(8) { BooleanArray(size) { true } }
    protected val columnByRemainder = intArrayOf(
        0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 3, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 0, 0, 0, 0, 0, 7
    )
    private val fullSize = size.toLong() * 30L
    protected val maxFind = sqrt(fullSize.toDouble())
    protected val currentPrimePosition = Position(0, 0)

    final override var hasComposites = true
        protected set

    final override var count = 4
        private set

    override val currentPrime
        get() = currentPrimePosition.value

    init {
        currentPrimePosition.erase()
    }

    protected inner class Position(row: Int, column: Int) {
        private val remainderByColumn = longArrayOf(1, 7, 11, 13, 17, 19, 23, 29)

        private var row = row
        private var column = column

        var value: Long = toNumber()
            private set

        var last = false
            private set

        val isPrime
            get() = sieve[column][row]

        constructor(position: Position) : this(position.row, position.column)

        fun erase() {
            sieve[column][row] = false
        }

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

        fun nextRow(step: Int): Boolean {
            val newRow = row + step
            if (newRow <= size - 1) {
                row = newRow
                return true
            }
            return false
        }
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

        if (currentPrimePosition.value > maxFind) {
            hasComposites = false
            return
        }

        val nextNumberPosition = Position(currentPrimePosition)
        var product: Long
        var productRow: Int
        var productColumn: Int

        repeat(8) {
            product = currentPrimePosition.value * nextNumberPosition.value
            if (product <= fullSize) {
                productRow = (product / 30).toInt()
                productColumn = columnByRemainder[(product % 30).toInt()]
                removeCompositeColumn(productRow, productColumn)
            }
            nextNumberPosition.next()
        }
    }


    protected fun removeCompositeColumn(productRow: Int, productColumn: Int ) {
        val step = currentPrimePosition.value.toInt()
        val position = Position(productRow,productColumn)
        position.erase()
        while (position.nextRow(step)) {
            position.erase()
        }
    }

}