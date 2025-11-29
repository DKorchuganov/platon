package com.jvmlab.platon.wolfram


enum class Cell {
    DEAD {
        override fun toString() = "-"
    },

    ALIVE {
        override fun toString() = "*"
    }
}