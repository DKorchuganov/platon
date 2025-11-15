package com.jvmlab.platon.wolfram

class Rule(code: UByte) {

    private val ruleMap = mutableMapOf<Triplet, Cell>()

    init {
        val list = listOf(Cell(false), Cell(true))
        var currentCode = code
        for (left in list)
            for (center in list)
                for (right in list) {
                    val triplet = Triplet(left, center, right)
                    ruleMap[triplet] = Cell((currentCode % 2u) > 0u)
                    currentCode = (currentCode / 2u).toUByte()
                }
    }


    fun show() {
        ruleMap.forEach {
            println("${it.key} -> ${it.value}")
        }
    }
}