# 03. Compose basics

Compose is a way to describe UI with Kotlin functions.

## Composable functions

A function with `@Composable` can draw UI.

Example from [App.kt](../src/commonMain/kotlin/com/example/chipgrid/App.kt):

```kotlin
@Composable
private fun StatusPanel(
    gridState: ChipGridState,
    message: String,
    onClear: () -> Unit,
) {
    Row {
        Text("Black chips: ${gridState.blackCount}")
    }
}
```

This function draws part of the app screen.

## UI as a tree

Compose UI is like a tree.

Example:

```text
Surface
└── Column
    ├── StatusPanel
    └── FlexibleBoard
```

A parent contains children.

`Column` means children go from top to bottom.

`Row` means children go from left to right.

## Modifier

A `Modifier` changes how something looks or behaves.

Example:

```kotlin
Modifier
    .fillMaxWidth()
    .padding(12.dp)
```

This means:

- use all available width
- add 12 dp of inner space

`dp` is a size unit used in Compose. It is like a logical pixel.

## State

State is data that can change while the app is running.

In [App.kt](../src/commonMain/kotlin/com/example/chipgrid/App.kt):

```kotlin
var gridState by remember { mutableStateOf(ChipGridState()) }
```

This means:

- remember the board state
- when it changes, redraw the UI that uses it

This is one of the most important Compose ideas.

## Event callbacks

A callback is a function that is called later.

Example:

```kotlin
Button(onClick = onClear) {
    Text("Clear")
}
```

When the user clicks the button, Compose calls `onClear`.

The grid uses the same idea.

When the user clicks a cell, the grid calls `onCellClicked`.
