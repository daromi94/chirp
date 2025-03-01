plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.dep)
    alias(libs.plugins.spring.boot)
}

group = "com.daromi.chirp.core.app"

version = "0.1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
    implementation(project(":users"))
    implementation(project(":posts"))

    implementation(libs.kotlin.reflect)
    implementation(libs.jackson.kotlin)
    implementation(libs.spring.boot.web)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.spring.boot.test)
}

kotlin {
    jvmToolchain(21)
    compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") }
}

tasks.test { useJUnitPlatform() }
