package com.jvmlab.platon.wolfram

abstract class AbstractRule {
    protected val ruleMap = mutableMapOf<Triplet, Cell>()

    private fun power2(exponent: Int): Int {
        var result = 1
        for (i in 1..exponent) {
            result *= 2
        }
        return result
    }

    fun code(): Int {
        var result = 0
        ruleMap.forEach { (triplet, cell) ->
            result += power2(triplet.toInt()) * cell.toInt()
        }
        return result
    }

    fun show() {
        ruleMap.forEach { (triplet, cell) ->
        println("$triplet -> $cell")
        }

        println("rule code: ${code()}")
    }


    fun apply(triplet: Triplet): Cell = ruleMap[triplet]!!

}