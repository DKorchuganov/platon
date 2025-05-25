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
    private val currentPrimePosition = BinaryPosition(0, 0, sieve)

    override var hasComposites = true
        private set

    override var count = 4
        private set

    override val currentPrime
        get() = currentPrimePosition.value

    init {
        currentPrimePosition.erase()
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

        val nextNumberPosition = BinaryPosition(currentPrimePosition)
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
        val position = BinaryPosition(productRow, productColumn, sieve)
        position.erase()
        while (position.nextRow(step)) {
            position.erase()
        }
    }

}