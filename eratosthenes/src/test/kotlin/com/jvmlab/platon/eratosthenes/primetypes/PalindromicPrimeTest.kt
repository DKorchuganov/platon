package com.jvmlab.platon.eratosthenes.primetypes

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PalindromicPrimeTest {

    @Test
    fun process() {
        val palindromicPrime = PalindromicPrime()
        assertFalse(palindromicPrime.process(10))
        assertTrue(palindromicPrime.process(131))
    }
}