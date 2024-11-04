plugins {
    alias(libs.plugins.kotlin.jvm)

    alias(libs.plugins.kotlin.spring)
}

dependencies {
    // Internal
    implementation(project(":users"))

    // External
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
