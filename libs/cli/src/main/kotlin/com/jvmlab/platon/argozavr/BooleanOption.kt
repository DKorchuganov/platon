package com.jvmlab.platon.argozavr

class BooleanOption(
    shortName: Char?,
    longName: String,
    description: String,
    valueIfAbsent: Boolean = false
) : AnyOption<Boolean>(shortName, longName, description) {
    override var value = valueIfAbsent
}