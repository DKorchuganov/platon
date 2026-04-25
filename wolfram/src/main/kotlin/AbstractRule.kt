package com.jvmlab.platon.wolfram

abstract class AbstractRule {
    protected val ruleMap = mutableMapOf<Triplet, Cell>()

    fun show() {
        ruleMap.forEach {
            println("${it.key} -> ${it.value}")
        }
    }


    fun apply(triplet: Triplet): Cell = ruleMap[triplet]!!

}