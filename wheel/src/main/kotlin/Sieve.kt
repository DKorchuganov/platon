package com.jvmlab.platon.wheel


interface Sieve {
    val count: Int             // how many prime numbers have been found in Sieve
    val currentPrime: Long     // current prime number
    fun nextPrime(): Boolean   // finds a next prime number after the currentPrime and sets the new currentPrime
    fun removeComposite()      // removes composite numbers that are divisible by the currentPrime
    val hasComposites: Boolean // true if Sieve still has composite numbers
}