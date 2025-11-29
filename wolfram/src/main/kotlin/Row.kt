package com.jvmlab.platon.wolfram

class Row(private val rule: Rule, initialList: List<Cell>) {

    private val list = initialList.toMutableList()


    fun nextList(): List<Cell> {
        val oldList = list.toList()
        oldList.forEachIndexed { index, center ->
            val left = if (index != 0) oldList[index - 1] else Cell.DEAD
            val right = if (index < oldList.lastIndex) oldList[index + 1] else Cell.DEAD
            val triplet = Triplet(left, center, right)
            list[index] = rule.apply(triplet)
        }
        return list.toList()
    }


}