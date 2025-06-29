package com.jvmlab.platon.wheel


@OptIn(ExperimentalUnsignedTypes::class)
class BinarySieve(size: Int) : AbstractSieve<UByteArray>(size) {
    override val sieve = UByteArray(size) {255.toUByte()}
    override val currentPrimePosition = createPosition(0, 0)

    init {
        currentPrimePosition.erase()
    }

    override fun createPosition(row: Int, column: Int): Position<UByteArray> =
        BinaryPosition(row, column, sieve)
}