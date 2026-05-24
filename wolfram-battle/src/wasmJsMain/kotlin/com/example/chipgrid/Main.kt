package com.example.chipgrid

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

/*
 * This is the browser entry point.
 *
 * Gradle builds this Kotlin code into JavaScript + WebAssembly files.
 * The browser loads index.html. That page has a <div id="compose-root">.
 * ComposeViewport puts our Compose UI inside that div.
 */
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(viewportContainerId = "compose-root") {
        ChipGridApp()
    }
}
