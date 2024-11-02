package com.jvmlab.platon.eratosthenes

class TwinPrime(private val sieve: BooleanArray) : PrimeType, AnyPrime() {
    override val name = "twin prime"

        override fun process(currentPrime: Int) {
        if (sieve[currentPrime - 2]) {
            println("${currentPrime - 2} and $currentPrime are twin primes")
            count++
        }
    }
}