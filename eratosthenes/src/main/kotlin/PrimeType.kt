package com.jvmlab.platon.eratosthenes

interface PrimeType {

    fun process(currentPrime: Int): Boolean

    fun processAndPrint(currentPrime: Int): Boolean

    fun printCount()
}