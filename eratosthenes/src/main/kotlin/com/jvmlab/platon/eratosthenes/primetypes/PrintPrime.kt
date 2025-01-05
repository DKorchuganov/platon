package com.jvmlab.platon.eratosthenes.primetypes

import com.jvmlab.platon.eratosthenes.Sieve

class PrintPrime(
    private val printPrimes: Boolean,
    private val sieve: Sieve,
    private val maxFind: Double
) : PrimeType, AnyPrime() {

    override val name = "prime number"

    private fun findDivisibles(currentPrime: Int) {
        for (i in (currentPrime * 2) .. sieve.primeDividers.lastIndex step currentPrime) {
            if (sieve.isPrime(i)) {
                sieve.primeDividers[i] = currentPrime
                sieve.quotients[i] = i / currentPrime
            }
        }

        return
    }

    override fun process(currentPrime: Int): Boolean {
        if (currentPrime < maxFind) findDivisibles(currentPrime)
        count++
        return true
    }

    override fun processAndPrint(currentPrime: Int): Boolean {
        if (process(currentPrime) && printPrimes) {
            println("$currentPrime is a $name")
        }
        return true
    }
}