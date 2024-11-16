package com.jvmlab.platon.eratosthenes

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BalancedPrimeTest {

    @Test
    fun process() {
        val balancedPrime = BalancedPrime()
        balancedPrime.process(47)
        balancedPrime.process(53)
        assertTrue(balancedPrime.process(59))
        assertFalse(balancedPrime.process(61))
    }
}