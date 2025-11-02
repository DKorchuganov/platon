package com.jvmlab.platon.wheel

import java.util.concurrent.Callable
import java.util.concurrent.Executors

class ParallelBooleanSieve(size: Int, newEraser: Boolean) : BooleanSieve(size, newEraser) {

    private val threadPool = Executors.newFixedThreadPool(8)

    override fun removeCompositeOld() {

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


    override fun removeCompositeNew() {
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

