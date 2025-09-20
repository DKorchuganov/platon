package com.jvmlab.platon.cli

@OptIn(ExperimentalUnsignedTypes::class)
class BinaryEraser(
    private val row: Int,
    private val step: Int,
    private val sieve: UByteArray
) : ColumnEraser {

    var currentEraser = 255.toUByte()

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

    override fun addColumn(column: Int) {
        currentEraser = currentEraser.and(eraser[column])
    }

    override fun erase() {
        for (currentRow in row .. sieve.lastIndex step step) {
            sieve[currentRow] = sieve[currentRow].and(currentEraser)
        }
    }
}