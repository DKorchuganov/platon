package com.jvmlab.platon.wheel

interface Position {
    val value: Long
    val last: Boolean
    val isPrime: Boolean
    fun erase()
    fun next()
    fun nextRow(step: Int): Boolean
}