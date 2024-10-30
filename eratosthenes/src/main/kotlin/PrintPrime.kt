package com.jvmlab.platon.eratosthenes

class PrintPrime(
    private val printPrimes: Boolean,
    private val  sieve: BooleanArray,
    private val maxFind: Double
) : PrimeType {

    private var count = 0

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

    override fun process(currentPrime: Int) {
        val divisibles = if (currentPrime < maxFind) {
            findDivisibles(currentPrime, sieve)
        } else 0
        if (printPrimes) {
            println("$currentPrime: $divisibles")
        }
        count++
    }

    override fun printCount() {
      printFound(count,"prime number")
    }

}