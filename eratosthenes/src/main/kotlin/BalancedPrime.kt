package com.jvmlab.platon.eratosthenes

class BalancedPrime : PrimeType, AnyPrime() {
    override val name = "balanced prime"
    private var lastPrime = 0
    private var lastLastPrime = 0

    override fun process(currentPrime: Int): Boolean {
        if (lastPrime * 2 == lastLastPrime + currentPrime) {
            count++
            return true
        }
        lastLastPrime = lastPrime
        lastPrime = currentPrime
        return false
    }

    override fun processAndPrint(currentPrime: Int): Boolean {
        if (process(currentPrime)) {
            println("$lastPrime is a balanced prime (between $lastLastPrime and $currentPrime)")
            return true
        }
        return false
    }
}