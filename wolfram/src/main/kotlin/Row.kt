package com.jvmlab.platon.wolfram

class Row(private val rule: Rule, initialList: List<Cell>, val loop: Boolean) {

    private val list = initialList.toMutableList()


    fun nextList(): List<Cell> {
        val oldList = list.toList()
        oldList.forEachIndexed { index, center ->
            val left = if (index != 0) oldList[index - 1] else if (loop) oldList[oldList.lastIndex] else Cell.DEAD
            val right = if (index < oldList.lastIndex) oldList[index + 1] else if (loop) oldList[0] else Cell.DEAD
            val triplet = Triplet(left, center, right)
            list[index] = rule.apply(triplet)
        }
        return list.toList()
    }


}