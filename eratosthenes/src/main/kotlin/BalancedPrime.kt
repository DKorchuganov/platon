package com.jvmlab.platon.eratosthenes

class BalancedPrime : PrimeType {
    private var count = 0
    private var lastPrime = 0
    private var lastLastPrime = 0


    override fun find(currentPrime: Int) {
        if (lastPrime * 2 == lastLastPrime + currentPrime) {
            println("$lastPrime is a balanced prime (between $lastLastPrime and $currentPrime)")
            count++
        }
        lastLastPrime = lastPrime
        lastPrime = currentPrime
    }

    override fun printCount() = printFound(count, "balanced prime")

}