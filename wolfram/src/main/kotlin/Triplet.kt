package com.jvmlab.platon.wolfram

data class Triplet(val left: Cell, val center: Cell, val right: Cell) {
    override fun toString(): String =
        "$left$center$right"


}
