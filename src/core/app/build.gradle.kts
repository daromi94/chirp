plugins {
    alias(libs.plugins.kotlin.jvm)

    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dep)
}

group = "com.daromi.chirp"

version = "0.1.0"

repositories { mavenCentral() }

dependencies {
    implementation(libs.spring.web)
    implementation(libs.jackson.kotlin)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.spring.test)
}

kotlin {
    jvmToolchain(21)
    compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") }
}

tasks.test { useJUnitPlatform() }
