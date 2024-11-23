package com.jvmlab.platon.eratosthenes.primetypes

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FactorialPrimeTest {

    @Test
    fun process() {
        val factorialPrime = FactorialPrime()
        assertTrue(factorialPrime.process(2))
        assertTrue(factorialPrime.process(719))
        assertFalse(factorialPrime.process(13))
    }
}