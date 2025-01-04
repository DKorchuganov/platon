package com.jvmlab.platon.eratosthenes.primetypes

import com.jvmlab.platon.eratosthenes.Sieve
import com.jvmlab.platon.eratosthenes.toDigits

class EmirpPrime(private val sieve: Sieve) : PrimeType, AnyPrime() {
    override val name = "emirp prime"
    private var emirp = 0

    override fun process(currentPrime: Int): Boolean {
        emirp = 0
        toDigits(currentPrime).forEach {
            emirp = emirp * 10 + it
        }
        if ((currentPrime > emirp) && sieve.isPrime(emirp)) {
            count++
            return true
        }
        return false
    }

    override fun processAndPrint(currentPrime: Int): Boolean {
        if (process(currentPrime)) {
            println("$currentPrime and $emirp are emirp primes")
            return true
        }
        return false
    }

}