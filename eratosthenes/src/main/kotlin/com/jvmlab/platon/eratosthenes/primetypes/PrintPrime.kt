package com.jvmlab.platon.eratosthenes.primetypes

class PrintPrime(
    private val printPrimes: Boolean,
    private val  sieve: BooleanArray,
    private val maxFind: Double
) : PrimeType, AnyPrime() {

    override val name = "prime number"
    private var divisibles = 0

    private fun findDivisibles(currentPrime: Int, sieve: BooleanArray): Int {
        var number = 0
        for (i in (currentPrime * 2) .. sieve.lastIndex step currentPrime) {
            if (sieve[i]) {
                sieve[i] = false
                number++
            }
        }

        return number
    }

    override fun process(currentPrime: Int): Boolean {
        divisibles = if (currentPrime < maxFind) {
            findDivisibles(currentPrime, sieve)
        } else 0
        count++
        return true
    }

    override fun processAndPrint(currentPrime: Int): Boolean {
        if (process(currentPrime) && printPrimes) {
            println("$currentPrime: $divisibles")
        }
        return true
    }
}