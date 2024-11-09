package com.jvmlab.platon.eratosthenes

class EmirpPrime(private val sieve: BooleanArray) : PrimeType, AnyPrime() {
    override val name = "emirp prime"
    private var emirp = 0

    override fun process(currentPrime: Int): Boolean {
        emirp = 0
        toDigits(currentPrime).forEach {
            emirp = emirp * 10 + it
        }
        if ((currentPrime > emirp) && sieve[emirp]) {
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