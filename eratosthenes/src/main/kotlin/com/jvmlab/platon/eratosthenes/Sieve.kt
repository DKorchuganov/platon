package com.jvmlab.platon.eratosthenes

class Sieve(size: Int) {
    val primeDividers = IntArray(size) {
        0
    }

    fun isPrime(number: Int): Boolean = (primeDividers[number] == 0)

    fun nextPrime(currentPrime: Int): Int? {
        for (i in (currentPrime + 1) .. primeDividers.lastIndex) {
            if (isPrime(i)) {
                return i
            }
        }
        return null
    }
}