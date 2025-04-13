package com.jvmlab.platon.wheel


interface Sieve {
    val count: Int
    val currentPrime: Long
    fun nextPrime(): Boolean
    fun removeComposite()
    val hasComposites: Boolean
}