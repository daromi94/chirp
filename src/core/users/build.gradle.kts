plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

group = "com.daromi.chirp.core"

repositories { mavenCentral() }

dependencies {
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
