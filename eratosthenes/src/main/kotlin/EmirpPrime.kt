package com.jvmlab.platon.eratosthenes

class EmirpPrime(private val sieve: BooleanArray) : PrimeType, AnyPrime() {
    override val name = "emirp prime"

    override fun process(currentPrime: Int) {
        var emirp = 0
        toDigits(currentPrime).forEach {
            emirp = emirp * 10 + it
        }
        if ((currentPrime > emirp) && sieve[emirp]) {
            println("$currentPrime and $emirp are emirp primes")
            count++
        }
    }

}