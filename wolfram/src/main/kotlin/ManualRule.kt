package com.jvmlab.platon.wolfram

class ManualRule() : AbstractRule() {
    init {
        val list = listOf(Cell.DEAD, Cell.ALIVE)
        for (left in list)
            for (center in list)
                for (right in list) {
                    val triplet = Triplet(left, center, right)
                    var input = ""
                    while (input != Cell.DEAD.toString() && input != Cell.ALIVE.toString()) {
                        print("Enter ${Cell.ALIVE} or ${Cell.DEAD} for $triplet -> ")
                        input = readln().trim()
                        if (input != Cell.ALIVE.toString() && input != Cell.DEAD.toString()) {
                            print("Try again! ")
                        }
                    }
                    ruleMap[triplet] = if (input == Cell.ALIVE.toString()) Cell.ALIVE else Cell.DEAD
                }
    }
}