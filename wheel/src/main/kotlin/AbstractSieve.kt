package com.jvmlab.platon.wheel

import kotlin.math.sqrt

abstract class AbstractSieve<T>(size: Int, newEraser: Boolean) : Sieve {
    override val removeComposite =
        if (newEraser) ::removeCompositeNew
        else ::removeCompositeOld

    protected abstract val sieve: T
    protected val columnByRemainder = intArrayOf(
        0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 3, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 0, 0, 0, 0, 0, 7
    )
    private val fullSize = size.toLong() * 30L
    protected val maxFind = sqrt(fullSize.toDouble())
    protected abstract val currentPrimePosition: Position<T>

    final override var hasComposites = true
        protected set

    final override var count = 4
        private set

    override val currentPrime
        get() = currentPrimePosition.value

    abstract fun createPosition(row: Int, column: Int): Position<T>

    abstract fun createColumnEraser(row: Int, step: Int): ColumnEraser

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


    fun removeCompositeNew() {
        if (currentPrimePosition.value > maxFind) {
            hasComposites = false
            return
        }
    }

     open fun removeCompositeOld() {

        if (currentPrimePosition.value > maxFind) {
            hasComposites = false
            return
        }

        val nextNumberPosition = currentPrimePosition.copy()
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


    protected fun removeCompositeColumn(productRow: Int, productColumn: Int) {
        val step = currentPrimePosition.value.toInt()
        val position = createPosition(productRow, productColumn)
        position.erase()
        while (position.nextRow(step)) {
            position.erase()
        }
    }

}