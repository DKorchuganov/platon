package com.jvmlab.platon.wheel

@OptIn(ExperimentalUnsignedTypes::class)
class BinaryPosition(row: Int, column: Int, sieve: UByteArray) :
    Position<UByteArray>(row, column, sieve) {
    override val size = sieve.size

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


    override val isPrime
        get() = (sieve[row].and(checker[column]) > 0.toUByte())

    override fun erase() {
        sieve[row] = sieve[row].and(eraser[column])
    }

    override fun copy(): Position<UByteArray> = BinaryPosition(row, column, sieve)

}