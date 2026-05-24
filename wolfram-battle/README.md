# Wolfram Battle

A small educational Kotlin/Wasm + Compose Multiplatform browser app.

The browser tab title is `Wolfram Battle`. The app screen starts directly with the status panel and board.

It runs entirely in the browser:

- no backend
- no handwritten JavaScript app logic
- Kotlin source code
- Gradle build
- flexible 100 × 50 grid

## What it does

The app renders a landscape grid with:

- 100 columns
- 50 rows
- up to 10 black chips in the left column
- up to 10 white chips in the right column
- mouse-click toggling
- a Clear button
- automatic resize when the browser window changes

## Important documents

- [BRD.md](BRD.md) — business requirements
- [AGENTS.md](AGENTS.md) — guide for Codex and other coding agents
- [doc/README.md](doc/README.md) — tutorial index

## Requirements

You need:

- Java 17 or newer
- Gradle
- a modern browser with Kotlin/Wasm support

This project was created for a user who already has Java, Gradle, and Kotlin installed via SDKMAN.

## Build and run in development mode

From the project root:

```bash
gradle wasmJsBrowserDevelopmentRun
```

Gradle starts a local dev server and prints the URL.

It is usually:

```text
http://localhost:8080/
```

If port 8080 is busy, Gradle may choose another port. Use the URL printed in the terminal.

## Build a production browser bundle

```bash
gradle wasmJsBrowserDistribution
```

The generated static files will be in:

```text
build/dist/wasmJs/productionExecutable/
```

The main file is:

```text
build/dist/wasmJs/productionExecutable/index.html
```

For best results, serve this directory over HTTP instead of opening `index.html` via `file://`:

```bash
cd build/dist/wasmJs/productionExecutable
python3 -m http.server 8080
```

Then open:

```text
http://localhost:8080/
```

## Configuring the grid

The grid size and chip limit are in one file:

```text
src/commonMain/kotlin/com/example/chipgrid/model/GridConfig.kt
```

Current defaults:

```kotlin
object GridConfig {
    const val ROWS: Int = 50
    const val COLUMNS: Int = 100
    const val MAX_CHIPS_PER_SIDE: Int = 10
}
```

For a landscape board, `COLUMNS` is the horizontal size and `ROWS` is the vertical size.

## Project structure

```text
src/commonMain/kotlin/com/example/chipgrid/
├── App.kt
├── components/
│   └── ChipGridCanvas.kt
└── model/
    ├── CellPosition.kt
    ├── ChipGridState.kt
    └── GridConfig.kt

src/wasmJsMain/
├── kotlin/com/example/chipgrid/Main.kt
└── resources/
    ├── index.html
    └── styles.css
```

Short version:

- `model` contains state and rules
- `components` contains the reusable grid drawing component
- `App.kt` wires state, messages, and layout together
- `Main.kt` starts the app in the browser page
- `resources` contains the small HTML and CSS boot files

For a full explanation, start with [doc/README.md](doc/README.md).
