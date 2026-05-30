package com.jvmlab.platon.wolfram.battle.components

import com.jvmlab.platon.wolfram.battle.model.BoardSide

data class BoardMessage(
    val message: String = "",
    val side: BoardSide? = null
)
