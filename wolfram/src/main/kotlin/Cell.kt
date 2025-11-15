package com.jvmlab.platon.wolfram

data class Cell(val value: Boolean) {
    override fun toString(): String = if (value) "*" else "-"

}