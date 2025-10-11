package com.jvmlab.platon.wheel


@OptIn(ExperimentalUnsignedTypes::class)
open class BinarySieve(size: Int, newEraser: Boolean) : AbstractSieve<UByteArray>(size, newEraser) {
    override val sieve = UByteArray(size) {255.toUByte()}
    override val currentPrimePosition = createPosition(0, 0)

    init {
        currentPrimePosition.erase()
    }

    override fun createPosition(row: Int, column: Int): Position<UByteArray> =
        BinaryPosition(row, column, sieve)

    override fun createColumnEraser(row: Int, step: Int): ColumnEraser =
        BinaryEraser(row, step, sieve)

}