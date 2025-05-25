package com.jvmlab.platon.wheel

@OptIn(ExperimentalUnsignedTypes::class)
class BinaryPosition(row: Int, column: Int, val sieve: UByteArray) : Position {
    private val size = sieve.size
    private val remainderByColumn = longArrayOf(1, 7, 11, 13, 17, 19, 23, 29)

    private val checker = ubyteArrayOf(
        1.toUByte(),
        2.toUByte(),
        4.toUByte(),
        8.toUByte(),
        16.toUByte(),
        32.toUByte(),
        64.toUByte(),
        128.toUByte()
    )

    private val eraser = ubyteArrayOf(
        254.toUByte(),
        253.toUByte(),
        251.toUByte(),
        247.toUByte(),
        239.toUByte(),
        223.toUByte(),
        191.toUByte(),
        127.toUByte()
    )

    private var row = row
    private var column = column

    override var value: Long = toNumber()
        private set

    override var last = false
        private set

    override val isPrime
        get() = (sieve[row].and(checker[column]) > 0.toUByte())

    constructor(position: BinaryPosition) : this(position.row, position.column, position.sieve)

    override fun erase() {
        sieve[row] = sieve[row].and(eraser[column])
    }

    override fun next() {
        if (column < 7) {
            column++
        } else {
            if (row == size - 1) {
                last = true
                return
            }
            column = 0
            row++
        }
        value = toNumber()
    }

    private fun toNumber(): Long = row.toLong() * 30 + remainderByColumn[column]

    override fun nextRow(step: Int): Boolean {
        val newRow = row + step
        if (newRow <= sieve.lastIndex) {
            row = newRow
            return true
        }
        return false
    }
}