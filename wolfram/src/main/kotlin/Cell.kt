package com.jvmlab.platon.wolfram


enum class Cell : Numeric {
    DEAD {
        override fun toString() = "-"
        override fun toInt(): Int = 0
    },

    ALIVE {
        override fun toString() = "*"
        override fun toInt(): Int = 1
    }
}

interface Numeric {

    fun toInt(): Int
}