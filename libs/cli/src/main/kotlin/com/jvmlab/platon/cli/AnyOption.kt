package com.jvmlab.platon.cli

abstract class AnyOption(
    override val shortName: Char?,
    override val longName: String,
    override val description: String
) : OptionInfo