package com.jvmlab.platon.eratosthenes

class TwinPrime(private val sieve: BooleanArray) : PrimeType {
    private var count = 0

    override fun find(currentPrime: Int) {
        if (sieve[currentPrime - 2]) {
            println("${currentPrime - 2} and $currentPrime are twin primes")
            count++
        }
    }

    override fun printCount() = printFound(count, "twin prime")
}