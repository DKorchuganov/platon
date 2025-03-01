package com.jvmlab.platon.wheel

import kotlin.math.sqrt

class Sieve(private val size: Int) {
    private val sieve = Array(8) { BooleanArray(size) { true } }
    private val remainderByColumn = intArrayOf(1, 7, 11, 13, 17, 19, 23, 29)
    private val maxFind = sqrt(size.toDouble() * 30)
    private var currentPrimeRow = 0
    private var currentPrimeColumn = 1


    var primeCount = 3

    init {
        sieve[0][0] = false
    }

    fun nextPrime(): Long? {
        if (currentPrimeColumn < 7) {
            currentPrimeColumn++
        } else {
            currentPrimeColumn = 0
            currentPrimeRow++
        }

        while (currentPrimeRow < size) {
            while (currentPrimeColumn < 8) {
                if (sieve[currentPrimeColumn][currentPrimeRow]) {
                    return (currentPrimeRow * 30 + remainderByColumn[currentPrimeColumn]).toLong()
                }
                currentPrimeColumn++
            }
            currentPrimeColumn = 0
            currentPrimeRow++
        }

        return null
    }

}