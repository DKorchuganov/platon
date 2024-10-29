package com.jvmlab.platon.eratosthenes

class PrintPrime(
    private val printPrimes: Boolean,
    private val  sieve: BooleanArray,
    private val maxFind: Double
) : PrimeType {

    private var count = 0

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