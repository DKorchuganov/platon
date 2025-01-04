package com.jvmlab.platon.eratosthenes.primetypes

import com.jvmlab.platon.eratosthenes.Sieve
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EmirpPrimeTest {

    @Test
    fun process() {
        val sieve = Sieve(50)

        val emirpPrime = EmirpPrime(sieve)

        assertTrue(emirpPrime.process(31))
        assertFalse(emirpPrime.process(13))
        sieve.primeDividers[14] = 2
        assertFalse(emirpPrime.process(41))
    }
}