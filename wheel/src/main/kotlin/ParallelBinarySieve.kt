package com.jvmlab.platon.wheel

import java.util.concurrent.Callable
import java.util.concurrent.Executors

class ParallelBinarySieve(size: Int, newEraser: Boolean) : BinarySieve(size, newEraser) {

    private val threadPool = Executors.newFixedThreadPool(8)

    @OptIn(ExperimentalUnsignedTypes::class)
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
}