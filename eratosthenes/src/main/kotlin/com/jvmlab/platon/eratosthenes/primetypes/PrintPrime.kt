package com.jvmlab.platon.eratosthenes.primetypes

import com.jvmlab.platon.eratosthenes.Sieve
import kotlin.math.sqrt

class PrintPrime(
    private val printPrimes: Boolean,
    private val sieve: Sieve
) : PrimeType, AnyPrime() {

    override val name = "prime number"
    private val maxFind = sqrt(sieve.size.toDouble())

    private fun findDivisibles(currentPrime: Int) {
        for (i in (currentPrime * currentPrime) .. sieve.primeDividers.lastIndex step currentPrime) {
            if (sieve.isPrime(i)) {
                sieve.primeDividers[i] = currentPrime
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