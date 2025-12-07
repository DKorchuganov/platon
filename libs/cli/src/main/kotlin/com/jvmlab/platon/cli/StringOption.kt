package com.jvmlab.platon.cli

import com.jvmlab.platon.cli.BooleanOption

class StringOption(shortName: Char?, longName: String, description: String) :
    AnyOption(shortName, longName, description) {

    constructor(longName: String, description: String) : this(null, longName, description)

    constructor(shortName: Char?, description: String) : this(shortName, "", description)
}