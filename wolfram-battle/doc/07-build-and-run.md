# 07. Build and run

This project uses Gradle.

The main build file is [build.gradle.kts](../build.gradle.kts).

## Development run

Run this from the project root:

```bash
gradle wasmJsBrowserDevelopmentRun
```

Gradle will:

1. compile Kotlin
2. create browser files
3. start a local development server
4. print a URL

Open the URL in your browser.

It is usually:

```text
http://localhost:8080/
```

## Production build

Run:

```bash
gradle wasmJsBrowserDistribution
```

This creates static files here:

```text
build/dist/wasmJs/productionExecutable/
```

The main file is:

```text
index.html
```

But do not rely on opening it directly with `file://`.

Serve the folder with a small HTTP server:

```bash
cd build/dist/wasmJs/productionExecutable
python3 -m http.server 8080
```

Then open:

```text
http://localhost:8080/
```

## What Gradle creates

The production folder contains files for the browser.

They include:

- HTML
- JavaScript loader files
- WebAssembly files
- other generated assets

You normally do not edit generated files.

Edit source files in `src` instead.

## If the build fails

Check these things first:

1. Are you in the project root?
2. Is Java installed?
3. Is Gradle installed?
4. Did you replace `settings.gradle.kts` with the version from this project?

This project does not use `RepositoriesMode.FAIL_ON_PROJECT_REPOS`, because Kotlin/Wasm tooling may add a Node.js distribution repository internally.
