package com.jvmlab.platon.eratosthenes


fun nextPrime(currentPrime: Int, sieve: BooleanArray): Int? {
    for (i in (currentPrime + 1) .. sieve.lastIndex) {
        if (sieve[i]) {
            return i
        }
    }
    return null
}