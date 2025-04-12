package com.jvmlab.platon.wheel

import kotlin.time.Duration

interface Sieve {
    val count: Int
    val currentPrime: Long
    fun nextPrime(): Boolean
    fun removeComposite()
    val removeCompositeDuration: Duration
    val hasComposites: Boolean
}