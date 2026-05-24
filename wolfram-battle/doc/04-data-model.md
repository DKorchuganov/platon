# 04. Data model

The model is the part of the app that stores data and rules.

It does not draw the screen.

The model files are here:

[model folder](../src/commonMain/kotlin/com/example/chipgrid/model)

## GridConfig

[GridConfig.kt](../src/commonMain/kotlin/com/example/chipgrid/model/GridConfig.kt) stores the important numbers:

```kotlin
object GridConfig {
    const val ROWS: Int = 50
    const val COLUMNS: Int = 100
    const val MAX_CHIPS_PER_SIDE: Int = 10
}
```

If you want a different board size, this is the first file to check.

## CellPosition

[CellPosition.kt](../src/commonMain/kotlin/com/example/chipgrid/model/CellPosition.kt) describes one grid cell:

```kotlin
data class CellPosition(
    val column: Int,
    val row: Int,
)
```

The code uses zero-based numbers.

That means:

- first column is `0`
- last column is `99`
- first row is `0`
- last row is `49`

The UI messages show user-friendly numbers from 1.

## ChipSide

[ChipGridState.kt](../src/commonMain/kotlin/com/example/chipgrid/model/ChipGridState.kt) contains this enum:

```kotlin
enum class ChipSide {
    BlackLeft,
    WhiteRight,
}
```

It says which side the user clicked.

## ChipGridState

`ChipGridState` stores the rows that have chips:

```kotlin
data class ChipGridState(
    val blackChipRows: Set<Int> = emptySet(),
    val whiteChipRows: Set<Int> = emptySet(),
)
```

A `Set` is useful because a row can have only one chip on one side.

There is no need to store all 5000 cells.

We only store rows for the two playable columns.

## Toggle logic

The function `toggle` adds or removes a chip.

If the chip already exists, it removes it.

If the chip does not exist, it tries to add it.

If there are already 10 chips on that side, it does not add another one.

The function returns `ToggleResult`:

```kotlin
data class ToggleResult(
    val state: ChipGridState,
    val message: String,
)
```

This is useful because the app needs both:

- the new board state
- the message for the user
