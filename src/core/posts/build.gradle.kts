plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

group = "com.daromi.chirp.core"
version = "0.1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
    implementation(project(":users"))

    // Kotlin
    implementation(libs.kotlin.reflect)
    testImplementation(libs.kotlin.test)

    // Jackson
    implementation(libs.jackson.kotlin)

    // Spring
    implementation(libs.spring.web)
}

kotlin {
    jvmToolchain(21)

    compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") }
}

tasks.test { useJUnitPlatform() }
