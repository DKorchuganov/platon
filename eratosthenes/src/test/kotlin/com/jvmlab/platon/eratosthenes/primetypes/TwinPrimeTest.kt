package com.jvmlab.platon.eratosthenes.primetypes

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TwinPrimeTest {


    @Test
    fun process() {
        val sieve = BooleanArray(50) {
            true
        }
        val twinPrime = TwinPrime(sieve)

        assertTrue(twinPrime.process(5))
        sieve[9] = false
        assertFalse(twinPrime.process(11))
    }
}