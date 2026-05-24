import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    // Kotlin Multiplatform lets one project have different targets.
    // In this sample we only use one target: wasmJs for the browser.
    kotlin("multiplatform") version "2.3.21"

    // Compose Multiplatform gives us Compose UI for non-Android targets too.
    id("org.jetbrains.compose") version "1.11.0"

    // Required for Compose compiler support with Kotlin 2.x.
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.21"
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        // This name is also used by index.html as chipGridApp.js.
        outputModuleName.set("chipGridApp")

        browser {
            commonWebpackConfig {
                outputFileName = "chipGridApp.js"
            }
        }

        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            // Direct dependency coordinates are used to avoid deprecated aliases.
            implementation("org.jetbrains.compose.runtime:runtime:1.11.0")
            implementation("org.jetbrains.compose.foundation:foundation:1.11.0")
            implementation("org.jetbrains.compose.material:material:1.11.0")
            implementation("org.jetbrains.compose.ui:ui:1.11.0")
        }
    }
}
