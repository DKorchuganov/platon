package com.jvmlab.platon.wheel

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.time.Duration

class ParallelSieve(size: Int) : BasicSieve(size) {

    private val threadPool = Executors.newFixedThreadPool(8)

    override fun removeComposite() {

        if (! hasComposites) return
        if (currentPrimePosition.value > maxFind) {
            removeCompositeDuration = start.elapsedNow()
            threadPool.shutdown()
            hasComposites = false
            return
        }

        val nextNumberPosition = Position(currentPrimePosition)

        val tasks = mutableListOf<Callable<Duration>>()

        repeat(8) {
            val product = currentPrimePosition.value * nextNumberPosition.value
            val productRow = (product / 30).toInt()
            val productColumn = columnByRemainder[(product % 30).toInt()]

            tasks.add {
                removeCompositeColumn(productRow, sieve[productColumn])
            }

            nextNumberPosition.next()
        }

        threadPool.invokeAll(tasks)
        /*threadPool.invokeAll(tasks).forEach {
            print("${it.get().inWholeMicroseconds} ")
        }
        println()*/
    }
}