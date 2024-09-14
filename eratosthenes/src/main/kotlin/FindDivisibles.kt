package com.jvmlab.platon.eratosthenes


fun findDivisibles(currentPrime: Int, sieve: BooleanArray): Int {
    var number = 0
    for (i in (currentPrime * 2) .. sieve.lastIndex step currentPrime) {
        if (sieve[i]) {
            sieve[i] = false
            number++
        }
    }

    return number
}