package com.jvmlab.platon.eratosthenes.primetypes

import com.jvmlab.platon.eratosthenes.Sieve
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TwinPrimeTest {


    @Test
    fun process() {
        val sieve = Sieve(50)
        val twinPrime = TwinPrime(sieve)

        assertTrue(twinPrime.process(5))
        sieve.primeDividers[9] = 3
        assertFalse(twinPrime.process(11))
    }
}