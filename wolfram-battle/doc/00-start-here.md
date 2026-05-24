# 00. Start here

This project is a small browser app.

It is written in Kotlin.

It uses Compose Multiplatform for Web. In this project, Compose runs in the browser using Kotlin/Wasm.

## What is Kotlin/Wasm?

Wasm means **WebAssembly**.

WebAssembly is a format that browsers can run.

You write Kotlin code. Gradle and Kotlin compile it to files that the browser can load.

So the browser does not run your Kotlin source code directly. It runs compiled output.

## What is Compose?

Compose is a UI framework.

A UI framework helps you build screens with buttons, text, layout, and drawing.

In Compose you write functions like this:

```kotlin
@Composable
fun Greeting() {
    Text("Hello")
}
```

This function describes a piece of UI.

You do not write HTML by hand for every button or text. Compose does most of that work.

## What does this app do?

The app shows a 100 × 50 grid.

The user can:

- click the left column to add or remove black chips
- click the right column to add or remove white chips
- put at most 10 chips on each side
- clear the board

The board grows or shrinks when the browser window size changes.

## Where to start reading code

Start with these files:

1. [GridConfig.kt](../src/commonMain/kotlin/com/example/chipgrid/model/GridConfig.kt) — board size and chip limit
2. [ChipGridState.kt](../src/commonMain/kotlin/com/example/chipgrid/model/ChipGridState.kt) — board state and rules
3. [App.kt](../src/commonMain/kotlin/com/example/chipgrid/App.kt) — screen layout
4. [ChipGridCanvas.kt](../src/commonMain/kotlin/com/example/chipgrid/components/ChipGridCanvas.kt) — grid drawing and mouse clicks
5. [Main.kt](../src/wasmJsMain/kotlin/com/example/chipgrid/Main.kt) — app start point

## Try it

Run:

```bash
gradle wasmJsBrowserDevelopmentRun
```

Open the URL that Gradle prints.

Then resize your browser window. The grid should resize too.
