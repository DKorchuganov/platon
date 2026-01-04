package com.jvmlab.platon.argozavr

class StringOption(
    shortName: Char?,
    longName: String,
    description: String
) : AnyOption<String?>(shortName, longName, description) {
    override var value: String? = null
}