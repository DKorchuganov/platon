package com.jvmlab.platon.wolfram.battle.model

/*
 * A board side tells which playable column owns the chip.
 */
enum class BoardSide(
    val label: String,
    val sentenceLabel: String,
) {
    Left(label = "left", sentenceLabel = "Left"),
    Right(label = "right", sentenceLabel = "Right"),
}