package com.jvmlab.platon.wheel

import kotlin.collections.lastIndex
import kotlin.math.sqrt

@OptIn(ExperimentalUnsignedTypes::class)
class BinarySieve(private val size: Int) : Sieve {
    private val sieve = UByteArray(size) {255.toUByte()}

    private val columnByRemainder = intArrayOf(
        0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 3, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 0, 0, 0, 0, 0, 7
    )
    private val fullSize = size.toLong() * 30L
    private val maxFind = sqrt(fullSize.toDouble())
    private val currentPrimePosition = Position(0, 0)

    override var hasComposites = true
        private set

    override var count = 4
        private set

    override val currentPrime
        get() = currentPrimePosition.value

    init {
        currentPrimePosition.erase()
    }


    @OptIn(ExperimentalUnsignedTypes::class)
    private inner class Position(row: Int, column: Int) {
        private val remainderByColumn = longArrayOf(1, 7, 11, 13, 17, 19, 23, 29)

        private val checker = ubyteArrayOf(
            1.toUByte(),
            2.toUByte(),
            4.toUByte(),
            8.toUByte(),
            16.toUByte(),
            32.toUByte(),
            64.toUByte(),
            128.toUByte()
        )

        private val eraser = ubyteArrayOf(
            254.toUByte(),
            253.toUByte(),
            251.toUByte(),
            247.toUByte(),
            239.toUByte(),
            223.toUByte(),
            191.toUByte(),
            127.toUByte()
        )

        var row = row
            private set

        var column = column
            private set

        var value: Long = toNumber()
            private set

        var last = false
            private set

        val isPrime
            get() = (sieve[row].and(checker[column]) > 0.toUByte())

        constructor(position: Position) : this(position.row, position.column)

        fun erase() {
            sieve[row] = sieve[row].and(eraser[column])
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
            if (newRow <= sieve.lastIndex) {
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


    private fun removeCompositeColumn(productRow: Int, productColumn: Int ) {
        val step = currentPrimePosition.value.toInt()
        val position = Position(productRow,productColumn)
        position.erase()
        while (position.nextRow(step)) {
            position.erase()
        }
    }

}