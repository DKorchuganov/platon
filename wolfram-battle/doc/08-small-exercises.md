# 08. Small exercises

These exercises help you learn the project.

Do them one by one.

## Exercise 1: change the chip limit

Open [GridConfig.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/model/GridConfig.kt).

Change:

```kotlin
const val MAX_CHIPS_PER_SIDE: Int = 10
```

to:

```kotlin
const val MAX_CHIPS_PER_SIDE: Int = 5
```

Run the app.

Try to add 6 black chips.

You should see a message that the side is full.

## Exercise 2: change the board size

Open [GridConfig.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/model/GridConfig.kt).

Try:

```kotlin
const val ROWS: Int = 25
const val COLUMNS: Int = 100
```

The board should become wider compared to its height.

Why? Because the aspect ratio changes.

## Exercise 3: change the page title

Open [index.html](../src/wasmJsMain/resources/index.html).

Find:

```html
<title>Wolfram Battle</title>
```

Change the browser tab title.

Run the app again.

## Exercise 4: change chip color

Open [GridConfig.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/model/GridConfig.kt).

Find:

```kotlin
val LEFT_CHIP_COLOR: Color = Color.Black
```

Try another color.

For example:

```kotlin
val LEFT_CHIP_COLOR: Color = Color.DarkGray
```

## Exercise 5: change grid line color

Open [ChipGridCanvas.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/components/ChipGridCanvas.kt).

Find:

```kotlin
val thinLine = Color(0xFFD8D8D8)
```

Try another gray color.

For example:

```kotlin
val thinLine = Color(0xFFE8E8E8)
```

## Exercise 6: add a new status line

Open [App.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/App.kt).

Find `StatusPanel()`.

Add another `Text(...)` line inside the `Column`.

For example:

```kotlin
Text(text = "Total chips: ${gridState.blackCount + gridState.whiteCount}")
```

Run the app and test it.
