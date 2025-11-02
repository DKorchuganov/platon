package com.jvmlab.platon.wheel

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.math.sqrt

abstract class AbstractSieve<T>(size: Int, newEraser: Boolean, parallel: Boolean) : Sieve {
    override val removeComposite =
        if (parallel) {
            if (newEraser) ::removeCompositeNewParallel
            else ::removeCompositeOldParallel
        } else {
            if (newEraser) ::removeCompositeNew
            else ::removeCompositeOld
        }
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


    open fun removeCompositeNew() {
        if (currentPrimePosition.value > maxFind) {
            hasComposites = false
            return
        }

        val nextNumberPosition = currentPrimePosition.copy()
        var product: Long
        var productRow: Int
        var productColumn: Int

        repeat(8) {
            product = currentPrime * nextNumberPosition.value
            if (product <= fullSize) {
                productRow = (product / 30).toInt()
                productColumn = columnByRemainder[(product % 30).toInt()]
                val columnEraser = createColumnEraser(productRow, currentPrime.toInt())
                columnEraser.addColumn(productColumn)
                columnEraser.erase()
            }
            nextNumberPosition.next()
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

    private val threadPool = Executors.newFixedThreadPool(8)

     fun removeCompositeOldParallel() {

        if (currentPrimePosition.value > maxFind) {
            threadPool.shutdown()
            hasComposites = false
            return
        }

        val nextNumberPosition = currentPrimePosition.copy()

        val tasks = mutableListOf<Callable<Unit>>()

        repeat(8) {
            val product = currentPrimePosition.value * nextNumberPosition.value
            val productRow = (product / 30).toInt()
            val productColumn = columnByRemainder[(product % 30).toInt()]

            tasks.add {
                removeCompositeColumn(productRow, productColumn)
            }

            nextNumberPosition.next()
        }

        threadPool.invokeAll(tasks)

    }

     fun removeCompositeNewParallel() {
        if (currentPrimePosition.value > maxFind) {
            threadPool.shutdown()
            hasComposites = false
            return
        }

        val nextNumberPosition = currentPrimePosition.copy()

        val erasers = mutableMapOf<Int, ColumnEraser>()
        val tasks = mutableListOf<Callable<Unit>>()

        repeat(8) {
            val product = currentPrimePosition.value * nextNumberPosition.value
            val productRow = (product / 30).toInt()
            val productColumn = columnByRemainder[(product % 30).toInt()]
            val remainder = (productRow % currentPrime).toInt()

            erasers.getOrPut(remainder) {
                createColumnEraser(productRow, currentPrime.toInt())
            }.addColumn(productColumn)

            nextNumberPosition.next()
        }

        erasers.forEach {
            tasks.add {
                it.value.erase()
            }
        }

        threadPool.invokeAll(tasks)
    }

}