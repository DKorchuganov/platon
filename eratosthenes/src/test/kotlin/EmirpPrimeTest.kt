package com.jvmlab.platon.eratosthenes

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EmirpPrimeTest {

    @Test
    fun process() {
        val sieve = BooleanArray(50) {
            true
        }
        val emirpPrime = EmirpPrime(sieve)

        assertTrue(emirpPrime.process(31))
        assertFalse(emirpPrime.process(13))
        sieve[14] = false
        assertFalse(emirpPrime.process(41))
    }
}