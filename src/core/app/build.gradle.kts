plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.dep)
    alias(libs.plugins.spring.boot)
}

group = "com.daromi.chirp.core"

repositories { mavenCentral() }

dependencies {
    implementation(project(":users"))
    implementation(project(":posts"))

    // Kotlin
    implementation(libs.kotlin.reflect)
    testImplementation(libs.kotlin.test)

    // Jackson
    implementation(libs.jackson.kotlin)

    // Spring Boot
    implementation(libs.spring.boot.web)
    testImplementation(libs.spring.boot.test)
}

kotlin {
    jvmToolchain(21)

    compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") }
}

tasks.test { useJUnitPlatform() }
