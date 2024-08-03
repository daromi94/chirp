plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "com.daromi.chirp.core"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.arrow.core)
    testImplementation(libs.kotlin.test)
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "$group.MainKt"
}

tasks.test {
    useJUnitPlatform()
}
