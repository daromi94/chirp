plugins {
    alias(libs.plugins.kotlin.jvm)

    alias(libs.plugins.kotlin.spring)
}

group = "com.daromi.chirp.core"

version = "0.1.0-SNAPSHOT"

dependencies {
    implementation(libs.kotlin.reflect)
    implementation(libs.jackson.kotlin)
    implementation(libs.spring.web)

    testImplementation(libs.kotlin.test)
}

kotlin {
    jvmToolchain(21)
    compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") }
}

tasks.test { useJUnitPlatform() }
