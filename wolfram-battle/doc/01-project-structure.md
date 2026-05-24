# 01. Project structure

This page explains the folders.

## Root folder

The root folder contains project-level files.

```text
build.gradle.kts
settings.gradle.kts
gradle.properties
README.md
BRD.md
AGENTS.md
doc/
src/
```

### `build.gradle.kts`

[build.gradle.kts](../build.gradle.kts) tells Gradle how to build the app.

It says:

- use Kotlin Multiplatform
- use Compose Multiplatform
- build a browser app with the `wasmJs` target
- use the Compose libraries

### `settings.gradle.kts`

[settings.gradle.kts](../settings.gradle.kts) gives the project name and tells Gradle where to find plugins and libraries.

### `gradle.properties`

[gradle.properties](../gradle.properties) has small Gradle and Kotlin settings.

### `BRD.md`

[BRD.md](../BRD.md) says what the app must do.

### `AGENTS.md`

[AGENTS.md](../AGENTS.md) tells Codex and other coding agents how to work in this project.

### `doc/`

The [doc](.) folder contains this tutorial.

## Source folder

Most important code is in `src`.

```text
src/
├── commonMain/
└── wasmJsMain/
```

### `commonMain`

[commonMain](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle) contains normal Kotlin app code.

In this project, it contains:

- UI layout
- state rules
- grid drawing

### `wasmJsMain`

[wasmJsMain](../src/wasmJsMain) contains browser-specific code and files.

It contains:

- [Main.kt](../src/wasmJsMain/kotlin/com/jvmlab/platon/wolfram/battle/Main.kt)
- [index.html](../src/wasmJsMain/resources/index.html)
- [styles.css](../src/wasmJsMain/resources/styles.css)

## Kotlin package

The main package is:

```text
com.jvmlab.platon.wolfram.battle
```

The Kotlin code is split like this:

```text
com/jvmlab/platon/wolfram/battle/
├── App.kt
├── components/
│   └── ChipGridCanvas.kt
└── model/
    ├── CellPosition.kt
    ├── ChipGridState.kt
    └── GridConfig.kt
```

### `model`

The [model](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/model) folder contains data and rules.

It does not draw UI.

### `components`

The [components](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/components) folder contains reusable UI parts.

In this app, it has one component: the grid canvas.

### `App.kt`

[App.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/App.kt) is the main screen.

It connects model and UI.
