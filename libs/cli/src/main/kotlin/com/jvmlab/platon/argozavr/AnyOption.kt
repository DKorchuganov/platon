package com.jvmlab.platon.argozavr

sealed class AnyOption<T>(
    val shortName: Char?,
    val longName: String,
    val description: String
) {
    public abstract var value: T
    internal set
}