package com.jvmlab.platon.eratosthenes

class TwinPrime(private val sieve: BooleanArray) {
    private var count = 0

    fun find(currentPrime: Int) {
        if (sieve[currentPrime - 2]) {
            println("${currentPrime - 2} and $currentPrime are twin primes")
            count++
        }
    }

    fun printCount() = printFound(count, "twin prime")
}