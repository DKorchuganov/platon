# BRD: Wolfram Battle

BRD means **Business Requirements Document**. This file explains what the app must do and why.

## 1. Project goal

Build a small web frontend for a Kotlin backend developer who wants to learn frontend work without learning JavaScript first.

The app must be:

- written in Kotlin
- built with Gradle
- based on Compose Multiplatform for the browser
- runnable in a browser
- usable without any backend server logic
- free from handwritten JavaScript
- simple and good for learning

## 2. Target user

The target user knows basic Kotlin:

- classes
- functions
- types
- interfaces
- basic generics
- simple collections like lists, sets, and maps

The target user does **not** know web development yet:

- no frontend experience
- no backend web experience required for this project
- no JavaScript knowledge required
- no HTML or CSS knowledge required before reading the docs

## 3. Main user story

As a user, I want to open a browser app and click on a grid, so that I can put chips on the left side and right side.

## 4. Functional requirements

### 4.1 Browser app

The project must build a static browser app.

The app must run in a browser after:

```bash
gradle wasmJsBrowserDevelopmentRun
```

The app must also produce static production files after:

```bash
gradle wasmJsBrowserDistribution
```

### 4.2 No backend

The app must not require a backend process to work.

A local development server may be used only to serve the static browser files.

### 4.3 No handwritten JavaScript

Application logic must be written in Kotlin.

HTML and CSS may exist only as small browser boot files.

### 4.4 Grid size

The board must be a landscape grid:

- 100 columns
- 50 rows

This means the logical aspect ratio is 2:1.

### 4.5 Flexible board size

The board must fit the available browser width.

When the user resizes the browser window, the board must resize too.

The board must keep the correct 100 × 50 aspect ratio.

### 4.6 Playable columns

Only two columns accept chips:

- the far-left column accepts left-side chips
- the far-right column accepts right-side chips

Clicks in any other column must not add chips.

### 4.7 Left-side chips

The user can put left-side chips only in the far-left column.

The user can put at most 10 left-side chips.

Clicking an empty row in the left column adds a left-side chip.

Clicking a row that already has a left-side chip removes it.

### 4.8 Right-side chips

The user can put right-side chips only in the far-right column.

The user can put at most 10 right-side chips.

Clicking an empty row in the right column adds a right-side chip.

Clicking a row that already has a right-side chip removes it.

### 4.9 Status panel

The app must show:

- current number of left-side chips
- current number of right-side chips
- rows that contain left-side chips
- rows that contain right-side chips
- a message after the last user action

The browser page title must be `Wolfram Battle`.

The app screen must not show a separate in-page title or title subtitle.

Rows shown to the user must start from 1.

Internal Kotlin code may use zero-based row and column numbers.

### 4.10 Clear button

The app must have a Clear button.

The Clear button must remove all chips from the board.

## 5. Non-functional requirements

### 5.1 Educational structure

The project must be easy to study.

The code must be split into small files with clear responsibilities.

### 5.2 Documentation

The root folder must contain:

- `BRD.md`
- `AGENTS.md`
- `README.md`

The project must also contain a `doc` folder with tutorial-style Markdown files.

The tutorial must explain the project from the ground up for a Kotlin developer who does not know web development.

The tutorial must use simple English, around B1-B2 level.

### 5.3 Source code comments

Source code files must contain comments that help learning.

Comments should explain why the code exists and what important Compose or browser ideas mean.

### 5.4 Central configuration

Important board values must be easy to find and change.

The grid size, chip limit, and chip styles must be stored in one configuration object:

- `GridConfig.ROWS`
- `GridConfig.COLUMNS`
- `GridConfig.MAX_CHIPS_PER_SIDE`
- `GridConfig.LEFT_CHIP_STYLE`
- `GridConfig.RIGHT_CHIP_STYLE`

### 5.5 Simple UI

The UI must be simple and readable.

It does not need advanced design.

The grid must be visible and usable with a mouse.

## 6. Out of scope

The following features are not required now:

- persistence after page reload
- saving data to a server
- user accounts
- routing between multiple pages
- mobile touch optimization
- tests
- game rules beyond chip placement and limits
- custom images or animations
