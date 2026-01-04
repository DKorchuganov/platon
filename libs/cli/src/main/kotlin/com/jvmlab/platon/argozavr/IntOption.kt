package com.jvmlab.platon.argozavr

class IntOption(
    shortName: Char?,
    longName: String,
    description: String,
    valueIfAbsent: Int = 0
) : AnyOption<Int>(shortName, longName, description) {
    override var value = valueIfAbsent
}