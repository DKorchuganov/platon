package com.jvmlab.platon.eratosthenes

class BalancedPrime {
    private var count = 0

    fun find(lastLastPrime: Int, lastPrime: Int, currentPrime: Int) {
        if (lastPrime * 2 == lastLastPrime + currentPrime) {
            println("$lastPrime is a balanced prime (between $lastLastPrime and $currentPrime)")
            count++
        }
    }

    fun printCount() = printFound(count, "balanced prime")

}