plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "com.jvmlab.platon"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.jvmlab.platon.euler_sieve.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.jvmlab.platon:cli")
    testImplementation(kotlin("test"))
}

tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources")) // We need this for Gradle optimization to work
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}