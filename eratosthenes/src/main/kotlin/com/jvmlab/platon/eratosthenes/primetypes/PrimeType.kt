package com.jvmlab.platon.eratosthenes.primetypes

interface PrimeType {

    fun process(currentPrime: Int): Boolean

    fun processAndPrint(currentPrime: Int): Boolean

    fun printCount()
}