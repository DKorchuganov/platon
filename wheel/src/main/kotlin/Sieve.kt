package com.jvmlab.platon.wheel

import kotlin.math.sqrt

class Sieve(private val size: Int) {
    private val sieve = Array(8) { BooleanArray(size) { true } }
    private val remainderByColumn = longArrayOf(1, 7, 11, 13, 17, 19, 23, 29)
    private val columnByRemainder = intArrayOf(
        0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 3, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 0, 0, 0, 0, 0, 7
    )
    private val maxFind = sqrt(size.toDouble() * 30)
    private var currentPrimeRow = 0
    private var currentPrimeColumn = 1

    var count = 0
        private set

    var currentPrime: Long = 7
        private set


    init {
        sieve[0][0] = false
    }

    fun nextPrime(): Boolean {
        if (currentPrimeColumn < 7) {
            currentPrimeColumn++
        } else {
            currentPrimeColumn = 0
            currentPrimeRow++
        }

        while (currentPrimeRow < size) {
            while (currentPrimeColumn < 8) {
                if (sieve[currentPrimeColumn][currentPrimeRow]) {
                    count++
                    currentPrime = currentPrimeRow.toLong() * 30 + remainderByColumn[currentPrimeColumn]
                    return true
                }
                currentPrimeColumn++
            }
            currentPrimeColumn = 0
            currentPrimeRow++
        }

        return false
    }


    fun removeComposite() {
        var nextNumber = currentPrime
        var product: Long = 0
        var productRow = 0
        var productColumn = 0

        repeat(8) {
            product = currentPrime * nextNumber
            productRow = (product / 30).toInt()
            productColumn = columnByRemainder[(product % 30).toInt()]
        }
    }

}