package com.jvmlab.platon.wolfram.battle.model

import com.jvmlab.platon.wolfram.battle.components.BoardMessage

/*
 * A small result object for user actions.
 * It contains the new state and a message to show in the UI.
 */
data class ToggleResult(
    val state: ChipGridState,
    val boardMessage: BoardMessage,
)