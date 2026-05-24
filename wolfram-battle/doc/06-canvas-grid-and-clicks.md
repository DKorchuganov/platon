# 06. Canvas grid and clicks

The grid is drawn in [ChipGridCanvas.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/components/ChipGridCanvas.kt).

## Why Canvas?

The board has:

```text
100 × 50 = 5000 cells
```

We could create 5000 UI boxes, but that is not simple for learning.

Canvas is better here.

Canvas lets us draw:

- lines
- rectangles
- circles

## Canvas size

The canvas receives its size from [App.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/App.kt).

Inside the canvas, we calculate cell size:

```kotlin
val cellWidth = canvasSize.width / GridConfig.COLUMNS
val cellHeight = canvasSize.height / GridConfig.ROWS
```

If the browser window changes, the canvas size changes.

Then the cell width and height also change.

## Drawing the board

First, the canvas draws a white background:

```kotlin
drawRect(color = Color.White, size = canvasSize)
```

Then it shades the two playable columns:

- left column
- right column

Then it draws grid lines.

Every normal line is light.

Every 10th line is darker.

This helps the user see the large board.

## Drawing chips

Black chips are circles in the left column.

White chips are white circles with a black outline in the right column.

The chip radius is based on the current cell size:

```kotlin
val radius = minOf(cellWidth, cellHeight) * 0.42f
```

So chips resize with the board.

## Mouse clicks

The canvas uses this code:

```kotlin
.pointerInput(state) {
    detectTapGestures { offset ->
        ...
    }
}
```

`offset` is the mouse position inside the canvas.

It has:

- `offset.x`
- `offset.y`

These are pixel positions.

We convert the pixel position to a grid cell:

```kotlin
val column = floor(offset.x / cellWidth).toInt()
val row = floor(offset.y / cellHeight).toInt()
```

Then the canvas calls:

```kotlin
onCellClicked(CellPosition(column = column, row = row))
```

The canvas does not decide the business rule.

It only says: “The user clicked this cell.”

The rule lives in [App.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/App.kt) and [ChipGridState.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/model/ChipGridState.kt).

## Good separation

This is important:

- `ChipGridCanvas` knows how to draw and detect clicks.
- `ChipGridState` knows rules about chips.
- `App.kt` connects them.

This makes the project easier to change.
