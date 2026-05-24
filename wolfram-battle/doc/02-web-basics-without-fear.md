# 02. Web basics without fear

You do not need to know much web development to understand this project.

But a few words are useful.

## Browser

A browser is a program like Chrome, Firefox, or Safari.

It can open web pages.

This project builds files that a browser can open.

## HTML

HTML is the basic structure of a web page.

This project has one HTML file:

[index.html](../src/wasmJsMain/resources/index.html)

It is very small:

```html
<div id="compose-root"></div>
```

This line creates an empty place on the page.

Compose puts the Kotlin UI inside this place.

The HTML file also loads a script:

```html
<script src="chipGridApp.js"></script>
```

You do not write this JavaScript file. Gradle creates it during the build.

## CSS

CSS is style for web pages.

This project has one CSS file:

[styles.css](../src/wasmJsMain/resources/styles.css)

It says that the page should use the full browser size:

```css
html, body, #compose-root {
    width: 100%;
    height: 100%;
    margin: 0;
}
```

It also removes the browser body's default scrolling.

Most app styling is not written in CSS here. It is written in Kotlin Compose code.

## JavaScript

JavaScript is the normal programming language of the browser.

But this project does not ask you to write JavaScript.

Kotlin and Gradle create browser files for you.

## Development server

A development server is a small local server on your computer.

When you run:

```bash
gradle wasmJsBrowserDevelopmentRun
```

Gradle starts a local server.

The browser can then open something like:

```text
http://localhost:8080/
```

This is still not a backend application. It only serves the frontend files.
