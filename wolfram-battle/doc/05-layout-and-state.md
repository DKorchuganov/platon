# 05. Layout and state

The main screen is in [App.kt](../src/commonMain/kotlin/com/example/chipgrid/App.kt).

## Root composable

The root composable is:

```kotlin
fun ChipGridApp()
```

This is the main screen of the app.

It creates state:

```kotlin
var gridState by remember { mutableStateOf(ChipGridState()) }
var message by remember { mutableStateOf("...") }
```

`gridState` stores the chips.

`message` stores the last action message.

## Screen layout

The screen uses this structure:

```text
MaterialTheme
└── Surface
    └── Column
        ├── StatusPanel
        └── Board area
```

### MaterialTheme

`MaterialTheme` gives default style to buttons and text.

### Surface

`Surface` is a basic background area.

Here it fills the whole browser window.

### Column

`Column` places children from top to bottom.

It is similar to saying: first status, then board.

## StatusPanel

`StatusPanel()` shows:

- black chip count
- white chip count
- last message
- selected black rows
- selected white rows
- Clear button

The Clear button uses this callback:

```kotlin
onClear = {
    gridState = gridState.clear()
    message = "Board cleared."
}
```

This creates a new empty state.

## FlexibleBoard

`FlexibleBoard()` is the part that makes the grid resize.

It uses `BoxWithConstraints`:

```kotlin
BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
    val boardWidth = maxWidth
    val boardHeight = boardWidth / BoardAspectRatio
}
```

`maxWidth` is the width available from the parent.

When the browser gets wider or narrower, Compose calculates it again.

The board height is calculated from the width.

Because the board is 100 × 50, the aspect ratio is 2:1.

So:

```text
height = width / 2
```

Then the canvas receives this size:

```kotlin
modifier = Modifier
    .width(boardWidth)
    .height(boardHeight)
```
