package com.jvmlab.platon.eratosthenes.primetypes

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HappyPrimeTest {

    @Test
    fun process() {
        val happyPrime = HappyPrime()
        assertFalse(happyPrime.process(3))
        assertTrue(happyPrime.process(7))
    }
}