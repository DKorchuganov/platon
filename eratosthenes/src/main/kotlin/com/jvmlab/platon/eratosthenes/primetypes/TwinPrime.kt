package com.jvmlab.platon.eratosthenes.primetypes

class TwinPrime(private val sieve: BooleanArray) : PrimeType, AnyPrime() {
    override val name = "twin prime"

    override fun process(currentPrime: Int): Boolean {
        if (sieve[currentPrime - 2]) {
            count++
            return true
        }
        return false
    }

    override fun processAndPrint(currentPrime: Int): Boolean {
        if (process(currentPrime)) {
            println("${currentPrime - 2} and $currentPrime are twin primes")
            return true
        }
        return false
    }
}