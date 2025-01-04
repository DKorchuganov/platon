package com.jvmlab.platon.eratosthenes.primetypes

import com.jvmlab.platon.eratosthenes.Sieve

class TwinPrime(private val sieve: Sieve ) : PrimeType, AnyPrime() {
    override val name = "twin prime"

    override fun process(currentPrime: Int): Boolean {
        if (sieve.isPrime(currentPrime - 2)) {
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