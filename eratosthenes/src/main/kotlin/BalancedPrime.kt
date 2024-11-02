package com.jvmlab.platon.eratosthenes

class BalancedPrime : PrimeType, AnyPrime() {
    override val name = "balanced prime"
        private var lastPrime = 0
    private var lastLastPrime = 0

    override fun process(currentPrime: Int) {
        if (lastPrime * 2 == lastLastPrime + currentPrime) {
            println("$lastPrime is a balanced prime (between $lastLastPrime and $currentPrime)")
            count++
        }
        lastLastPrime = lastPrime
        lastPrime = currentPrime
    }
}