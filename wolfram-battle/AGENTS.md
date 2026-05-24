# AGENTS.md

This file is for Codex or another coding agent working in this repository.

Before changing behavior, read [BRD.md](BRD.md). The BRD is the source of truth for business requirements. Do not copy the BRD here. Keep this file focused on technical guidance for the project.

## Project type

This is a Kotlin/Wasm browser app built with Compose Multiplatform.

It has:

- no backend
- no handwritten JavaScript application logic
- Gradle as the build tool
- a small HTML file only to start the browser app
- a small CSS file only for page setup

## Build commands

Development run:

```bash
gradle wasmJsBrowserDevelopmentRun
```

Production static files:

```bash
gradle wasmJsBrowserDistribution
```

Production output folder:

```text
build/dist/wasmJs/productionExecutable/
```

Serve that folder over HTTP before opening it in the browser.

## Important Gradle note

Do not set `RepositoriesMode.FAIL_ON_PROJECT_REPOS` in `settings.gradle.kts` for this sample.

The Kotlin/Wasm browser tooling may add a Node.js distribution repository internally. `FAIL_ON_PROJECT_REPOS` can block that and break the build.

## Source structure

```text
src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/
├── App.kt
├── components/
│   └── ChipGridCanvas.kt
└── model/
    ├── CellPosition.kt
    ├── ChipGridState.kt
    └── GridConfig.kt

src/wasmJsMain/
├── kotlin/com/jvmlab/platon/wolfram/battle/Main.kt
└── resources/
    ├── index.html
    └── styles.css
```

### `src/commonMain`

Put normal shared Kotlin application code here.

In this project, most code lives here because there is only one platform target.

### `src/wasmJsMain`

Put browser-specific entry code and browser resources here.

`Main.kt` mounts Compose into the page.

`index.html` gives the browser a root element.

`styles.css` prepares the page size.

## Technical rules

- Keep board configuration in `GridConfig.kt`.
- Do not hardcode board dimensions in several files.
- Keep UI state immutable where practical.
- Prefer simple data classes and small functions.
- Keep all app logic in Kotlin.
- Do not add handwritten JavaScript unless the user explicitly asks.
- Keep comments educational.
- Keep docs in simple English.
- If behavior changes, update `BRD.md`, `README.md`, and the tutorial docs.

## Compose rules for this project

- `WolframBattleApp()` is the root app composable.
- `FlexibleBoard()` controls board size.
- `ChipGridCanvas()` draws the grid and chips.
- `ChipGridState` owns the state rules.
- `Main.kt` should remain small.

## Documentation rules

The `doc` folder is a tutorial.

When changing code, check whether a tutorial page links to that code. Update the explanation if the code changed.

Prefer relative Markdown links, for example:

```markdown
[App.kt](../src/commonMain/kotlin/com/jvmlab/platon/wolfram/battle/App.kt)
```

## Style rules

Use simple names.

Avoid clever abstractions.

This repository is more educational than production-style.
